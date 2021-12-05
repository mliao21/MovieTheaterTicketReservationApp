package ensf614project.src.datasource.Theatre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class TheatreQuery {
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/ensf614_movie_theatre";
    static final String JDBC_USER = "mock_user";
    static final String JDBC_PASS = "password";

    public ArrayList<TheatreObject> getAllTheatres() {
        ArrayList<TheatreObject> theatres = new ArrayList<>();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM THEATRE");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                TheatreObject theatre = new TheatreObject(
                        resObj.getInt("TheatreID"),
                        resObj.getString("TheatreName"),
                        resObj.getInt("Capacity")
                );
                theatres.add(theatre);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return theatres;
    }

    public ArrayList<TheatreObject> getTheatreByName(String name) {
        ArrayList<TheatreObject> theatres = new ArrayList<>();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM THEATRE WHERE TheatreName LIKE ?");
            prepStatement.setString(1, "%" + name + "%");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                TheatreObject theatre = new TheatreObject(
                        resObj.getInt("TheatreID"),
                        resObj.getString("TheatreName"),
                        resObj.getInt("Capacity")
                );
                theatres.add(theatre);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return theatres;
    }


    public static void main(String[] args) {
        TheatreQuery query = new TheatreQuery();
        ArrayList<TheatreObject> theatres = query.getAllTheatres();
        for(TheatreObject t : theatres) {
            System.out.println(t);
        }

        ArrayList<TheatreObject> theatreByName = query.getTheatreByName("Red");
        for(TheatreObject t : theatreByName) {
            System.out.println(t);
        }

    }
}



