package model;

import java.util.ArrayList;

public class Theater {
	private String nameId;
	private ArrayList<Seat> seatList;
	
	public void createAllSeats(int row, int col) {
		for ( int i = 0 ; i < row ; i++ )
		   {
		      
		      for ( int j = 0 ; i < col ; i++ )
		      {
		         this.seatList.add(new Seat(nameId+"r"+i+"c"+j, i, j));
		      }
		     
		   }
		
	}

}
