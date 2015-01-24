package autentificattion.dao;

import autentificattion.model.User;

/**
 * Interface for User DAOs
 * 
 */
public interface UserDao  {
	
	/**
	 * Find a User by username
	 * @param username the user name
	 * @return the User that match username
	 */
	User findByUsername(String username);

}
