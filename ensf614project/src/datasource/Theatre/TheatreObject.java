package ensf614project.src.datasource.Theatre;

import ensf614project.src.datasource.Seat.SeatObject;

import java.util.HashMap;

public class TheatreObject {
    private int id;
    private String name;
    private int capacity;
    private HashMap<String, SeatObject> seats;

    public TheatreObject(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public TheatreObject(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<String, SeatObject> getSeats() {
        return seats;
    }

    public void setSeats(HashMap<String, SeatObject> seats) {
        this.seats = seats;
    }

    public SeatObject getSeat(String seatId) {
        return seats.get(seatId);
    }

    @Override
    public String toString() {
        return "TheatreObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
