package ensf614project.src.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends JFrame{
	
	private JPanel sidePanel, panel;
	private JButton buyTicketButton,cancelTicketButton, loginButton, registerButton, searchMovieButton;
	private JLabel topLabel, movieLabel;
	
	public MainMenuView() {
		
		JFrame frame = new JFrame("Alberta Cinema's");
		
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 805, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		sidePanel = new JPanel();
		sidePanel.setBackground(new Color(0, 139, 139));
		sidePanel.setBounds(10, 11, 187, 500);
		frame.getContentPane().add(sidePanel);
		sidePanel.setLayout(null);
		
		buyTicketButton = new JButton("Buy Ticket");
		buyTicketButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buyTicketButton.setForeground(new Color(0, 0, 0));
		buyTicketButton.setBackground(Color.LIGHT_GRAY);
		buyTicketButton.setBounds(32, 217, 119, 23);
		sidePanel.add(buyTicketButton);
		
		cancelTicketButton = new JButton("Cancel Ticket");
		cancelTicketButton.setBackground(Color.LIGHT_GRAY);
		cancelTicketButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelTicketButton.setBounds(32, 272, 119, 23);
		sidePanel.add(cancelTicketButton);
		
		loginButton = new JButton("Login");
	
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setBounds(32, 166, 119, 23);
		sidePanel.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBackground(Color.LIGHT_GRAY);
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		registerButton.setBounds(32, 120, 119, 23);
		sidePanel.add(registerButton);
		
		searchMovieButton = new JButton("Search Movie");
		searchMovieButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchMovieButton.setBackground(Color.LIGHT_GRAY);
		searchMovieButton.setBounds(32, 67, 119, 23);
		sidePanel.add(searchMovieButton);
		
		topLabel = new JLabel("New label");
		topLabel.setIcon(new ImageIcon("Images\\Alberta Cinema's.png"));
		topLabel.setBounds(193, 11, 592, 76);
		frame.getContentPane().add(topLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(193, 87, 592, 410);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		movieLabel = new JLabel("");
		movieLabel.setIcon(new ImageIcon("Images\\LOTR.png"));
		movieLabel.setBounds(67, 0, 478, 396);
		panel.add(movieLabel);
		
		frame.setVisible(true);
	}

	public JPanel getSidePanel() {
		return sidePanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBuyTicket() {
		return buyTicketButton;
	}

	public JButton getCancelTicket() {
		return cancelTicketButton;
	}

	public JButton getLogin() {
		return loginButton;
	}

	public JButton getRegister() {
		return registerButton;
	}

	public JButton getSearchMovie() {
		return searchMovieButton;
	}

	public JLabel getTopLabel() {
		return topLabel;
	}

	public JLabel getMovieLabel() {
		return movieLabel;
	}

	public void setSidePanel(JPanel sidePanel) {
		this.sidePanel = sidePanel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setBuyTicket(JButton buyTicket) {
		this.buyTicketButton = buyTicket;
	}

	public void setCancelTicket(JButton cancelTicket) {
		this.cancelTicketButton = cancelTicket;
	}

	public void setLogin(JButton login) {
		this.loginButton = login;
	}

	public void setRegister(JButton register) {
		this.registerButton = register;
	}

	public void setSearchMovie(JButton searchMovie) {
		this.searchMovieButton = searchMovie;
	}

	public void setTopLabel(JLabel topLabel) {
		this.topLabel = topLabel;
	}

	public void setMovieLabel(JLabel movieLabel) {
		this.movieLabel = movieLabel;
	}
	
	
	public void addBuyTicketButtonListener(ActionListener actionListener) {
		buyTicketButton.addActionListener(actionListener);
	}
	
	public void addCancelTicketButtonListener(ActionListener actionListener) {
		cancelTicketButton.addActionListener(actionListener);
	}
	
	public void addSearchMovieButtonListener(ActionListener actionListener) {
		searchMovieButton.addActionListener(actionListener);
	}
	
	public void addLoginButtonListener(ActionListener actionListener) {
		loginButton.addActionListener(actionListener);
	}
	
	public void addRegisterButtonListener(ActionListener actionListener) {
		registerButton.addActionListener(actionListener);
	}

}
