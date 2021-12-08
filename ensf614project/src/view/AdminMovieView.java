package ensf614project.src.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import javax.swing.*;

public class AdminMovieView extends JFrame {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	NumberFormat integerFormat = NumberFormat.getIntegerInstance();
	private JTextField movie = new JTextField (30);
	private JFormattedTextField theaterId = new JFormattedTextField(integerFormat);
	private JFormattedTextField cleaningTime = new JFormattedTextField(integerFormat);
	private JFormattedTextField openingDate = new JFormattedTextField(dateFormat);
	private JFormattedTextField endDate = new JFormattedTextField(dateFormat);
	private JButton addButton = new JButton("Add");

	public AdminMovieView() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel movie = new JLabel("Movie:");
		movie.setForeground(Color.WHITE);
		movie.setFont(new Font("Tahoma", Font.BOLD, 12));
		movie.setBounds(28, 57, 200, 20);
		panel.add(movie);
		
		JLabel theater = new JLabel("Theater ID:");
		theater.setForeground(Color.WHITE);
		theater.setFont(new Font("Tahoma", Font.BOLD, 12));
		theater.setBounds(28, 93, 200, 20);
		panel.add(theater);
		
		JLabel cleaning = new JLabel("Cleaning Time (Min):");
		cleaning.setForeground(Color.WHITE);
		cleaning.setFont(new Font("Tahoma", Font.BOLD, 12));
		cleaning.setBounds(28, 129, 200, 20);
		panel.add(cleaning);
		
		JLabel opening = new JLabel("Opening Date (YYYY-MM-DD):");
		opening.setForeground(Color.WHITE);
		opening.setFont(new Font("Tahoma", Font.BOLD, 12));
		opening.setBounds(28, 165, 200, 20);
		panel.add(opening);
		
		JLabel end = new JLabel("Ending Date (YYYY-MM-DD):");
		end.setForeground(Color.WHITE);
		end.setFont(new Font("Tahoma", Font.BOLD, 12));
		end.setBounds(28, 201, 200, 20);
		panel.add(end);
	    
		this.movie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.movie.setBounds(220, 57, 156, 20);
		panel.add(this.movie);		
		
		this.theaterId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.theaterId.setBounds(220, 93, 156, 20);
		panel.add(this.theaterId);
		
		this.cleaningTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.cleaningTime.setBounds(220, 129, 156, 20);
		panel.add(this.cleaningTime);
		
		this.openingDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.openingDate.setBounds(220, 165, 156, 20);
		panel.add(this.openingDate);
		
		this.endDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.endDate.setBounds(220, 201, 156, 20);
		panel.add(this.endDate);
		
		this.addButton.setBounds(160, 270, 80, 30);
		panel.add(this.addButton);      
	
		setTitle("ADMIN MOVIE VIEW");
		setSize(430, 400);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	public String getMovie() {
		return movie.getText();
	}

	public int getTheaterId() {
		return Integer.parseInt(theaterId.getText());
	}

	public int getCleaningTime() {
		return Integer.parseInt(cleaningTime.getText());
	}
	
	public String getOpeningDate() {
		return openingDate.getText();
	}

	public String getEndDate() {
		return endDate.getText();
	}

	public JButton getUpdateButton() {
		return addButton;
	}
	
	public void addButtonListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}

}
