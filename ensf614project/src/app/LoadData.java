package ensf614project.src.app;

import ensf614project.src.config.Configuration;
import ensf614project.src.controller.ModelController;
import ensf614project.src.model.Movie;
import ensf614project.src.model.OrdinaryUser;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


/**
 * Loads into the database.
 */
public class LoadData {
    OrdinaryUser user = (OrdinaryUser) OrdinaryUser.getInstance();
    ModelController mc = new ModelController(user);


    /**
     * Loads Registered Users into the database
     * @throws SQLException
     */
    public void insertRUs() {
        // create an array list of first names
        ArrayList<String> firstNames = new ArrayList<>();
        firstNames.add("John");
        firstNames.add("Jane");
        firstNames.add("Bob");
        firstNames.add("Mary");
        firstNames.add("Sally");

        // create an array list of last names
        ArrayList<String> lastNames = new ArrayList<>();
        lastNames.add("Smith");
        lastNames.add("Jones");
        lastNames.add("Doe");
        lastNames.add("Williams");
        lastNames.add("Brown");

        // create array of credit cards
        ArrayList<String> creditCards = new ArrayList<>();
        creditCards.add("0000-0000-0000-0000");
        creditCards.add("1111-1111-1111-1111");
        creditCards.add("2222-2222-2222-2222");
        creditCards.add("3333-3333-3333-3333");
        creditCards.add("4444-4444-4444-4444");


        // create an array list of emails
        ArrayList<String> emails = new ArrayList<>();
        emails.add("John@Smith.com");
        emails.add("Jane@Jones.com");
        emails.add("Bob@Doe.com");
        emails.add("Mary@Williams.com");
        emails.add("Sally@Brown.com");

        // create an array list of passwords
        ArrayList<String> passwords = new ArrayList<>();
        passwords.add("john");
        passwords.add("jane");
        passwords.add("bob");
        passwords.add("mary");
        passwords.add("sally");

        // add users to the database
        for (int i = 0; i < firstNames.size(); i++) {
            mc.registerUser(firstNames.get(i), lastNames.get(i), creditCards.get(i), emails.get(i), passwords.get(i));
        }

    }


    /**
     * Loads Movies into the database - adds showtimes and dates automatically based on run time of the movie and dates selected
     * @throws SQLException
     */
    public void insertMovies() {


        // create an array list of movies
        ArrayList<Movie> movies = new ArrayList<>();


        Movie shawshank = new Movie("The Shawshank Redemption", "1995-01-01", "A story about...", 150);
        Movie godfather = new Movie("The Godfather", "1972-01-01", "A story about...", 200);
        Movie godfather2 = new Movie("The Godfather II", "1979-01-01", "A story about...", 220);
        Movie darkKnight = new Movie("The Dark Knight", "2008-01-01", "A story about...", 120);
        Movie darkKnight2 = new Movie("The Dark Knight Rises", "2012-01-01", "A story about...", 140);
        Movie inception = new Movie("Inception", "2010-01-01", "A story about...", 150);
        Movie theMatrix = new Movie("The Matrix", "1999-01-01", "A story about...", 178);
        Movie theMatrix2 = new Movie("The Matrix Reloaded", "2003-01-01", "A story about...", 231);
        Movie theMatrix3 = new Movie("The Matrix Revolutions", "2006-01-01", "A story about...", 125);

        movies.add(shawshank);
        movies.add(godfather);
        movies.add(godfather2);
        movies.add(darkKnight);
        movies.add(darkKnight2);
        movies.add(inception);
        movies.add(theMatrix);
        movies.add(theMatrix2);
        movies.add(theMatrix3);

        // create list of theater ids
        ArrayList<Integer> theaterIds = new ArrayList<>();
        theaterIds.add(1);
        theaterIds.add(2);
        theaterIds.add(3);
        theaterIds.add(1);
        theaterIds.add(2);
        theaterIds.add(3);
        theaterIds.add(1);
        theaterIds.add(2);
        theaterIds.add(3);

        //create list of startdate strings
        ArrayList<String> startDates = new ArrayList<>();
        startDates.add("2021-12-15");
        startDates.add("2021-12-15");
        startDates.add("2021-12-15");
        startDates.add("2021-12-17");
        startDates.add("2021-12-17");
        startDates.add("2021-12-17");
        startDates.add("2021-12-19");
        startDates.add("2021-12-19");
        startDates.add("2021-12-19");

        //create list of enddate strings
        ArrayList<String> endDates = new ArrayList<>();
        endDates.add("2021-12-16");
        endDates.add("2021-12-16");
        endDates.add("2021-12-16");
        endDates.add("2021-12-18");
        endDates.add("2021-12-18");
        endDates.add("2021-12-18");
        endDates.add("2021-12-20");
        endDates.add("2021-12-20");
        endDates.add("2021-12-20");



        for (int i = 0; i < movies.size(); i++) {
            mc.addMovies(movies.get(i).getName(), theaterIds.get(i), 15, startDates.get(i), endDates.get(i));
        }

    }


