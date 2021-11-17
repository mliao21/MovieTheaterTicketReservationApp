package ensf614project;

public class Theater {
	private Movie[] movies;
	private String name;
	private String address;
	public Theater(Movie[] movies, String name, String address) {
		super();
		this.movies = movies;
		this.name = name;
		this.address = address;
	}
	public Movie[] getMovies() {
		return movies;
	}
	public void setMovies(Movie[] movies) {
		this.movies = movies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
