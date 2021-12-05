package View;

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
	private JButton buyTicket,cancelTicket, login, register, searchMovie;
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
		
		buyTicket = new JButton("Buy Ticket");
		buyTicket.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buyTicket.setForeground(new Color(0, 0, 0));
		buyTicket.setBackground(Color.LIGHT_GRAY);
		buyTicket.setBounds(32, 217, 119, 23);
		sidePanel.add(buyTicket);
		
		cancelTicket = new JButton("Cancel Ticket");
		cancelTicket.setBackground(Color.LIGHT_GRAY);
		cancelTicket.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelTicket.setBounds(32, 272, 119, 23);
		sidePanel.add(cancelTicket);
		
		login = new JButton("Login");
	
		login.setFont(new Font("Tahoma", Font.PLAIN, 12));
		login.setBackground(Color.LIGHT_GRAY);
		login.setBounds(32, 166, 119, 23);
		sidePanel.add(login);
		
		register = new JButton("Register");
		register.setBackground(Color.LIGHT_GRAY);
		register.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		register.setBounds(32, 120, 119, 23);
		sidePanel.add(register);
		
		searchMovie = new JButton("Search Movie");
		searchMovie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchMovie.setBackground(Color.LIGHT_GRAY);
		searchMovie.setBounds(32, 67, 119, 23);
		sidePanel.add(searchMovie);
		
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
		return buyTicket;
	}

	public JButton getCancelTicket() {
		return cancelTicket;
	}

	public JButton getLogin() {
		return login;
	}

	public JButton getRegister() {
		return register;
	}

	public JButton getSearchMovie() {
		return searchMovie;
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
		this.buyTicket = buyTicket;
	}

	public void setCancelTicket(JButton cancelTicket) {
		this.cancelTicket = cancelTicket;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

	public void setRegister(JButton register) {
		this.register = register;
	}

	public void setSearchMovie(JButton searchMovie) {
		this.searchMovie = searchMovie;
	}

	public void setTopLabel(JLabel topLabel) {
		this.topLabel = topLabel;
	}

	public void setMovieLabel(JLabel movieLabel) {
		this.movieLabel = movieLabel;
	}
	
	//private JButton buyTicket,cancelTicket, login, register, searchMovie;
	
	public void addBuyTicketListener(ActionListener actionListener) {
		buyTicket.addActionListener(actionListener);
	}
	
	public void addCancelTicketListener(ActionListener actionListener) {
		cancelTicket.addActionListener(actionListener);
	}
	
	public void addSearchMovieListener(ActionListener actionListener) {
		searchMovie.addActionListener(actionListener);
	}
	
	public void addLoginListener(ActionListener actionListener) {
		login.addActionListener(actionListener);
	}
	
	public void addRegisterListener(ActionListener actionListener) {
		register.addActionListener(actionListener);
	}

}
