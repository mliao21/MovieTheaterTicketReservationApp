//package ensf614project.src.controller;
package ensf614project.src.controller;

//import ensf614project.src.model.OrdinaryUser;
//import ensf614project.src.model.Seat;
//import ensf614project.src.model.User;

import java.util.ArrayList;

import ensf614project.src.model.OrdinaryUser;
import ensf614project.src.model.Seat;
import ensf614project.src.model.User;

public class testModelController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User me = OrdinaryUser.getInstance();
		ModelController test = new  ModelController(me);
		test.loadModelsQuery();
		ArrayList<Seat> seatList = test.getTheaterList().get(0).getSeatList();
		for (int i = 0; i < seatList.size(); i++) { 		      
	          System.out.println("Seat: " + seatList.get(i).getId() + " row: " + seatList.get(i).getRow() + " column: " + seatList.get(i).getCol() + " status: " + seatList.get(i).isStatus()); 		
	      }  
		
		System.out.println(test.getMovieList().get(0).getName());
		for(int i = 0; i< test.getMovieList().size();i++) {

			System.out.println("Movide id: "+test.getMovieList().get(i).getId());
			
		}
		
		//System.out.println(test.getShowTimeList().get(0).getMovie().getName());
		for(int i = 0; i< test.getAllSubscribers().size();i++) {
			System.out.println(test.getAllSubscribers().get(i));
		}
		
		//test.createTicket(1, 2, 10, "SOLD", "mike@mike.com", "111111111111");
		//test.addMovies("TEST Movie2", "2021-01-01", "Titanic, the movie", 100, "Black Theatre", "08:00:00", "11:14:00", "2021-02-02");
		System.out.println(test.getCouponList().get(0).toString());
		test.login("al@test.com", "testpassword");

	}

}
