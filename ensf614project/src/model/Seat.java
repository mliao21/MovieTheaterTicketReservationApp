package model;

public class Seat {
	private String id;
	private int row;
	private int col;
	private boolean status;
	public Seat(String id, int row, int col) {
		super();
		this.id = id;
		this.row = row;
		this.col = col;
		this.status =false;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}
