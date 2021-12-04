package ensf614project.src.controller;

import ensf614project.src.config.Configuration;
import ensf614project.src.model.Credit;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static void main(String[] args) throws SQLException {
        NewFunctions newFunctions = new NewFunctions();
        newFunctions.cancelMovie(3);
        newFunctions.refundTicket(2);
        newFunctions.issueCoupon(2, false);
        newFunctions.issueCoupon(1, false);


    }
}