    /**
     * Populates the database with tickets sold - updates the payment table with the ticket id and the payment id
     * @throws SQLException
     */
    public void sellTickets() {
        try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());

            // update seats for ordinary tickets
            String statement = "UPDATE SEAT_INSTANCE SET Occupied = True WHERE SeatID IN(2, 5, 7, 15, 22, 81, 55, 79, 101, 102, 100, 155, 210, 205, 22)";
            PreparedStatement prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();

            // update seats for presale
            statement = "UPDATE SEAT_INSTANCE SET Occupied = True AND Presale = True WHERE SeatID IN(20,23,24,25,26,27,103,104,105,106,206,207,208);";
            prepStatement = conn.prepareStatement(statement);

            // get seat instances that are occupied and update them to SOLD
            ArrayList<Integer> seatIds = new ArrayList<>();

            statement = "INSERT INTO TICKET(SeatInstanceID, Price, TicketStatus, Email)\n" +
                    "(SELECT SeatInstanceID, 2000, 'SOLD', 'test@email.com' FROM SEAT_INSTANCE WHERE Occupied = True);";
            prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();

            statement = "INSERT INTO PAYMENT(TicketID, CreditCardNo, Amount)\n" +
                    "(SELECT TicketID, '1234-5678-9123-456', 2000 FROM TICKET JOIN SEAT_INSTANCE SI on TICKET.SeatInstanceID = SI.SeatInstanceID\n" +
                    "WHERE Occupied = True);";
            prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();


            conn.close();
        } catch (SQLException e) {
            System.out.println("Database duplication error");
        }
    }

    /**
     * Updates the database with refunded tickets
     * @throws SQLException
     */
    public void refundTickets() {
        try {
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            // create seat instance array
            ArrayList<Integer> ticketIds = new ArrayList<>();


            String statement = "SELECT TicketId FROM TICKET WHERE SeatInstanceID IN (100,155) AND TicketStatus = 'SOLD'";
            PreparedStatement prepStatement = conn.prepareStatement(statement);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                ticketIds.add(rs.getInt("TicketID"));
            }

            for (Integer ticketId : ticketIds) {
                mc.issueCoupon(ticketId, false);
                System.out.println("Coupon for " + ticketId + " issued");

            }

            ArrayList<Integer> seatIds = new ArrayList<>();

            statement = "SELECT SeatID FROM SEAT_INSTANCE WHERE SeatInstanceID IN (100,155)";
            prepStatement = conn.prepareStatement(statement);
            rs = prepStatement.executeQuery();
            while (rs.next()) {
                seatIds.add(rs.getInt("SeatID"));
            }

            for (Integer seatId : seatIds) {
                mc.refundTicket(seatId, false);
                System.out.println("Ticket " + seatId + " has been refunded");
            }

        } catch (SQLException e) {
            System.out.println("Database duplication error");
        }
    }

    /**
     * Updates the database with coupons
     * @throws SQLException
     */
    public void insertCoupons(){
        // insert coupons into database
        try{
            Connection conn = DriverManager.getConnection(Configuration.getConnection(), Configuration.getUsername(), Configuration.getPassword());
            String statement = "INSERT INTO COUPONS(CouponCode, CouponValue, TicketID, ExpiryDate) " +
                    "VALUES((SELECT LEFT(MD5(RAND()), 15)), 2000, 15, NOW() + INTERVAL 1 YEAR),\n" +
                    "       ('AAAAAAAAAAAA', 200000, 1, NOW() + INTERVAL 1 YEAR),\n" +
                    "       ('BBBBBBBBBBBB', 20000, 2, NOW() + INTERVAL 1 YEAR),\n" +
                    "       ('CCCCCCCCCCCC', 1500, 3, NOW() + INTERVAL 1 YEAR);\n" +
                    "\n";

            PreparedStatement prepStatement = conn.prepareStatement(statement);
            prepStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database duplication error");
        }
    }


    public static void main(String[] args) {
        LoadData loadData = new LoadData();
        loadData.insertRUs();
        loadData.insertMovies();
        loadData.sellTickets();
        loadData.refundTickets();
        loadData.insertCoupons();

    }

}