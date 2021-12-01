package model;

public class Movie {
	private String name;
	private String releaseDate;
	private Theater t;
	public Movie(String name, String releaseDate, Theater t) {
		super();
		this.name = name;
		this.releaseDate = releaseDate;
		this.t = t;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Theater getT() {
		return t;
	}
	public void setT(Theater t) {
		this.t = t;
	}
	
	

}
