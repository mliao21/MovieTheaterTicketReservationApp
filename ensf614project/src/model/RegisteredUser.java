package ensf614project.src.model;
//package ensf614project.src.model;

import java.util.ArrayList;

/**
 * This class represents a registered user.
 * Registered users can create and manage their own accounts and have credit cards stored in the database
 */
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
	
	public static synchronized void RegisteredInstance() {
		onlyInstance = new RegisteredUser();
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
	@Override
	public String toString() {
		return "RegisteredUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", cardFullName=" + cardFullName + ", cardNum=" + cardNum + ", cardExp="
				+ cardExp + ", cardCVV=" + cardCVV + ", creditCodes=" + creditCodes + ", password=" + password + "]";
	}
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getCardFullName() {
		return cardFullName;
	}
	public String getCardNum() {
		return cardNum;
	}
	public String getCardExp() {
		return cardExp;
	}
	public int getCardCVV() {
		return cardCVV;
	}
	public ArrayList<String> getCreditCodes() {
		return creditCodes;
	}
	public String getPassword() {
		return password;
	}
	
	
	
	
	
	
	

}
