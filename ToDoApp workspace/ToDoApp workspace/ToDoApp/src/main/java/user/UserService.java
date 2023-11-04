package user;

import java.util.List;

import javax.persistence.Persistence;

public class UserService {
	private UserDao userDao;

	public UserService() {
		try {
			userDao = new UserDao(Persistence.createEntityManagerFactory("App"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void addUser(User newUser) {
		userDao.create(newUser);
	}


	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getUserFromLogin(String userName, String password) throws Exception { // it is called from the LoginScreenController
		List<User> users = userDao.find(userName);
		if (users.size() == 0) // The user cannot be found
			return null;
			
		User u = users.get(0);

		return u.password.equals(password) ? u : null; // Returns the user if the password matches
	}



}