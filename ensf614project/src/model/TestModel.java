package ensf614project.src.model;

import java.util.ArrayList;
import java.util.HashMap;

public class TestModel {
	
	public HashMap<String, Boolean> getSeats(){
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
	
	public static void main(String[] args) {
		TestModel a = new TestModel();
		Theater tt = new Theater("red");
		tt.createAllSeats(a.getSeats());
		ArrayList<Seat> seatList = tt.getSeatList();
		for (int i = 0; i < seatList.size(); i++) { 		      
	          System.out.println("Seat: " + seatList.get(i).getId() + " row: " + seatList.get(i).getRow() + " column: " + seatList.get(i).getCol()); 		
	      }   
		
		
	}
}
