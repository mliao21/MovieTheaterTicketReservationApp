package ensf614project;

import java.time.LocalDate;

public class Ticket {
	private String name;
	private int seatNum;
	private LocalDate datePurchased;
	private LocalDate dateEvent;
	private Theater theater;
	private long cost;
	public Ticket(String name, int seatNum, LocalDate datePurchased, LocalDate dateEvent, long cost) {
		super();
		this.name = name;
		this.seatNum = seatNum;
		this.datePurchased = datePurchased;
		this.dateEvent = dateEvent;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public LocalDate getDatePurchased() {
		return datePurchased;
	}
	public void setDatePurchased(LocalDate datePurchased) {
		this.datePurchased = datePurchased;
	}
	public LocalDate getDateEvent() {
		return dateEvent;
	}
	public void setDateEvent(LocalDate dateEvent) {
		this.dateEvent = dateEvent;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	

}
