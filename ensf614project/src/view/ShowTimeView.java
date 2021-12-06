package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ShowTimeView extends JFrame {
	
	private ArrayList<JButton> showTimes;
	
	public ShowTimeView() {}
	
	public ShowTimeView(ArrayList<String> showTimesList) {
		
		showTimes = new ArrayList<JButton>();
		this.setShowTimesListButtons(showTimesList);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(0, 128, 128));
		
		for(JButton s: showTimes) {
			s.setPreferredSize(new Dimension(300, 50));
			menu.add(s);
		}
	
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("Center", menu);
		
		setTitle("AVAILABLE SHOWTIMES");
		setSize(400, 400);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
		
	public void setShowTimesListButtons(ArrayList<String> showTimesList) {
		for(String s: showTimesList) {
			this.showTimes.add(new JButton (s));
		}
	}
	
	public ArrayList<JButton> getShowtimesButton() {
		return showTimes;
	}
	
	public void selectShowTimeButtonListener(ActionListener actionListener) {
		if (showTimes != null) {
			for(JButton s: showTimes) {
				s.addActionListener(actionListener);
			}			
		}	
	}


}
