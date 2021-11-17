package ensf614project;

import java.time.LocalTime;

public class Movie {
	private String name;
	
	private LocalTime[] showTimes;
	public Movie(String name, LocalTime[] showTimes) {
		super();
		this.name = name;
		this.showTimes = showTimes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalTime[] getShowTimes() {
		return showTimes;
	}
	public void setShowTimes(LocalTime[] showTimes) {
		this.showTimes = showTimes;
	}
	
	

}
