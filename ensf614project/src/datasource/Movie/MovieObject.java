package ensf614project.src.datasource.Movie;

import java.util.Date;

public class MovieObject {
    private int id;
    private String title;
    private Date openingDate;
    private String description;
    private int runtime;

    public MovieObject(int id, String title, Date openingDate, String description, int runtime) {
        this.id = id;
        this.title = title;
        this.openingDate = openingDate;
        this.description = description;
        this.runtime = runtime;
    }

    public MovieObject() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public String getDescription() {
        return description;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public String toString() {
        return "MovieObject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", openingDate=" + openingDate +
                ", description='" + description + '\'' +
                ", runtime=" + runtime +
                '}';
    }
}
