package ensf614project.src.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.OrdinaryUser;
import ensf614project.src.model.RegisteredUser;
import ensf614project.src.model.Seat;
import ensf614project.src.model.User;

public class TestContoller {
	
	public boolean login(String email, String password) {
		
		int id, cardCVV;
		String firstName, lastName, address, cardFullName,  cardNum, cardExp, username;
		ArrayList<String> creditCodes = new ArrayList<String> (); 
		
		try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            String statement = "SELECT * FROM REGISTERED_USERS WHERE Email = ? AND Password = ?;";
            PreparedStatement prepStatement = conn.prepareStatement(statement);
            prepStatement.setString(1, email);
            prepStatement.setString(2, password);
            ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
            	
            	id = resultSet.getInt("UserID");
            	username = resultSet.getString("Username");
            	firstName = resultSet.getString("Fname");
            	lastName = resultSet.getString("Lname");
            	cardNum = resultSet.getString("CreditCardNo");
            	address = "";
            	cardFullName = firstName + lastName;
            	cardExp = "";
            	cardCVV = 0;
                
            	
                
            	
            	
            	RegisteredUser.RegisteredInstance();
        		RegisteredUser temp = (RegisteredUser) RegisteredUser.getOnlyInstance();
        		temp.loadUserinfo(id, firstName, lastName, email, address, cardFullName, cardNum, cardExp, cardCVV, creditCodes, password);
        		System.out.println(temp.toString());
            	conn.close();
            	return true;
                
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        return false;
		//check if user match
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestContoller test = new TestContoller();
		test.login("al@test.com", "testpassword");
	}
}
