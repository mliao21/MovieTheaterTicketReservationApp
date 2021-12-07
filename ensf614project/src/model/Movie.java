//package ensf614project.src.model;
package ensf614project.src.model;

import java.util.Date;

public class Movie {
	private int id, runTime;
	private String name;
	private String description;
	private Date releaseDate;
	private String releaseDateString;
	
	public Movie(int id, String name, Date releaseDate, String description, int runTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		
	}

	public Movie(String name, String releaseDateString, String description, int runTime) {
		super();
		this.name = name;
		this.description = description;
		this.releaseDateString = releaseDateString;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRunTime() {
		return runTime;
	}
	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseDateString() {
		return releaseDateString;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", runTime=" + runTime +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", releaseDate=" + releaseDate +
				'}';
	}
}
