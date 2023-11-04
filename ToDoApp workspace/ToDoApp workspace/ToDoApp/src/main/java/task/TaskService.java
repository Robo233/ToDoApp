package task;

import java.util.List;
import javax.persistence.Persistence;

public class TaskService {
	private TaskDao taskDao;

	public TaskService() {
		try {
			taskDao = new TaskDao(Persistence.createEntityManagerFactory("App"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void addTask(Task newTask) {
		taskDao.create(newTask);
	}

	public void updateTask(Task updatedTask) {
		taskDao.update(updatedTask);
	}
	
	public void deleteTask(Task taskToDelete) {
	    taskDao.delete(taskToDelete);
	}

	public List<Task> getAllTasks() {
		return taskDao.findAll();
	}
}
