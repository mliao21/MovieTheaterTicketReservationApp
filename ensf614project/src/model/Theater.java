//package model;
package ensf614project.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Theater {
	private int id,capacity;
	
	private String nameId;
	private ArrayList<Seat> seatList;
	
	
	
	public Theater(int id, String nameId, int capacity) {
		super();
		this.nameId = nameId;
		seatList = new ArrayList<Seat>();
		this.id=id;
		this.capacity = capacity;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public void createAllSeats(HashMap<String, Boolean> seats) {
		this.seatList.clear();
		int i = 0;
		String j = "";
		Seat tempseat;
		for (Map.Entry<String, Boolean> item : seats.entrySet()) {
		    String key = item.getKey();
		    boolean value = item.getValue();
		    String[] temp = key.split(":");
		    i = Integer.valueOf(temp[1]);
		    j = temp[2];
		    tempseat =  new Seat(key, i, j);
		    tempseat.setStatus(value);
		    this.seatList.add(tempseat);
		    
		}
		
		
		
		
		
	}



	public String getNameId() {
		return nameId;
	}



	public void setNameId(String nameId) {
		this.nameId = nameId;
	}



	public ArrayList<Seat> getSeatList() {
		return seatList;
	}



	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}
	
	

}
