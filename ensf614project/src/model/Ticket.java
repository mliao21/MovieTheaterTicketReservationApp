package ensf614project.src.model;

import ensf614project.src.model.Theater;

import java.time.LocalDate;

public class Ticket {
	private String id;
	private User user;
	private ShowTime showtime;
	private LocalDate purchaseDate;
	private double price;
	private Seat seat;
	public Ticket(User user, ShowTime showtime, double price, Seat seat) {
		super();
		this.user = user;
		this.showtime = showtime;
		this.price = price;
		this.seat = seat;
	}
	
	public Movie getMovie() {
		return this.showtime.getMovie();
	}
	
	public void setMovie(Movie m) {
		this.showtime.setMovie(m);
	}
	
	public Theater getTheater() {
		return this.showtime.getTheater();
	}
	
	public void setTheater(Theater t) {
		this.showtime.setTheater(t);
	}

}
