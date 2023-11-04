package task;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="tasks")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	public int taskId;

	@Column(name="name")
	public String name;

	@Column(name="description")
	public String description;

	@Temporal(TemporalType.TIMESTAMP)
	public Date deadline;

	@Column(name="color")
	public String color;

	public Task(String name, String description, Date deadline, String color) {
		super();
		this.name = name;
		this.description = description;
		this.deadline = deadline;
		this.color = color;
	}
	
	public Task() {} // It gives error if this constructor is not defined
	
	public String getName() { // Needed when sorting the list of tasks in the TasksScreenController.generateTasksList method
        return name;
    }

}