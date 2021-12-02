package ensf614project.src.model;

import ensf614project.src.model.Theater;

public class ShowTime {
	private int hour;
	private int minute;
	private Movie movie;
	private Theater theater;
	public ShowTime(int hour, int minute, Movie movie, Theater theater) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.movie = movie;
		this.theater = theater;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Theater getTheater() {
		return theater;
	}
	public void setTheater(Theater theater) {
		this.theater = theater;
	}
	
	

}
