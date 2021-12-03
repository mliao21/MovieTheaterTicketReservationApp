package ensf614project.src.datasource.Showtime;


import ensf614project.src.datasource.Movie.MovieObject;
import ensf614project.src.datasource.Movie.MovieQuery;
import ensf614project.src.datasource.Theatre.TheatreObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ShowtimeQuery {
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/ensf614_movie_theatre";
    static final String JDBC_USER = "mock_user";
    static final String JDBC_PASS = "password";

    public ArrayList<ShowtimeObject> getShowtime(String movieTitle, String date) {
        ArrayList<ShowtimeObject> showtimes = new ArrayList<>();
        MovieQuery movieQuery = new MovieQuery();
        ArrayList<MovieObject> movies = movieQuery.getMovieByTitle(movieTitle);

        for(MovieObject m : movies) {
            try {
                Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
                PreparedStatement prepStatement = connObj
                        .prepareStatement(
                                "SELECT M.Title, " +
                                        "T.TheatreID, T.TheatreName, T.Capacity, " +
                                        "S.ShowtimeID, S.StartTime, S.EndTime, S.ShowDate " +
                                        "FROM SHOWTIME S " +
                                        "JOIN MOVIE M on S.MovieID = M.MovieID " +
                                        "JOIN THEATRE T on S.TheatreID = T.TheatreID " +
                                        "WHERE M.Title = ? " +
                                        "AND ShowDate = ?;");
                prepStatement.setString(1, m.getTitle());
                prepStatement.setString(2, date);

                ResultSet resObj = prepStatement.executeQuery();
                while(resObj.next()) {
                    TheatreObject theatre = new TheatreObject(
                            resObj.getInt("TheatreID"),
                            resObj.getString("TheatreName"),
                            resObj.getInt("Capacity"));

                    System.out.println(resObj.getString("Title"));

                    ShowtimeObject showtime = new ShowtimeObject(
                            resObj.getInt("ShowtimeID"),
                            m,
                            theatre,
                            resObj.getString("StartTime"),
                            resObj.getString("EndTime"),
                            resObj.getDate("ShowDate")
                    );
                    showtimes.add(showtime);
                }
            } catch (Exception sqlException) {
                sqlException.printStackTrace();
            }
        }

        return showtimes;
    }

    public ArrayList<ShowtimeObject> getShowtime(String movieTitle, String date, String time) {
        ArrayList<ShowtimeObject> showtimes = new ArrayList<>();
        MovieQuery movieQuery = new MovieQuery();
        ArrayList<MovieObject> movies = movieQuery.getMovieByTitle(movieTitle);

        for(MovieObject m : movies) {
            try {
                Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
                PreparedStatement prepStatement = connObj
                        .prepareStatement(
                                "SELECT M.Title, " +
                                        "T.TheatreID, T.TheatreName, T.Capacity, " +
                                        "S.ShowtimeID, S.StartTime, S.EndTime, S.ShowDate " +
                                        "FROM SHOWTIME S " +
                                        "JOIN MOVIE M on S.MovieID = M.MovieID " +
                                        "JOIN THEATRE T on S.TheatreID = T.TheatreID " +
                                        "WHERE M.Title = ? " +
                                        "AND StartTime > ? " +
                                        "AND ShowDate = ?;");
                prepStatement.setString(1, m.getTitle());
                prepStatement.setString(2, time);
                prepStatement.setString(3, date);

                ResultSet resObj = prepStatement.executeQuery();
                while(resObj.next()) {
                    TheatreObject theatre = new TheatreObject(
                            resObj.getInt("TheatreID"),
                            resObj.getString("TheatreName"),
                            resObj.getInt("Capacity"));

                    System.out.println(resObj.getString("Title"));

                    ShowtimeObject showtime = new ShowtimeObject(
                            resObj.getInt("ShowtimeID"),
                            m,
                            theatre,
                            resObj.getString("StartTime"),
                            resObj.getString("EndTime"),
                            resObj.getDate("ShowDate")
                    );
                    showtimes.add(showtime);
                }
            } catch (Exception sqlException) {
                sqlException.printStackTrace();
            }
        }

        return showtimes;
    }



    public static void main(String[] args) {
        ShowtimeQuery showtimeQuery = new ShowtimeQuery();
        ArrayList<ShowtimeObject> showtimes = showtimeQuery.getShowtime("Titanic", "2022-01-01");
        for(ShowtimeObject s : showtimes) {
            System.out.println(s);
        }

        ArrayList<ShowtimeObject> showtimes2 = showtimeQuery.getShowtime("Titanic", "2022-01-01", "12:00:00");
        for(ShowtimeObject s : showtimes2) {
            System.out.println(s);
        }


    }
}