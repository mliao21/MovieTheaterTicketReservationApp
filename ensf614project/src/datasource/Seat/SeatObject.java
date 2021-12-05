package ensf614project.src.datasource.Seat;

public class SeatObject {
    private int seatId;
    private int seatRow;
    private char seatColumn;
    private boolean isReserved;
    private boolean isOccupied;

    public SeatObject(){}

    public SeatObject(int seatId, int seatRow, char seatColumn, boolean isReserved, boolean isOccupied) {
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.isReserved = isReserved;
        this.isOccupied = isOccupied;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public char getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(char seatColumn) {
        this.seatColumn = seatColumn;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "SeatObject{" +
                "seatId=" + seatId +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                ", isReserved=" + isReserved +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
