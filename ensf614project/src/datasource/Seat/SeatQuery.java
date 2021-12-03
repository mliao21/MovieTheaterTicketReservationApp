package ensf614project.src.datasource.Seat;

import ensf614project.src.datasource.Seat.SeatObject;
import ensf614project.src.datasource.Showtime.ShowtimeObject;
import ensf614project.src.datasource.Showtime.ShowtimeQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class SeatQuery {
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/ensf614_movie_theatre";
    static final String JDBC_USER = "mock_user";
    static final String JDBC_PASS = "password";

    public void assignShowtimeSeats(ShowtimeObject showtime) {
//        showtime.getTheatre().setSeats();
        HashMap<String, SeatObject> seats = new HashMap<>();
        int movieID = showtime.getMovie().getId();
        int theatreID = showtime.getTheatre().getId();
        int showtimeID = showtime.getId();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT SeatInstanceID, SC.SeatCol, SC.SeatRow, Presale, Occupied FROM SHOWTIME ST " +
                                    "JOIN THEATRE T on ST.TheatreID = T.TheatreID " +
                                    "JOIN SEAT_INSTANCE SI on ST.ShowtimeID = SI.ShowtimeID " +
                                    "JOIN SEAT_CHART SC on SI.SeatID = SC.SeatID " +
                                    "JOIN MOVIE M on ST.MovieID = M.MovieID " +
                                    "WHERE M.MovieID = ? " +
                                    "AND T.TheatreID = ? " +
                                    "AND ST.ShowtimeID = ?");
            prepStatement.setInt(1, movieID);
            prepStatement.setInt(2, theatreID);
            prepStatement.setInt(3, showtimeID);

            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                int seatID = resObj.getInt("SeatInstanceID");
                String seatCol = resObj.getString("SeatCol");
                Integer seatRow = resObj.getInt("SeatRow");
                boolean presale = resObj.getBoolean("Presale");
                boolean occupied = resObj.getBoolean("Occupied");
                SeatObject seat = new SeatObject(seatID, seatRow, seatCol.charAt(0), presale, occupied);
                seats.put(seatCol+seatRow.toString(), seat);
            }
            showtime.getTheatre().setSeats(seats);
            connObj.close();
            } catch (Exception sqlException) {
                sqlException.printStackTrace();
        }

    }


    public static void main(String[] args) {
        ShowtimeQuery showtimeQuery = new ShowtimeQuery();
        ArrayList<ShowtimeObject> showtimes = showtimeQuery.getShowtime("Titanic", "2022-01-01", "12:00:00");
        for (ShowtimeObject showtime : showtimes) {
            SeatQuery seatQuery = new SeatQuery();
            seatQuery.assignShowtimeSeats(showtime);
        }


    }
}