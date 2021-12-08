package ensf614project.src.config;


/**
 * This class helps the database connect and gives the user access to the admin methods within
 */
public class Configuration {

	static public String getUsername() {
		return "mock_user";
	}

	static public String getPassword() {
		return "password";
	}

	static public String getConnection() {
		return "jdbc:mysql://localhost:3306/ensf614_movie_theatre";
	}
	

}
