package ensf614project.src.controller;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewFunctions {

//    public void cancelMovie(int movieId) {
//        try {
//            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
//
//            // this is all that needs to be done, queries that pull movies need to only look at movies that are not cancelled
//            PreparedStatement prepStatement = conn
//                    .prepareStatement(
//                            "UPDATE MOVIE SET MovieStatus = 'CANCELLED' WHERE MovieID = ?;");
//            prepStatement.setInt(1, movieId);
//            prepStatement.executeUpdate();
//
//        } catch (Exception sqlException) {
//            sqlException.printStackTrace();
//        }
//    }
//
//    public void refundTicket(int seatInstanceId) {
//        try {
//            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
//            // get ticket id that you are refunding
//            PreparedStatement prepStatement = conn.prepareStatement(
//                    "SELECT TicketID FROM TICKET WHERE TicketStatus = 'SOLD'\n" +
//                            "AND SeatInstanceID = ?;\n");
//
//            prepStatement.setInt(1, seatInstanceId);
//            ResultSet resultSet = prepStatement.executeQuery();
//            int ticketId = 0;
//            while (resultSet.next()) {
//                ticketId = resultSet.getInt("TicketID");
//            }
//
//            // update ticket status to refunded
//            prepStatement = conn.prepareStatement(
//                    "UPDATE TICKET SET TicketStatus = 'REFUNDED' WHERE TicketID = ?;");
//            prepStatement.setInt(1, ticketId);
//            prepStatement.executeUpdate();
//
//
//            // update seat instance status to available, presale to false by default
//            prepStatement = conn.prepareStatement(
//                    "UPDATE SEAT_INSTANCE SET Occupied = FALSE AND Presale = FALSE WHERE SeatInstanceID = ?;");
//            prepStatement.setInt(1, seatInstanceId);
//            prepStatement.executeUpdate();
//
//        } catch (Exception sqlException) {
//            sqlException.printStackTrace();
//        }
//    }
//
//    public Credit issueCoupon(int ticketId, boolean subscriber) {
//        double multiplier = 1;
//
//        // if subscriber is false, multiplier is 0.85
//        if (!subscriber) {
//            multiplier = 0.85;
//        }
//
//        try {
//            // get showdate and time and ticket price
//            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
//            PreparedStatement prepStatement = conn.prepareStatement(
//                    "SELECT ShowDate, StartTime, Price\n" +
//                            "FROM SHOWTIME\n" +
//                            "    JOIN SEAT_INSTANCE SI on SHOWTIME.ShowtimeID = SI.ShowtimeID\n" +
//                            "    JOIN TICKET T on SI.SeatInstanceID = T.SeatInstanceID\n" +
//                            "WHERE TicketID = ?");
//            prepStatement.setInt(1, ticketId);
//            ResultSet resultSet = prepStatement.executeQuery();
//            String showDate = "";
//            String startTime = "";
//            int price = 0;
//            while (resultSet.next()) {
//                showDate = resultSet.getString("ShowDate");
//                startTime = resultSet.getString("StartTime");
//                price = resultSet.getInt("Price");
//            }
//
//            // check if showDate and startTime are within 72 hours
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime showDateTime = LocalDateTime.parse(showDate + " " + startTime, formatter);
//            Duration duration = Duration.between(now, showDateTime);
//            long hours = duration.toHours();
//
//
//            // get no coupon discount if within 72 hours
//            if (hours < 72) {
//                return null;
//            }
//
//            int couponAmount = (int) (price * multiplier);
//
//            // create COUPON in database
//
//            prepStatement = conn.prepareStatement(
//                    "INSERT INTO COUPONS(CouponCode, CouponValue, TicketID, ExpiryDate)\n" +
//                            "VALUES((SELECT LEFT(MD5(RAND()), 15)), ?, ?, NOW() + INTERVAL 1 YEAR);");
//
//            prepStatement.setInt(1, couponAmount);
//            prepStatement.setInt(2, ticketId);
//            prepStatement.executeUpdate();
//
//            // get coupon code
//
//            prepStatement = conn.prepareStatement(
//                    "SELECT CouponCode FROM COUPONS WHERE TicketID = ?;");
//            prepStatement.setInt(1, ticketId);
//            resultSet = prepStatement.executeQuery();
//            String couponCode = "";
//            while (resultSet.next()) {
//                couponCode = resultSet.getString("CouponCode");
//            }
//
//            // create credit object
//            Credit credit = new Credit(couponCode, couponAmount);
//            System.out.println(credit);
//            return credit;
//        }
//        catch (Exception sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }
//
//    public void addMovies(Movie movie, int theaterId, int cleaningTime,String openingDateString, String endDateString) {
//
//        // set a opening time of the theater for 8 am
//        String openingTime = "08:00:00";
//        // last showing time of 10 pm
//        String lastShowing = "22:00:00";
//
//
//        // get all dates between openingDate and endDate
//        List<String> dates = new ArrayList<>();
//        LocalDate startDate = LocalDate.parse(openingDateString);
//        LocalDate endDate = LocalDate.parse(endDateString);
//        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
//            dates.add(startDate.toString());
//            startDate = startDate.plusDays(1);
//        }
//
//        // get all times between openingTime and lastShowing base on movie runtime + cleaning time
//        List<String> times = new ArrayList<>();
//        LocalTime startTime = LocalTime.parse(openingTime);
//        LocalTime endTime = LocalTime.parse(lastShowing);
//        while (startTime.isBefore(endTime) && startTime.plusMinutes(1).isAfter(LocalTime.parse(openingTime))) {
//            times.add(startTime.toString());
//            startTime = startTime.plusMinutes(movie.getRunTime() + cleaningTime);
//        }
//
//        Connection conn;
//        String statement = "";
//        PreparedStatement prepStatement;
//        try {
//            conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
//
//            // initial insert of the movie
//            statement = "INSERT INTO MOVIE(Title, OpeningDate, Description, Runtime)\r\n"
//                    + "VALUES(?, ?, ?, ?);";
//            prepStatement = conn.prepareStatement(statement);
//            prepStatement.setString(1, movie.getName());
//            prepStatement.setDate(2, (Date) movie.getReleaseDate());
//            prepStatement.setString(3, movie.getDescription());
//            prepStatement.setInt(4, movie.getRunTime());
//            prepStatement.executeUpdate();
//
//            // get the last movie id inserted to use for all showtimes
//            prepStatement = conn.prepareStatement("SELECT LAST_INSERT_ID() AS 'MovieID';");
//            ResultSet resObj = prepStatement.executeQuery();
//            int movieId = 0;
//            while (resObj.next()) {
//                movieId = resObj.getInt("MovieID");
//            }
//
//            for (String date : dates) {
//                for (String time : times) {
//                    // insert showtime
//                    statement ="INSERT INTO SHOWTIME(MovieID, TheatreID, StartTime, EndTime, ShowDate)\r\n"
//                            + "VALUES(?, ?, ?, ?, ?);";
//                    prepStatement = conn.prepareStatement(statement);
//                    prepStatement.setInt(1, movieId);
//                    prepStatement.setInt(2, theaterId);
//                    prepStatement.setString(3, time);
//                    prepStatement.setString(4, String.valueOf(LocalTime.parse(time).plusMinutes(movie.getRunTime())));
//                    prepStatement.setString(5, date);
//                    prepStatement.executeUpdate();
//                }
//            }
//
//            // get all showtimes with the movie id
//            statement = "SELECT ShowtimeID FROM SHOWTIME WHERE MovieID = ?;";
//            prepStatement = conn.prepareStatement(statement);
//            prepStatement.setInt(1, movieId);
//            ResultSet resultSet = prepStatement.executeQuery();
//            ArrayList<Integer> showtimeIds = new ArrayList<>();
//            while (resultSet.next()) {
//                showtimeIds.add(resultSet.getInt("ShowtimeID"));
//            }
//
//            // insert into SEAT_INSTANCE for all Showtime
//            for (int showtimeId : showtimeIds) {
//                statement = "INSERT INTO SEAT_INSTANCE(SeatID, ShowtimeID)\r\n"
//                        + "SELECT SeatID, ShowtimeID FROM (\n" +
//                        "SELECT ShowtimeID FROM SHOWTIME\n" +
//                        "JOIN THEATRE T on SHOWTIME.TheatreID = T.TheatreID\n" +
//                        "WHERE T.TheatreID = ?\n" +
//                        "    AND ShowtimeID = ?\n" +
//                        "    ) ST\n" +
//                        "CROSS JOIN (\n" +
//                        "    SELECT SeatID FROM SEAT_CHART\n" +
//                        "    WHERE TheatreID = ?\n" +
//                        "    ) CJ;";
//                prepStatement = conn.prepareStatement(statement);
//                prepStatement.setInt(1, theaterId);
//                prepStatement.setInt(2, showtimeId);
//                prepStatement.setInt(3, theaterId);
//                prepStatement.executeUpdate();
//            }
//
//
//            conn.close();
//
//            MovieNotification subject = new MovieNotification();
//            Subscribers ob1 = new Subscribers(subject);
//            subject.addObserver(ob1);
//            subject.notifyAllObservers(movie.getName() + " is pre-selling tickets now! ShowDates are: " + dates);
//
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }

    public boolean verifyLogin(String email, String password) {
        try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            String statement = "SELECT * FROM REGISTERED_USERS WHERE Email = ? AND Password = ?;";
            PreparedStatement prepStatement = conn.prepareStatement(statement);
            prepStatement.setString(1, email);
            prepStatement.setString(2, password);
            ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String username, String firstName, String lastName, String creditCardNumber, String email, String password) {
        try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            PreparedStatement prepStatement = conn.prepareStatement(
                    "INSERT INTO REGISTERED_USERS(Username, FName, LName, CreditCardNo, MembershipStart, Email, Password) VALUES(?, ?, ?, ?, ?, ?, ?);");
            prepStatement.setString(1, username);
            prepStatement.setString(2, firstName);
            prepStatement.setString(3, lastName);
            prepStatement.setString(4, creditCardNumber);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            prepStatement.setString(5, dtf.format(now).toString());
            prepStatement.setString(6, email);
            prepStatement.setString(7, password);
            prepStatement.executeUpdate();

            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createTicket(int showTimeId, int seatInstanceId, int price, String ticketStatus, String email, String creditCard, String couponCode) {
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

            System.out.println("TicketID: " + ticketId);
            System.out.println("CreditCardNo: " + creditCard);
            System.out.println("Amount: " + Math.max(0, price - couponValue));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        NewFunctions newFunctions = new NewFunctions();
//        newFunctions.cancelMovie(3);
//        newFunctions.refundTicket(2);
//        newFunctions.issueCoupon(2, false);
//        newFunctions.issueCoupon(1, false);


//        Movie movie = new Movie(1,"The Matrix",new Date(2018, 11, 7),"The Matrix is a 1999 American epic science fiction film directed by The Wachowskis and produced by Wachowski Productions, based on the story of the same name by Dan ", 150);
//        newFunctions.addMovies(movie, 1,10, "2021-12-07", "2021-12-25");

        if (newFunctions.verifyLogin("al@test.com", "testpassword")) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }

        if (newFunctions.verifyLogin("al@test.com", "wrongpassword")) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }

        if (newFunctions.verifyLogin("wrongemail@email.com", "testpassword")) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }

        if (newFunctions.registerUser("al", "Al", "L", "123456789", "AL@AL.com", "testpassword")) {
            System.out.println("Registration Successful");
        }
        else {
            System.out.println("Registration Failed");
        }

        if (newFunctions.registerUser("alex", "Al", "L", "123456789", "AL@AL.com", "testpassword")) {
            System.out.println("Registration Successful");
        }
        else {
            System.out.println("Registration Failed");
        }

        // create ticket
        newFunctions.createTicket(1, 8,2000, "SOLD", "mike@mike.com", "111111111111", "AAAAAAAAAAAA");



    }
}