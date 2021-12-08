package ensf614project.src.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.*;

public class ModelController {
	private User userInstance;
	private ArrayList<Movie> movieList;
	private ArrayList<Movie> moviePreSaleList;
	private ArrayList<Theater> theaterList;
	private ArrayList<ShowTime> showTimeList;
	private ArrayList<Ticket> ticketList;
	private ArrayList<Credit> couponList;

	/**
	 * Constructor with a user passed in
	 *
	 * @param userInstance
	 */
	public ModelController(User userInstance) {
		super();
		this.userInstance = userInstance;
		movieList = new ArrayList<Movie>();
		moviePreSaleList = new ArrayList<Movie>();
		theaterList = new ArrayList<Theater>();
		showTimeList = new ArrayList<ShowTime>();
		ticketList = new ArrayList<Ticket>();
		this.couponList = new ArrayList<Credit>();

	}


	public User getUserInstance() {
		return userInstance;
	}

	/**
	 * Method to check the type of user. Return true if the user is an ordinary user
	 *
	 * @return true if the user is an ordinary user
	 */
	public boolean isOrdinaryUser() {
		if(userInstance instanceof OrdinaryUser) {
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 *
	 * Login method - check username and password in database
	 *
	 * @param email is the email of the user
	 * @param password is the password of the user
	 * @return true if user is found and password is correct
	 */
	public boolean login(String email, String password) {

		int id, cardCVV;
		String firstName, lastName, address, cardFullName,  cardNum, cardExp;
		ArrayList<String> creditCodes = new ArrayList<String> ();

		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			String statement = "SELECT * FROM REGISTERED_USERS WHERE Email = ? AND Password = ?;";
			PreparedStatement prepStatement = conn.prepareStatement(statement);
			prepStatement.setString(1, email);
			prepStatement.setString(2, password);
			ResultSet resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {

				id = resultSet.getInt("UserID");
				firstName = resultSet.getString("Fname");
				lastName = resultSet.getString("Lname");
				cardNum = resultSet.getString("CreditCardNo");
				address = "";
				cardFullName = firstName + lastName;
				cardExp = "";
				cardCVV = 0;

				RegisteredUser.RegisteredInstance();
				RegisteredUser temp = (RegisteredUser) RegisteredUser.getOnlyInstance();
				temp.loadUserinfo(id, firstName, lastName, email, address, cardFullName, cardNum, cardExp, cardCVV, creditCodes, password);
				conn.close();
				return true;

			}
			conn.close();
		} catch (SQLException e) {
			return false;
		}

		return false;

	}

	/**
	 * Takes in customer's email and password and returns true if the email is unique, confirming registration
	 *
	 *
	 * @param firstName is the first name of the user
	 * @param lastName is the last name of the user
	 * @param creditCardNumber is the credit card number of the user
	 * @param email is the email of the user
	 * @param password is the password of the user
	 * @return true if the email is unique
	 */
	public boolean registerUser(String firstName, String lastName, String creditCardNumber, String email, String password) {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn.prepareStatement(
					"INSERT INTO REGISTERED_USERS(FName, LName, CreditCardNo, MembershipStart, Email, Password) VALUES(?, ?, ?, ?, ?, ?);");
			prepStatement.setString(1, firstName);
			prepStatement.setString(2, lastName);
			prepStatement.setString(3, creditCardNumber);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			prepStatement.setString(4, dtf.format(now).toString());
			prepStatement.setString(5, email);
			prepStatement.setString(6, password);
			prepStatement.executeUpdate();

			conn.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Searches for all movies in the database that are currently open
	 *
	 * @return an ArrayList of all movies that are currently open
	 */
	public ArrayList<Movie> getMovieList() {
        movieList = new ArrayList<Movie>();
        try {
            Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM MOVIE WHERE OpeningDate >= ?");
            // TODO : Change to current date not in milliseconds
            prepStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                Movie movie = new Movie(
                        resObj.getInt("MovieID"),
                        resObj.getString("Title"),
                        resObj.getDate("OpeningDate"),
                        resObj.getString("Description"),
                        resObj.getInt("Runtime")
                );
                movieList.add(movie);
            }
        } catch (Exception sqlException) {
            return null;
        }
		return movieList;
	}


	/**
     * Filters the movie list only returning the names of the movies
     *
     * @return ArrayList of all movie titles
     */
	public ArrayList<String> getMovieNameList() {
		ArrayList<String> movieNameList = new ArrayList<String>();
		ArrayList<Movie> movies = getMovieList();
		for (Movie m: movies) {
			movieNameList.add(m.getName());
		}	
		return movieNameList;
	}

	public ArrayList<Movie> getMoviePreSaleList() {
		return moviePreSaleList;
	}

	public ArrayList<Theater> getTheaters() {
		return this.theaterList;
	}

	/**
     * Searches for all theaters in the database, builds theater objects and adds them to the theater list
     *
     * @return an ArrayList of all theaters
     */
	public ArrayList<Theater> getTheaterList() {
		theaterList = new ArrayList<Theater>();

        try {
            Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM THEATRE");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                Theater theatre = new Theater(
                        resObj.getInt("TheatreID"),
                        resObj.getString("TheatreName"),
                        resObj.getInt("Capacity")
                );
                theaterList.add(theatre);
            }
        } catch (Exception sqlException) {
			return null;
        }
		return theaterList;
	}

	/**
     * Filters the theater list only returning the names of the theaters
     *
     * @return an ArrayList of all theater names
     */
	public ArrayList<String> getTheaterNameList() {
		ArrayList<String> theaterNameList = new ArrayList<String>();
		ArrayList<Theater> theaters = getTheaterList();
		for (Theater t: theaters) {
			theaterNameList.add(t.getNameId());
		}	
		return theaterNameList;
	}

	/**
	 * Searches for all a movie title and theater in the database
	 * @param selectedMovie the movie title
	 * @param selectedTheater the theater name
	 * @return
	 */
	public ArrayList<ShowTime> getShowTimeList(String selectedMovie, String selectedTheater) {
		showTimeList = new ArrayList<ShowTime>();
		try {
			Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = connObj
					.prepareStatement(
							"SELECT M.*, T.*, S.* " +
									"FROM SHOWTIME AS S, MOVIE AS M, THEATRE AS T " +
									"WHERE S.MovieID = M.MovieID AND S.TheatreID = T.TheatreID " +
									"AND M.Title = ? " +
									"AND T.TheatreName = ?;");
			prepStatement.setString(1, selectedMovie);
			prepStatement.setString(2, selectedTheater);

			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				Movie movie = new Movie(
						resObj.getInt("M.MovieID"),
						resObj.getString("M.Title"),
						resObj.getDate("M.OpeningDate"),
						resObj.getString("M.Description"),
						resObj.getInt("Runtime"));

				Theater theatre = new Theater(
						resObj.getInt("T.TheatreID"),
						resObj.getString("T.TheatreName"),
						resObj.getInt("T.Capacity"));

				ShowTime showtime = new ShowTime(
						resObj.getInt("S.ShowtimeID"),
						resObj.getString("S.StartTime"),
						resObj.getString("S.EndTime"),
						resObj.getString("S.ShowDate"),
						movie,
						theatre
				);
				showTimeList.add(showtime);
			}
		} catch (Exception sqlException) {
			return null;
		}
		return showTimeList;
	}

	/**
	 * Filters teh showtime list only returning the showtimes for the selected movie and theater
	 *
	 * @param selectedMovie the movie title
	 * @param selectedTheater the theater name
	 * @return an ArrayList of all showtimes for the selected movie and theater
	 */
	public ArrayList<String> getAvailableShowTimesList(String selectedMovie, String selectedTheater) {
		ArrayList<String> showTimesList = new ArrayList<String>();
		ArrayList<ShowTime> showtimes = getShowTimeList(selectedMovie, selectedTheater);
		for (ShowTime s: showtimes) {
			showTimesList.add("Start Time: " + s.getStartTime() + ", End Time: " + s.getEndTime() + ", Show Date: " + s.getShowDate());
		}
		return showTimesList;
	}

	/**
	 * Search for a specific showtime in the database by movie title, theater name, and time
	 * @param selectedMovie the movie title
	 * @param selectedTheater the theater name
	 * @param selectedShowTime the showtime start and time to be parsed
	 * @return showtimeID if found, 0 if not found
	 */
	public int searchShowTimeID(String selectedMovie, String selectedTheater, String selectedShowTime) {
		String startTime = selectedShowTime.substring(12, 19);
		String endTime = selectedShowTime.substring(32, 39);
		String showDate = selectedShowTime.substring(53, 63);
		try {
			Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = connObj
					.prepareStatement(
							"SELECT S.ShowtimeID " +
									"FROM SHOWTIME S, MOVIE M, THEATRE T " +
									"WHERE S.MovieID = M.MovieID AND S.TheatreID = T.TheatreID " +
									"AND M.Title = ? " +
									"AND T.TheatreName = ? " +
									"AND S.StartTime = ? " +
									"AND S.EndTime = ? " +
									"AND S.ShowDate = ?;");
			prepStatement.setString(1, selectedMovie);
			prepStatement.setString(2, selectedTheater);
			prepStatement.setString(3, startTime);
			prepStatement.setString(4, endTime);
			prepStatement.setString(5, showDate);
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				int result = resObj.getInt("S.ShowtimeID");
				return result;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		return 0;
	}

	/**
	 * Returns all seats for a specific showtime to populate the seat selection menu
	 *
	 * @param selectedShowTimeID the showtimeID to be searched
	 * @return an ArrayList of all seats for the selected showtime
	 */
	public ArrayList<Integer> getSeatsStatuses(int selectedShowTimeID) {
		ArrayList<Integer> seatStatusList = new ArrayList<Integer>();
		try {
			Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = connObj
					.prepareStatement(
							"SELECT *, Presale + Occupied AS SeatStatus " +
									"FROM SEAT_INSTANCE AS SI, SEAT_CHART AS SC " +
									"WHERE SI.SeatID = SC.SeatID AND SI.ShowtimeID = ? " +
									"ORDER BY SC.SeatCol, SC.SeatRow;");
			prepStatement.setInt(1, selectedShowTimeID);

			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				int status = resObj.getInt("SeatStatus");
				seatStatusList.add(status);
			}
		} catch (Exception sqlException) {
			return null;
		}
		return seatStatusList;
	}



	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}

	public ArrayList<Credit> getCouponList() {
		return couponList;
	}

	/**
	 * Called when the database is updated to keep a list in memory of all movies and theaters
	 *
	 */
	public void loadModelsQuery() {

		this.movieList.clear();
		this.moviePreSaleList.clear();
		this.theaterList.clear();
		this.showTimeList.clear();
		this.ticketList.clear();
		this.couponList.clear();

		this.loadTheaters();
		this.loadSeats();
		this.loadMovies();
		this.loadShowTime();
		this.loadCredits();

	}

	/**
	 * Gets all registered users from the database to be able to email them.
	 * Note we have not implemented the feature to email users yet but this would help with that.
	 *
	 * @return an ArrayList of all subscriber emails
	 */
	public ArrayList<String> getAllSubscribers(){
		ArrayList<String> temp = new ArrayList<String>();
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT * FROM REGISTERED_USERS");
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {

				temp.add(resObj.getString("Email"));
			}
		} catch (Exception sqlException) {
			return null;
		}
		return temp;
	}


	/**
	 * Get all coupons from the database to populate our model
	 *
	 */
	private void loadCredits() {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT * FROM COUPONS");
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {

				this.couponList.add( new Credit(resObj.getString("CouponCode"), resObj.getInt("CouponValue")));
			}
		} catch (Exception sqlException) {
			return;
		}

	}

	/**
	 * Get all theaters from the database to populate our model
	 */
	private void loadTheaters() {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT * FROM THEATRE");
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				Theater theatre = new Theater(
						resObj.getInt("TheatreID"),
						resObj.getString("TheatreName"),
						resObj.getInt("Capacity")
				);
				theaterList.add(theatre);
			}
		} catch (Exception sqlException) {
			return;
		}

	}

	/**
	 * Get all movies from the database to populate our model
	 */
	private void loadMovies() {

		// get movies that are not pre-sale
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT * FROM MOVIE WHERE OpeningDate >= ? AND MovieStatus = 'AVAILABLE'");
			// TODO : Change to current date not in milliseconds
			prepStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				Movie movie = new Movie(
						resObj.getInt("MovieID"),
						resObj.getString("Title"),
						resObj.getDate("OpeningDate"),
						resObj.getString("Description"),
						resObj.getInt("Runtime")
				);
				movieList.add(movie);
			}
		} catch (Exception sqlException) {
			return;
		}

		// get movies that are pre sale
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT * FROM MOVIE WHERE OpeningDate < ? AND MovieStatus = 'AVAILABLE'");
			prepStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				Movie movie = new Movie(
						resObj.getInt("MovieID"),
						resObj.getString("Title"),
						resObj.getDate("OpeningDate"),
						resObj.getString("Description"),
						resObj.getInt("Runtime")
				);
				moviePreSaleList.add(movie);
			}
		} catch (Exception sqlException) {
			return;
		}
	}

	/**
     *  Searches through our movieList to check for an id matching the given id
	 *
	 * @param id is the id of the movie we are searching for
	 * @return the Movie object
     */
	public Movie getMovie(int id) {
		for(int i = 0; i< this.movieList.size();i++) {
			if(this.movieList.get(i).getId()==id) {
				return this.movieList.get(i);
			}
		}
		for(int i = 0; i< this.moviePreSaleList.size();i++) {
			if(this.moviePreSaleList.get(i).getId()==id) {
				return this.moviePreSaleList.get(i);
			}
		}
		return null;
	}


	/**
	 * Searches through our TheaterList to check for an id matching the given id
	 *
	 * @param id is the id of the theater we are searching for
	 * @return the Theater object
	 */
	public Theater getTheater(int id) {
		for(int i = 0; i< this.theaterList.size();i++) {
			if(this.theaterList.get(i).getId()==id) {
				return this.theaterList.get(i);
			}
		}
		return null;
	}


	/**
	 * Get all shows from the database to populate our model
	 *
	 */
	private void loadShowTime() {
		int showTimeID, movieID, theatreID;
		String startTime, endTime;
		Date date;
		Movie tempM;
		Theater tempT;

		try {

			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"SELECT S.* FROM SHOWTIME S " +
									"JOIN MOVIE M ON S.MovieID = M.MovieID " +
									"WHERE M.MovieStatus = 'AVAILABLE'");
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				showTimeID = resObj.getInt("ShowtimeID");
				movieID = resObj.getInt("MovieID");
				theatreID = resObj.getInt("TheatreID");
				startTime = resObj.getString("StartTime");
				endTime = resObj.getString("EndTime");
				date = resObj.getDate("ShowDate");
				tempM = this.getMovie(movieID);
				tempT = this.getTheater(theatreID);
				ShowTime stemp = new ShowTime(showTimeID, startTime, endTime, date.toString(), tempM, tempT);

				this.showTimeList.add(stemp);
			}

		} catch (Exception sqlException) {
			return;
		}
	}

	/**
	 * Returns a list of the seats in a searchable format
	 * @param theaterId is the id of the theater that has the seats
	 * @return a list of the seats in HashMap format
	 */
	private HashMap<String, Boolean> getSeats(String theaterId){
		String id = "";

		boolean occupy = false;
		HashMap<String, Boolean> test = new HashMap<String, Boolean>();

		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SEAT_CHART WHERE THEATREID = " + theaterId);
			ResultSet seat;
			while(rs.next()){
				id = rs.getString("SEATID");

				seat = stmt2.executeQuery("SELECT * FROM SEAT_INSTANCE WHERE SEATID = " + id);
				while(seat.next()){
					if(seat.getString("Occupied") == "1") {
						occupy = true;
					} else {
						occupy = false;
					}
				}
				test.put(id + ":" + rs.getString("SEATROW")+ ":" + rs.getString("SEATCOL"), occupy);
			}
		} catch (SQLException e) {
			return null;
		}
		return test;
	}


	/**
	 * Loads the seats from a previous query into our model
	 */
	private void loadSeats() {

		for(int j = 0;j<this.theaterList.size();j++) {
			Theater tt = this.theaterList.get(j);
			tt.createAllSeats(this.getSeats(String.valueOf(tt.getId())));
			ArrayList<Seat> seatList = tt.getSeatList();
			for (int i = 0; i < seatList.size(); i++) {
				System.out.println("Seat: " + seatList.get(i).getId() + " row: " + seatList.get(i).getRow() + " column: " + seatList.get(i).getCol() + " status: " + seatList.get(i).isStatus());
			}
		}
	}

	/**
	 * Create a ticket purchase for a given showtime and seat
	 * Updates the database with teh new ticket, state of the seat, and creates a new payment line item
	 *
	 * @param showTimeId is the id of the showtime
	 * @param seatInstanceID is the id of the seat instance
	 * @param price is the price of the ticket
	 * @param ticketStatus is the state of the ticket, occupied or not
	 * @param email is the email of the customer to email their ticket
	 * @param creditCard is the credit card number of the customer
	 */
	public boolean createTicket(int showTimeId, int seatInstanceID, int price, String ticketStatus, String email, String creditCard) {
		String statement = "";
		PreparedStatement prepStatement;
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			Statement stmt = conn.createStatement();

			statement = "UPDATE SEAT_INSTANCE SET Occupied = TRUE WHERE ShowtimeID =" + showTimeId +" AND SeatInstanceID = " +seatInstanceID + ";";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.executeUpdate();

			statement =  "INSERT INTO TICKET(SeatInstanceID, Price, TicketStatus, Email)  VALUES ("+seatInstanceID+", "+price+", '"+ticketStatus+"', '"+email+"');";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.executeUpdate();

			statement =  "INSERT INTO PAYMENT (TicketID, CreditCardNo)\r\n"
					+ "VALUES (\r\n"
					+ "        (SELECT TicketID\r\n"
					+ "            FROM TICKET\r\n"
					+ "            WHERE SeatInstanceID = "+seatInstanceID+"\r\n"
					+ "            ), '"+creditCard+"'\r\n"
					+ "       );";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.executeUpdate();

		} catch (SQLException e) {
			return false;
		}
		this.loadModelsQuery();
		return true;
	}

	/**
	 * Create a ticket purchase for a given showtime and seat
	 * Updates the database with teh new ticket, state of the seat, and creates a new payment line item
	 * Takes a coupon code and checks if it is valid, how much it is worth, and updates that value
	 *
	 * @param showTimeId is the id of the showtime
	 * @param seatInstanceId is the id of the seat instance
	 * @param price is the price of the ticket
	 * @param ticketStatus is the state of the ticket, occupied or not
	 * @param email is the email of the customer to email their ticket
	 * @param creditCard is the credit card number of the customer
	 * @param couponCode is the coupon code to be used
	 */
	public boolean createTicket(int showTimeId, int seatInstanceId, int price, String ticketStatus, String email, String creditCard, String couponCode) {
		// update seat instance
		String statement = "";
		PreparedStatement prepStatement;

		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());

			// check coupon code database:
			statement = "SELECT CouponValue FROM COUPONS WHERE CouponCode = ? AND ExpiryDate > CURRENT_DATE;";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setString(1, couponCode);
			ResultSet resObj = prepStatement.executeQuery();
			int couponValue = 0;

			if(resObj.next()) {
				couponValue = resObj.getInt("CouponValue");

				// update coupon value to couponValue - price or 0 if couponValue - price < 0
				statement = "UPDATE COUPONS SET CouponValue = ? WHERE CouponCode = ?";
				prepStatement = conn.prepareStatement(statement);
				prepStatement.setInt(1, Math.max(0, couponValue - price));
				prepStatement.setString(2, couponCode);
				prepStatement.executeUpdate();
			}

			// update seat instance
			statement = "UPDATE SEAT_INSTANCE SET Occupied = TRUE WHERE ShowtimeID = ? AND SeatInstanceID = ?";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setInt(1, showTimeId);
			prepStatement.setInt(2, seatInstanceId);
			prepStatement.executeUpdate();

			// insert ticket
			statement = "INSERT INTO TICKET (SeatInstanceID, Price, TicketStatus, Email) VALUES (?, ?, ?, ?)";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setInt(1, seatInstanceId);
			prepStatement.setInt(2, price);
			prepStatement.setString(3, ticketStatus);
			prepStatement.setString(4, email);
			prepStatement.executeUpdate();

			// get the last movie id inserted to use for all showtimes
			prepStatement = conn.prepareStatement("SELECT LAST_INSERT_ID() AS 'TicketID';");
			resObj = prepStatement.executeQuery();
			int ticketId = 0;
			while (resObj.next()) {
				ticketId = resObj.getInt("TicketID");
			}

			// insert payment
			statement = "INSERT INTO PAYMENT (TicketID, CreditCardNo, Amount) VALUES (?, ?, ?)";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setInt(1, ticketId);
			prepStatement.setString(2, creditCard);
			prepStatement.setInt(3, Math.max(0, price - couponValue));

			this.loadModelsQuery();

		} catch (SQLException ex) {
			return false;
		}

		return true;
	}

	/**
	 * Add a movie to the database by the admin.
	 * The times and dates are automatically added to the database based on the start and end dates and movie length
	 *
	 * @param movieTitle is the title of the movie
	 * @param theaterId is the id of the theater
	 * @param cleaningTime is the time it takes to clean a theater
	 * @param openingDateString is the date the movie is opening
	 * @param endDateString is the date the movie is closing
	 */
	public void addMovies(String movieTitle, int theaterId, int cleaningTime, String openingDateString, String endDateString) {
		// hard coding for now
		int runtime = 150;
		String description = "";

		// set a opening time of the theater for 8 am
		String openingTime = "08:00:00";
		// last showing time of 10 pm
		String lastShowing = "22:00:00";


		// get all dates between openingDate and endDate
		List<String> dates = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(openingDateString);
		LocalDate endDate = LocalDate.parse(endDateString);
		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			dates.add(startDate.toString());
			startDate = startDate.plusDays(1);
		}

		// get all times between openingTime and lastShowing base on movie runtime + cleaning time
		List<String> times = new ArrayList<>();
		LocalTime startTime = LocalTime.parse(openingTime);
		LocalTime endTime = LocalTime.parse(lastShowing);
		while (startTime.isBefore(endTime) && startTime.plusMinutes(1).isAfter(LocalTime.parse(openingTime))) {
			times.add(startTime.toString());
			startTime = startTime.plusMinutes(runtime + cleaningTime);
		}

		Connection conn;
		String statement = "";
		PreparedStatement prepStatement;
		try {
			conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());

			// initial insert of the movie
			statement = "INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)\r\n"
					+ "VALUES(?, ?, ?, ?);";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setString(1, movieTitle);
			prepStatement.setString(2,openingDateString);
			prepStatement.setString(3, description);
			prepStatement.setInt(4, runtime);
			prepStatement.executeUpdate();

			// get the last movie id inserted to use for all showtimes
			prepStatement = conn.prepareStatement("SELECT LAST_INSERT_ID() AS 'MovieID';");
			ResultSet resObj = prepStatement.executeQuery();
			int movieId = 0;
			while (resObj.next()) {
				movieId = resObj.getInt("MovieID");
			}

			for (String date : dates) {
				for (String time : times) {
					// insert showtime
					statement ="INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)\r\n"
							+ "VALUES(?, ?, ?, ?, ?);";
					prepStatement = conn.prepareStatement(statement);
					prepStatement.setInt(1, movieId);
					prepStatement.setInt(2, theaterId);
					prepStatement.setString(3, time);
					prepStatement.setString(4, String.valueOf(LocalTime.parse(time).plusMinutes(runtime)));
					prepStatement.setString(5, date);
					prepStatement.executeUpdate();
				}
			}

			// get all showtimes with the movie id
			statement = "SELECT ShowtimeID FROM SHOWTIME WHERE MovieID = ?;";
			prepStatement = conn.prepareStatement(statement);
			prepStatement.setInt(1, movieId);
			ResultSet resultSet = prepStatement.executeQuery();
			ArrayList<Integer> showtimeIds = new ArrayList<>();
			while (resultSet.next()) {
				showtimeIds.add(resultSet.getInt("ShowtimeID"));
			}

			// insert into SEAT_INSTANCE for all Showtime
			for (int showtimeId : showtimeIds) {
				statement = "INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)\r\n"
						+ "SELECT SeatID, ShowtimeID FROM (\n" +
						"SELECT ShowtimeID FROM SHOWTIME\n" +
						"JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID\n" +
						"WHERE T.TheatreID = ?\n" +
						"    AND ShowtimeID = ?\n" +
						"    ) ST\n" +
						"CROSS JOIN (\n" +
						"    SELECT SeatID FROM SEAT_CHART\n" +
						"    WHERE TheatreID = ?\n" +
						"    ) CJ;";
				prepStatement = conn.prepareStatement(statement);
				prepStatement.setInt(1, theaterId);
				prepStatement.setInt(2, showtimeId);
				prepStatement.setInt(3, theaterId);
				prepStatement.executeUpdate();
			}

			conn.close();

			// send a response to all of our subscribers
			MovieNotification subject = new MovieNotification();
			Subscribers ob1 = new Subscribers(subject);
			subject.addObserver(ob1);
			subject.notifyAllObservers(movieTitle + " is pre-selling tickets now! ShowDates are: " + dates);

		} catch (SQLException e) {
			return;
		}

	}

	/**
	 * Admin method to cancel a movie
	 * @param movieId is the id of the movie to be cancelled
	 */
	public void cancelMovie(int movieId) {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());

			// this is all that needs to be done, queries that pull movies need to only look at movies that are not cancelled
			PreparedStatement prepStatement = conn
					.prepareStatement(
							"UPDATE MOVIE SET MovieStatus = 'CANCELLED' WHERE MovieID = ?;");
			prepStatement.setInt(1, movieId);
			prepStatement.executeUpdate();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
	}

	/**
	 * Refunds a ticket, updates the state of the seat and ticket within the database
	 * Calls issueCoupon at the end
	 *
	 * @param ticketId is the id of the ticket to be refunded
	 * @param subscriber is a boolean that determines if the refund is being done by a subscriber
	 */
	public String refundTicket(int ticketId, boolean subscriber) {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());

			// update ticket status to refunded
			PreparedStatement prepStatement = conn.prepareStatement(
					"UPDATE TICKET SET TicketStatus = 'REFUNDED' WHERE TicketID = ?;");
			prepStatement.setInt(1, ticketId);
			prepStatement.executeUpdate();


			// update seat instance status to available, presale to false by default
			prepStatement = conn.prepareStatement(
					"UPDATE SEAT_INSTANCE JOIN TICKET ON SEAT_INSTANCE.SeatInstanceID = TICKET.SeatInstanceID " +
							"SET Occupied = FALSE AND Presale = FALSE WHERE TicketID = ?;");
			prepStatement.setInt(1, ticketId);
			prepStatement.executeUpdate();

			// call issue coupon
			Credit returned_credit =  issueCoupon(ticketId, subscriber);
			if (returned_credit != null)
				return "Credit code: " + returned_credit.getCode() + " Amount: $" + returned_credit.getBalance() / 100.0;

		} catch (Exception sqlException) {
			return null;
		}
		return null;
	}

	/**
	 * Issues a coupon after checking if the ticket is a subscriber or not, if the cancellation is within 72 hours
	 *
	 * @param ticketId is the id of the ticket to be refunded
	 * @param subscriber is a boolean that determines if the refund is being done by a subscriber
	 * @return is a Credit object which contains the amount of the coupon and the code
	 */
	public Credit issueCoupon(int ticketId, boolean subscriber) {
		double multiplier = 1;

		// if subscriber is false, multiplier is 0.85
		if (!subscriber) {
			multiplier = 0.85;
		}

		try {
			// get showdate and time and ticket price
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn.prepareStatement(
					"SELECT ShowDate, StartTime, Price\n" +
							"FROM SHOWTIME\n" +
							"    JOIN SEAT_INSTANCE SI on SHOWTIME.ShowtimeID = SI.ShowtimeID\n" +
							"    JOIN TICKET T on SI.SeatInstanceID = T.SeatInstanceID\n" +
							"WHERE TicketID = ?");
			prepStatement.setInt(1, ticketId);
			ResultSet resultSet = prepStatement.executeQuery();
			String showDate = "";
			String startTime = "";
			int price = 0;
			while (resultSet.next()) {
				showDate = resultSet.getString("ShowDate");
				startTime = resultSet.getString("StartTime");
				price = resultSet.getInt("Price");
			}

			// check if showDate and startTime are within 72 hours
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime showDateTime = LocalDateTime.parse(showDate + " " + startTime, formatter);
			Duration duration = Duration.between(now, showDateTime);
			long hours = duration.toHours();


			// get no coupon discount if within 72 hours
			if (hours < 72) {
				return null;
			}

			int couponAmount = (int) (price * multiplier);

			// create COUPON in database

			prepStatement = conn.prepareStatement(
					"INSERT INTO COUPONS(CouponCode, CouponValue, TicketID, ExpiryDate)\n" +
							"VALUES((SELECT LEFT(MD5(RAND()), 15)), ?, ?, NOW() + INTERVAL 1 YEAR);");

			prepStatement.setInt(1, couponAmount);
			prepStatement.setInt(2, ticketId);
			prepStatement.executeUpdate();

			// get coupon code

			prepStatement = conn.prepareStatement(
					"SELECT CouponCode FROM COUPONS WHERE TicketID = ?;");
			prepStatement.setInt(1, ticketId);
			resultSet = prepStatement.executeQuery();
			String couponCode = "";
			while (resultSet.next()) {
				couponCode = resultSet.getString("CouponCode");
			}

			// create credit object
			Credit credit = new Credit(couponCode, couponAmount);
			System.out.println(credit);
			return credit;
		}
		catch (Exception sqlException) {
			return null;
		}
	}

	/**
	 * Checks the database for a chosen seat within a showtime
	 * @param showTimeId is the id of the showtime
	 * @param chosenSeat is the seat to be checked
	 * @return the id of the seat instance
	 */
	public int searchSeatInstanceID(int showTimeId, String chosenSeat) {
		String seatCol = chosenSeat.substring(0,1);
		int seatRow = Integer.parseInt(chosenSeat.substring(1, 2));
		try {
			Connection connObj = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = connObj
					.prepareStatement(
							"SELECT SI.SeatInstanceID " +
									"FROM SEAT_INSTANCE AS SI, SEAT_CHART AS SC " +
									"WHERE SI.SeatID = SC.SeatID " +
									"AND SI.ShowtimeID = ? " +
									"AND SC.SeatCol = ? " +
									"AND SC.SeatRow = ?;");
			prepStatement.setInt(1, showTimeId);
			prepStatement.setString(2, String.valueOf(seatCol));
			prepStatement.setInt(3, seatRow);
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				int result = resObj.getInt("SI.SeatInstanceID");
				return result;
			}
		} catch (Exception sqlException) {
			return 0;
		}
		return 0;
	}

}
