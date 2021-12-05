package ensf614project.src.controller;
//package ensf614project.src.controller;
//import ensf614project.src.config.Configuration;
//import ensf614project.src.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.Credit;
import ensf614project.src.model.Movie;
import ensf614project.src.model.MovieNotification;
import ensf614project.src.model.Seat;
import ensf614project.src.model.ShowTime;
import ensf614project.src.model.Subscribers;
import ensf614project.src.model.Theater;
import ensf614project.src.model.Ticket;
import ensf614project.src.model.User;

public class ModelController {
	private User userInstance;
	private ArrayList<Movie> movieList;
	private ArrayList<Movie> moviePreSaleList;
	private ArrayList<Theater> theaterList;
	private ArrayList<ShowTime> showTimeList;
	private ArrayList<Ticket> ticketList;
	private ArrayList<Credit> couponList;
	
	
	
	public User getUserInstance() {
		return userInstance;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public ArrayList<Movie> getMoviePreSaleList() {
		return moviePreSaleList;
	}

	public ArrayList<Theater> getTheaterList() {
		return theaterList;
	}

	public ArrayList<ShowTime> getShowTimeList() {
		return showTimeList;
	}

	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}
	
	

	
	public ArrayList<Credit> getCouponList() {
		return couponList;
	}

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
            sqlException.printStackTrace();
        }
		return temp;
		
		
	}
	
	private void loadCredits() {
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
                    .prepareStatement(
                            "SELECT * FROM coupons");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
            	
            	this.couponList.add( new Credit(resObj.getString("CouponCode"), resObj.getInt("CouponValue")));
            }


        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
		
	}
	
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
            sqlException.printStackTrace();
        }
		
	}
	
	private void loadMovies() {
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
            sqlException.printStackTrace();
        }
		
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
            sqlException.printStackTrace();
        }
		
		
	}
	
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
	
	public Theater getTheater(int id) {
		for(int i = 0; i< this.theaterList.size();i++) {
			if(this.theaterList.get(i).getId()==id) {
				return this.theaterList.get(i);
			}
		}
		return null;
	}
	
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
                            "SELECT * FROM SHOWTIME");
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
            	ShowTime stemp = new ShowTime(showTimeID, startTime, endTime,tempM, tempT);
            	
            	
            	this.showTimeList.add(stemp);
            }
			
           
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return test;
	}
	
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
	
	public void createTicket(int showTimeId, int seatInstanceID, int price, String ticketStatus, String email, String creditCard) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.loadModelsQuery();

	}
	
	
	public void addMovies(String movieTitle, String openingDate, String movieDescription, int runTime, String theaterName, String startTime, String endTime, String showDate ) {
		Connection conn;
		String statement = "";
		PreparedStatement prepStatement;
		try {
			conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			
			
			statement = "INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)\r\n"
                    + "VALUES('"+movieTitle+"', '"+openingDate+"', '"+movieDescription+"', "+runTime+");";
			prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();

			prepStatement = conn.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet resObj = prepStatement.executeQuery();
			resObj.next();
			int movieId = resObj.getInt(1);

            statement ="INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)\r\n"
            		+ "VALUES (\r\n"
					+ "        '"+movieId+"',\r\n"
					+ "        (SELECT TheatreID\r\n"
					+ "            FROM THEATRE\r\n"
					+ "            WHERE TheatreName = '" + theaterName+"'\r\n"
					+ "            ),\r\n"
					+ "        '"+startTime+"',\r\n"
            		+ "        '"+endTime+"',\r\n"
            		+ "        '"+showDate+"');";
            prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();
            
            
            statement = "INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)\r\n"
            		+ "SELECT SeatID, ShowtimeID FROM (\r\n"
            		+ "SELECT ShowtimeID FROM SHOWTIME\r\n"
            		+ "JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID\r\n"
            		+ "WHERE T.TheatreID = (SELECT TheatreID\r\n"
            		+ "            FROM THEATRE\r\n"
            		+ "            WHERE TheatreName = '"+theaterName+"')) ST\r\n"
            		+ "CROSS JOIN (\r\n"
            		+ "    SELECT SeatID FROM SEAT_CHART\r\n"
            		+ "    WHERE TheatreID = (\r\n"
            		+ "        SELECT TheatreID\r\n"
            		+ "            FROM THEATRE\r\n"
            		+ "            WHERE TheatreName = '"+theaterName+"')\r\n"
            		+ "    ) CJ;";
            
            prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();
            
            MovieNotification subject = new MovieNotification();
            Subscribers ob1 = new Subscribers(subject);
            subject.addObserver(ob1);
            subject.notifyAllObservers(movieTitle + " is pre-selling tickets now! ShowDate is: " + showDate);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.loadModelsQuery();

	}
}
