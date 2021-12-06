package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SeatView extends JFrame {
	
	private ArrayList<ArrayList<String>> seats;
	private int rows;
    private int columns;
    private ImageIcon choice = new ImageIcon("cinema_seat.png"); 
    private JButton checkout = new JButton ("Proceed to Checkout");
    
	public SeatView(String showtime, String theater) {
		
		seats = getSeatsDB(showtime, theater);
		JPanel menu = new JPanel(new GridLayout(rows, columns));
		
		for (int i = 0 ; i < rows; i++) {
			for (int j = 0 ; j < columns; j++) {
				if (seats.get(i).get(j).equals("C2") || seats.get(i).get(j).equals("F5") || seats.get(i).get(j).equals("G3")) {
					JButton reserved = new JButton (seats.get(i).get(j).toString());
					reserved.setEnabled(false);
					reserved.setBackground(Color.LIGHT_GRAY);
					reserved.setText("taken");
					menu.add(reserved);
				}
				else {
					JToggleButton button = new JToggleButton(seats.get(i).get(j).toString());
	                button.addActionListener(new ActionListener() {
	                	
	                	@Override
	                    public void actionPerformed(ActionEvent actionEvent) {
	                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	                        boolean selected = abstractButton.getModel().isSelected();
	                        if (selected) {
	                        	Image img = choice.getImage();
	                            Image newimg = img.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);  
	                            choice = new ImageIcon(newimg);
	                        	button.setIcon(choice);
	                        } else {
	                        	button.setIcon(null);
	                        }
	                    }
	                });
	                menu.add(button);
				}
			}
		}
		
		JPanel screen = new JPanel();
		screen.setBackground(Color.CYAN);
		screen.add(new JLabel("SCREEN"));
		
		JPanel proceed = new JPanel();
		proceed.add(checkout);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North", screen);
		contentPane.add("Center", menu);
		contentPane.add("South", proceed);
		
		setTitle("AVAILABLE SEATS");
		setSize(500, 600);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
			
	public ArrayList<ArrayList<String>> getSeatsDB(String showtime, String theater) {
		
		// retrieve list of seats available for the theaters and showtime input in
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
	
	public JButton getCheckoutButton() {
		return checkout;
	}
	
	public void checkoutButtonListener(ActionListener actionListener) {
		checkout.addActionListener(actionListener);
	}
	
}
