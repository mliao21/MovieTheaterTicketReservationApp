package ensf614project.src.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import config.Configuration;
import ensf614project.src.model.*;


public class ModelController {
	private User userInstance;
	private ArrayList<Movie> movieList;
	private ArrayList<Movie> moviePreSaleList;
	private ArrayList<Theater> theaterList;
	private ArrayList<ShowTime> showTimeList;
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

	private ArrayList<Ticket> ticketList;
	public ModelController(User userInstance) {
		super();
		this.userInstance = userInstance;
		movieList = new ArrayList<Movie>();
		moviePreSaleList = new ArrayList<Movie>();
		theaterList = new ArrayList<Theater>();
		showTimeList = new ArrayList<ShowTime>();
		ticketList = new ArrayList<Ticket>();
		
	}
	
	public void loadModelsQuery() {
		this.loadTheaters();
		this.loadSeats();
		this.loadMovies();
		this.loadShowTime();
		
		
		
		
	}
	
	public ArrayList<String> getAllSubscribers(){
		ArrayList<String> temp = new ArrayList<String>();
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
                    .prepareStatement(
                            "SELECT * FROM registered_users");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
            	
            	temp.add(resObj.getString("Email"));
            }
			
            
           
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
		return temp;
		
		
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
            sqlException.printStackTrace();
        }
		
		try {
			Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			PreparedStatement prepStatement = conn
                    .prepareStatement(
                            "SELECT * FROM MOVIE WHERE OpeningDate < ?");
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
	
	
	
	
	

}
