package ensf614project.src.model;

/**
 * OrdinaryUser class - contains no special attributes
 */
public class OrdinaryUser extends User{
	
	private OrdinaryUser() {
		super();
	}

	/**
	 * Implementation of the singleton pattern
 	 * @return the only instance of the OrdinaryUser class
	 */
	public static synchronized User getInstance() {
	       if (onlyInstance == null) {
	    	   onlyInstance = new OrdinaryUser();
	       }

	       return onlyInstance;
	    }       
	
	

}
