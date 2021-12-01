package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Theater {
	private String nameId;
	private ArrayList<Seat> seatList;
	
	
	
	public Theater(String nameId) {
		super();
		this.nameId = nameId;
	}



	public void createAllSeats(HashMap<String, Boolean> seats) {
		this.seatList.clear();
		int i = 0,j = 0;
		Seat tempseat;
		for (Map.Entry<String, Boolean> item : seats.entrySet()) {
		    String key = item.getKey();
		    boolean value = item.getValue();
		    String[] temp = key.split(":");
		    i = Integer.valueOf(temp[1]);
		    j = Integer.valueOf(temp[2]);
		    tempseat =  new Seat(key, i, j);
		    tempseat.setStatus(value);
		    this.seatList.add(tempseat);
		    
		}
		
		
		
		
		
	}
	
	

}
