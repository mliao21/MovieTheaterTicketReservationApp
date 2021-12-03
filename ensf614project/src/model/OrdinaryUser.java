package ensf614project.src.model;

public class OrdinaryUser extends User{
	
	private OrdinaryUser() {
		super();
	}
	
	 public static synchronized User getInstance() {
	       if (onlyInstance == null) {
	    	   onlyInstance = new OrdinaryUser();
	       }

	       return onlyInstance;
	    }       
	
	

}
