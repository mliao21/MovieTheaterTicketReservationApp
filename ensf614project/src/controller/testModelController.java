package controller;

import java.util.ArrayList;

import model.OrdinaryUser;
import model.Seat;
import model.User;

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
		System.out.println(test.getShowTimeList().get(0).getMovie().getName());
		for(int i = 0; i< test.getAllSubscribers().size();i++) {
			System.out.println(test.getAllSubscribers().get(i));
		}
		
		//test.createTicket(1, 2, 10, "SOLD", "mike@mike.com", "111111111111");
		test.addMovies("TEST Movie", "2021-01-01", "Titanic, the movie", 100, "Black Theatre", "08:00:00", "11:14:00", "2021-02-02");
		
		

	}

}
