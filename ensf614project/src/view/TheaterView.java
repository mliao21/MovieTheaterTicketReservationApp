package ensf614project.src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class TheaterView extends JFrame {
	
	private ArrayList<JButton> theaters;
	
	public TheaterView() {}
	
	public TheaterView(ArrayList<String> theaterList) {
		
		theaters = new ArrayList<JButton>();
		this.setTheatersListButtons(theaterList);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(0, 128, 128));
		
		for(JButton t: theaters) {
			t.setPreferredSize(new Dimension(400, 50));
			menu.add(t);
		}
	
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("Center", menu);
		
		setTitle("THEATERS AVAILABLE");
		setSize(400, 210);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
		
	public void setTheatersListButtons(ArrayList<String> theaterlist) {
		for(String t: theaterlist) {
			this.theaters.add(new JButton (t));
		}
	}
	
	public ArrayList<JButton> getTheatersButton() {
		return theaters;
	}
	
	public void selectTheaterButtonListener(ActionListener actionListener) {
		if (theaters != null) {
			for(JButton t: theaters) {
				t.addActionListener(actionListener);
			}			
		}
		
	}

}
