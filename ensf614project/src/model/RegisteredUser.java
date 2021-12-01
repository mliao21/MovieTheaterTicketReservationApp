package model;

import java.util.ArrayList;

public class RegisteredUser extends User{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String cardFullName;
	private String cardNum;
	private String cardExp;
	private int cardCVV;
	private ArrayList<String> creditCodes;
	private String password;
	
	
	private RegisteredUser() {
		
	}
	public static synchronized User getInstance() {
	       if (onlyInstance == null) {
	    	   onlyInstance = new RegisteredUser();
	       }

	       return onlyInstance;
	} 
	
	
	
	
	public void loadUserinfo(int id, String firstName, String lastName, String email, String address, String cardFullName,
			String cardNum, String cardExp, int cardCVV, ArrayList<String> creditCodes, String password) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.cardFullName = cardFullName;
		this.cardNum = cardNum;
		this.cardExp = cardExp;
		this.cardCVV = cardCVV;
		this.creditCodes = creditCodes;
		this.password = password;
	}
	
	
	
	
	

}
