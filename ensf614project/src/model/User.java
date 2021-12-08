package ensf614project.src.model;

/**
 * Parent User class
 */
public class User {
	protected static User onlyInstance;

	protected User() {
		
	}

	/**
	 * Singleton method
	 * @return User
	 */
	public static synchronized User getOnlyInstance() {
		if (onlyInstance == null) {
			onlyInstance = new User();
	       }
		return onlyInstance;
	}
	
	
	
	

}
