package controllers;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import task.Task;
import task.TaskService;

/*
	Contains the methods that are called in the TaskEditorScreen.fxml
*/

public class TaskEditorScreenController {

	@FXML
	Node backButtonTaskEditorScreen;

	@FXML
	Label tasksEditorScreenTitle;

	@FXML
	Button updateOrAddTaskButton;

	@FXML
	TextField taskNameField;

	@FXML
	TextField taskDescriptionField;

	@FXML
	ColorPicker taskColorField;

	@FXML
	DatePicker taskDeadlineField;
	
	@FXML
	Label taskEditorErrorText;

	public static Task currentTask;

	@FXML
	public void initialize() { // Called when the TasksEditorScreen is loaded
		if (currentTask == null) {
			tasksEditorScreenTitle.setText("Add task");
			updateOrAddTaskButton.setText("Add task");
		} else {
			tasksEditorScreenTitle.setText("Modify task");
			updateOrAddTaskButton.setText("Modify task");
			taskNameField.setText(currentTask.name);
			taskDescriptionField.setText(currentTask.description);
			taskColorField.setValue(Color.web(currentTask.color)); // Converts String to Color
			taskDeadlineField.setValue(currentTask.deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); // Converts Date to LocalDate, because Date type is given from mysql
		}

	}

	public void backFromTaskEditorScreen(ActionEvent actionEvent) { // It is called when the back button is pressed
		new SceneLoader().openScene((Node) actionEvent.getSource(), "TasksScreen");
	}

	public void updateOrAddTask(ActionEvent actionEvent) throws InvalidDateException { // It is called
		List<TextField> textFields = Arrays.asList(taskNameField, taskDescriptionField );
		
		for (TextField textField : textFields) {
			if (textField.getText().isEmpty()) {
				new TextAnimationHandler().SetTextForAFewSeconds(taskEditorErrorText, "All entries should be filled!", 5);
				return;
			}
		}
		
		try {
	        LocalDate date = taskDeadlineField.getValue();
	        if (date == null) {
	            throw new InvalidDateException("Invalid Deadline!");
	        }
	        if (date.isBefore(LocalDate.now())){
	        	throw new InvalidDateException("Deadline is in the past!");
	        }
	    } catch (InvalidDateException e) {
	        new TextAnimationHandler().SetTextForAFewSeconds(taskEditorErrorText, e.getMessage(), 5);
	        return;
	    }
		
		if (taskDeadlineField.getValue() == null) {
		    new TextAnimationHandler().SetTextForAFewSeconds(taskEditorErrorText, "Date is invalid", 5);
		    return;
		}
		
		String taskName = taskNameField.getText();
		String taskDesciption = taskDescriptionField.getText();
		Color taskColor = taskColorField.getValue();
		LocalDate taskDate = taskDeadlineField.getValue();
		Task newTask = new Task(
				taskName, 
				taskDesciption, 
				Date.from(taskDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), // LocalDate is converted to Date, because the Task accepts Date type
				taskColor.toString().replaceAll("^(.{2})(.*)(.{2})$", "$2")); // .toString() converts the color into a format where 0x is added to the beginning of the String and two more characters are added to the final, so a regex is used to remove the first 2 and last 2 characters of the String thus making it valid
		
		try {
	        if (currentTask == null) {
	            throw new Exception("Task is null");
	        }
	        newTask.taskId = currentTask.taskId; // Otherwise the the task cannot be found in the table
	        new TaskService().updateTask(newTask);
	    } catch (Exception e) {
	        new TaskService().addTask(newTask);
	    }
		
		backFromTaskEditorScreen(actionEvent);
	}
	
}