package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class TheaterView extends JFrame {
	
	private ArrayList<JButton> theaters;	
	
	public TheaterView(String ShowTime) {
		
		theaters = new ArrayList<JButton>();
		this.setTheatersListButtons(getTheaterDB(ShowTime));
		
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(0, 1));
		
		for(JButton t: theaters) {
			menu.add(t);
		}
	
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("Center", menu);
		
		setTitle("THEATERS AVAILABLE");
		setSize(600, 300);
		setResizable(false); //can't change the size of the screen
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
		
	public void setTheatersListButtons(ArrayList<String> theaterlist) {
		for(String t: theaterlist) {
			this.theaters.add(new JButton (t));
		}
	}
	
	public ArrayList<String> getTheaterDB(String showtime) {
		
		// retrieve list of available theaters at showtime input
		ArrayList<String> theatersDB = new ArrayList<String>();
		theatersDB.add("Red Theater");
		theatersDB.add("Blue Theater");
		theatersDB.add("Black Theater");
		theatersDB.add("Green Theater");
		theatersDB.add("Yellow Theater");
		return theatersDB;
	}

	public static void main(String[] args) {
		TheaterView theView = new TheaterView("ShowTime: Movie Titanic at 7pm");
		theView.setVisible(true);
	}

}
