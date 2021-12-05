package ensf614project.src.model;
//package ensf614project.src.model;

public class ShowTime {
	private int id;
	private String startTime, endTime;
	private Movie movie;
	private Theater theater;
	public ShowTime(int id, String startTime, String endTime, Movie movie, Theater theater) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.movie = movie;
		this.theater = theater;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
