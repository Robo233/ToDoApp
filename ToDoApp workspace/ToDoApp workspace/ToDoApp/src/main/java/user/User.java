package user;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	public String password;
	
	@Column(name="username")
	private String username;

	public User(String firstName, String lastName, String password, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
	}

	public User() {} // It gives error if this constructor is not defined
	
}
