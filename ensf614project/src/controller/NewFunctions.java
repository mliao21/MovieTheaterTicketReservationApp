package ensf614project.src.controller;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewFunctions {

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

    public void refundTicket(int seatInstanceId) {
        try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            // get ticket id that you are refunding
            PreparedStatement prepStatement = conn.prepareStatement(
                    "SELECT TicketID FROM TICKET WHERE TicketStatus = 'SOLD'\n" +
                            "AND SeatInstanceID = ?;\n");

            prepStatement.setInt(1, seatInstanceId);
            ResultSet resultSet = prepStatement.executeQuery();
            int ticketId = 0;
            while (resultSet.next()) {
                ticketId = resultSet.getInt("TicketID");
            }

            // update ticket status to refunded
            prepStatement = conn.prepareStatement(
                    "UPDATE TICKET SET TicketStatus = 'REFUNDED' WHERE TicketID = ?;");
            prepStatement.setInt(1, ticketId);
            prepStatement.executeUpdate();


            // update seat instance status to available, presale to false by default
            prepStatement = conn.prepareStatement(
                    "UPDATE SEAT_INSTANCE SET Occupied = FALSE AND Presale = FALSE WHERE SeatInstanceID = ?;");
            prepStatement.setInt(1, seatInstanceId);
            prepStatement.executeUpdate();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

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
                    "INSERT INTO COUPONS(CouponCode, CouponValue, TicketID)\n" +
                            "VALUES((SELECT LEFT(MD5(RAND()), 15)), ?, ?);");

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
            sqlException.printStackTrace();
        }
        return null;
    }

    public void addMovies(Movie movie, int theaterId, int cleaningTime,String openingDateString, String endDateString) {

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
            startTime = startTime.plusMinutes(movie.getRunTime() + cleaningTime);
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
            prepStatement.setString(1, movie.getName());
            prepStatement.setDate(2, (Date) movie.getReleaseDate());
            prepStatement.setString(3, movie.getDescription());
            prepStatement.setInt(4, movie.getRunTime());
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
                    prepStatement.setString(4, String.valueOf(LocalTime.parse(time).plusMinutes(movie.getRunTime())));
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

            MovieNotification subject = new MovieNotification();
            Subscribers ob1 = new Subscribers(subject);
            subject.addObserver(ob1);
            subject.notifyAllObservers(movie.getName() + " is pre-selling tickets now! ShowDates are: " + dates);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        NewFunctions newFunctions = new NewFunctions();
//        newFunctions.cancelMovie(3);
//        newFunctions.refundTicket(2);
//        newFunctions.issueCoupon(2, false);
//        newFunctions.issueCoupon(1, false);

        Movie movie = new Movie(1,"The Matrix",new Date(2018, 11, 7),"The Matrix is a 1999 American epic science fiction film directed by The Wachowskis and produced by Wachowski Productions, based on the story of the same name by Dan ", 150);
        newFunctions.addMovies(movie, 1,10, "2021-12-07", "2021-12-25");


    }
}