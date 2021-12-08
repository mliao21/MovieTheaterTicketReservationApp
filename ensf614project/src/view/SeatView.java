package ensf614project.src.view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SeatView extends JFrame {
	
	private ArrayList<ArrayList<String>> seats;
	private ArrayList<JButton> seatButtons;
	private ArrayList<Integer> statuses;
	private int rows;
    private int columns;
    
    public SeatView() {}
    
	public SeatView(ArrayList<Integer> statuses) {
		
		seats = setSeatLabels();
		seatButtons = new ArrayList<JButton>();
		this.statuses = new ArrayList<Integer>();
		this.setSeatStatus(statuses);
		JPanel menu = new JPanel(new GridLayout(rows, columns));
		
		int idx = 0;
		for (int i = 0 ; i < rows; i++) {
			for (int j = 0 ; j < columns; j++) {
				if (verifyOccupiedSeat(idx)) {
					JButton reserved = new JButton (seats.get(i).get(j).toString());
					reserved.setEnabled(false);
					reserved.setBackground(Color.LIGHT_GRAY);
					reserved.setText("taken");
					seatButtons.add(reserved);
					menu.add(reserved);
					idx++;
				}
				else {
					JButton button = new JButton(seats.get(i).get(j).toString());
	                seatButtons.add(button);
	                menu.add(button);
	                idx++;
				}
			}
		}
		
		JPanel screen = new JPanel();
		screen.setBackground(Color.CYAN);
		screen.add(new JLabel("SCREEN"));
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North", screen);
		contentPane.add("Center", menu);
		
		setTitle("AVAILABLE SEATS");
		setSize(500, 600);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
			
	public ArrayList<ArrayList<String>> setSeatLabels() {
		this.rows = 10;
		this.columns = 7;
		ArrayList<ArrayList<String>> seatlist = new ArrayList<>(rows);
		for(int i=0; i < rows; i++) {
			seatlist.add(new ArrayList<>());
		}
		
		for (int i = 0 ; i < rows; i++) {
			for (int j = 0 ; j < columns; j++) {
				char row_letter = (char) ('A'+ i);
				int col_num = j + 1;
				String seat_id = (Character.toString(row_letter) + Integer.toString(col_num));
				seatlist.get(i).add(seat_id);
			}
		}
		return seatlist;
	}
	
	public boolean verifyOccupiedSeat(int idx) {
		if (this.statuses == null)
			return true;
		else {
			if (statuses.get(idx).equals(1)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public void setSeatStatus(ArrayList<Integer> statuses) {
		for(Integer s: statuses) {
			this.statuses.add(s);
		}
	}
	
	public  ArrayList<JButton> getSeatButtons() {
		return seatButtons;
	}
	
	public void selectSeatButtonListener(ActionListener actionListener) {
		if (seatButtons != null) {
			for(JButton s: seatButtons) {
				s.addActionListener(actionListener);
			}			
		}
	}
	
}
