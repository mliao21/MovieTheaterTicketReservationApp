//package ensf614project.src.controller;
package ensf614project.src.controller;

import java.sql.Date;

//import ensf614project.src.model.OrdinaryUser;
//import ensf614project.src.model.Seat;
//import ensf614project.src.model.User;

import java.util.ArrayList;

import ensf614project.src.model.Movie;
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
		//System.out.println(test.getShowTimeList().get(0).getMovie().getName());
		for(int i = 0; i< test.getAllSubscribers().size();i++) {
			System.out.println(test.getAllSubscribers().get(i));
		}
		
		//test.createTicket(1, 2, 10, "SOLD", "mike@mike.com", "111111111111");
		//Movie movie = new Movie("The Matrix",new Date(2018, 11, 7),"The Matrix is a 1999 American epic science fiction film directed by The Wachowskis and produced by Wachowski Productions, based on the story of the same name by Dan ", 150);
		//test.addMovies(movie, 1,10, "2021-12-07", "2021-12-25");
		//test.cancelMovie(3);
		//test.refundTicket(2);
		//test.issueCoupon(2, false);
		//test.issueCoupon(1, false);

		
		System.out.println(test.getCouponList().get(0).toString());
	}

}
