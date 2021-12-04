package model;

public class User {
	protected static User onlyInstance;

	protected User() {
		
	}
	
	
	public static synchronized User getOnlyInstance() {
		if (onlyInstance == null) {
			onlyInstance = new User();
	       }
		return onlyInstance;
	}
	
	
	
	

}
