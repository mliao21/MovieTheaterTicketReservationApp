package ensf614project.src.datasource.Showtime;

import ensf614project.src.datasource.Movie.MovieObject;
import ensf614project.src.datasource.Theatre.TheatreObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShowtimeObject {
    private int id;
    private MovieObject movie;
    private TheatreObject theatre;
    private String startTime;
    private String endTime;
    private Date date;

    public ShowtimeObject() {
        this.id = 0;
        this.movie = null;
        this.theatre = null;
        this.startTime = null;
        this.endTime = null;
        this.date = null;
    }

    public ShowtimeObject(int id, MovieObject movie, TheatreObject theatre, String startTime, String endTime, Date date) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public ShowtimeObject(int id, MovieObject movie, TheatreObject theatre, String startTime, Date date) throws ParseException {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = getEndTime(startTime, movie);
        this.date = date;
    }

    public String getEndTime(String startTime, MovieObject movie) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date d = df.parse(startTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, movie.getRuntime());
        return df.format(cal.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieObject getMovie() {
        return movie;
    }

    public void setMovie(MovieObject movie) {
        this.movie = movie;
    }

    public TheatreObject getTheatre() {
        return theatre;
    }

    public void setTheatre(TheatreObject theatre) {
        this.theatre = theatre;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShowtimeObject{" +
                "id=" + id +
                ", movie=" + movie.getTitle() +
                ", theatre=" + theatre.getName() +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", date=" + date +
                '}';
    }
}
