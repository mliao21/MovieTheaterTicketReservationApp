package ensf614project.src.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDataSource {

    public static void main(String[] args) {
        try{
            // get connection to database

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf614_movie_theatre", "mock_user", "password");

            // create a statement
            Statement stmt = conn.createStatement();

            // execute a query
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOVIE");

            // process the result set
            while(rs.next()){
                System.out.println(rs.getString("Title"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }


}