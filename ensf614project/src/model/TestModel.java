package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import config.Configuration;

public class TestModel {
	
	public HashMap<String, Boolean> getTestSeats(){
		String id = "";
		HashMap<String, Boolean> test = new HashMap<String, Boolean>();
		for (int i = 1; i <= 3; ++i) {			 
			  for(int j = 1; j <=3; ++j) {
				  id = "red:"+i+":"+j;
				  test.put(id, false);
			  }
			
			}
		
		return test;
	}
	
	public HashMap<String, Boolean> getSeats(String theaterId){
		String id = "";
		
		boolean occupy = false;
		HashMap<String, Boolean> test = new HashMap<String, Boolean>();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf614_movie_theatre", Configuration.getUsername(), Configuration.getPassword());
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SEAT_CHART WHERE THEATREID = " + theaterId);
			ResultSet seat;
			while(rs.next()){
                id = rs.getString("SEATID");
                
                seat = stmt2.executeQuery("SELECT * FROM SEAT_INSTANCE WHERE SEATID = " + id);
                while(seat.next()){
                if(seat.getString("Occupied") == "1") {
                	occupy = true;
                } else {
                	occupy = false;
                }
                }
                test.put(id + ":" + rs.getString("SEATROW")+ ":" + rs.getString("SEATCOL"), occupy);
                
                
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return test;
	}
	
	
	public static void main(String[] args) {
		TestModel a = new TestModel();
		Theater tt = new Theater("2");
		tt.createAllSeats(a.getSeats(tt.getNameId()));
		ArrayList<Seat> seatList = tt.getSeatList();
		for (int i = 0; i < seatList.size(); i++) { 		      
	          System.out.println("Seat: " + seatList.get(i).getId() + " row: " + seatList.get(i).getRow() + " column: " + seatList.get(i).getCol() + " status: " + seatList.get(i).isStatus()); 		
	      }   
		
		
	}
}
