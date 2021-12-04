package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import config.Configuration;

public class TestModel {
	
	public void addMovies(String movieTitle, String openingDate, String movieDescription,int runTime, String theaterName, String startTime, String endTime, String showDate ) {
		Connection conn;
		String statement = "";
		PreparedStatement prepStatement;
		try {
			conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
			
			
			statement = "INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)\r\n"
                    + "VALUES('"+movieTitle+"', '"+openingDate+"', '"+movieDescription+"', "+runTime+");";
			prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();
            
            statement ="INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)\r\n"
            		+ "VALUES (\r\n"
            		+ "        (SELECT MovieID\r\n"
            		+ "            FROM MOVIE\r\n"
            		+ "            WHERE Title = '"+movieTitle+"'\r\n"
            		+ "            ),\r\n"
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
            		+ "            WHERE TheatreName = '\"+theaterName+\"')\r\n"
            		+ "    ) CJ;";
            
            
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	

	
	
	
	public static void main(String[] args) {
		TestModel a = new TestModel();
		
		//a.addMovies("TEST Movie", "2021-01-01", "Titanic, the movie", 100, "Black Theatre", "08:00:00", "11:14:00", "2021-02-02");
		
		
	}
}
