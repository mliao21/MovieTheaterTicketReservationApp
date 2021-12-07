package ensf614project.src.datasource.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class MovieQuery {
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/ensf614_movie_theatre";
    static final String JDBC_USER = "mock_user";
    static final String JDBC_PASS = "password";

    public ArrayList<MovieObject> getMovieByTitle(String title) {
        ArrayList<MovieObject> movies = new ArrayList<>();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM MOVIE WHERE Title LIKE ?");
            prepStatement.setString(1, "%" + title + "%");
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                MovieObject movie = new MovieObject(
                        resObj.getInt("MovieID"),
                        resObj.getString("Title"),
                        resObj.getDate("OpeningDate"),
                        resObj.getString("Description"),
                        resObj.getInt("Runtime")
                        );
                movies.add(movie);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return movies;
    }

    public ArrayList<MovieObject> allMoviesOpen() {
        ArrayList<MovieObject> movies = new ArrayList<>();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM MOVIE WHERE OpeningDate >= ?");
            // TODO : Change to current date not in milliseconds
            prepStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                MovieObject movie = new MovieObject(
                        resObj.getInt("MovieID"),
                        resObj.getString("Title"),
                        resObj.getDate("OpeningDate"),
                        resObj.getString("Description"),
                        resObj.getInt("Runtime")
                );
                movies.add(movie);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return movies;
    }

    public ArrayList<MovieObject> preSaleMovies() {
        ArrayList<MovieObject> movies = new ArrayList<>();

        try {
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement prepStatement = connObj
                    .prepareStatement(
                            "SELECT * FROM MOVIE WHERE OpeningDate < ?");
            prepStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ResultSet resObj = prepStatement.executeQuery();
            while(resObj.next()) {
                MovieObject movie = new MovieObject(
                        resObj.getInt("MovieID"),
                        resObj.getString("Title"),
                        resObj.getDate("OpeningDate"),
                        resObj.getString("Description"),
                        resObj.getInt("Runtime")
                );
                movies.add(movie);
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return movies;
    }



    public static void main(String[] args) {
        MovieQuery movieQuery = new MovieQuery();
        ArrayList<MovieObject> movies = movieQuery.getMovieByTitle("Titanic");
        for(MovieObject movie : movies) {
            System.out.println(movie);
        }

        ArrayList<MovieObject> openMovies = movieQuery.allMoviesOpen();
        for(MovieObject movie : openMovies) {
            System.out.println(movie);
        }

        ArrayList<MovieObject> preSaleMovies = movieQuery.preSaleMovies();
        for(MovieObject movie : preSaleMovies) {
            System.out.println(movie);
        }

    }
}



