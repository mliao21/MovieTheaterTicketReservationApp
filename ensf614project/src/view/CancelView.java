package ensf614project.src.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CancelView extends JFrame{

	
	private JTextField ticketNumber, emailAddress;
	private JButton cancelButton, mainMenuButton;
	private JFrame frame;
	
	public CancelView() {
		
		frame = new JFrame("Cancel Ticket");
		frame.setBounds(100, 100, 450, 274);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 414, 213);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel ticketNumberLabel = new JLabel("Ticket Number:");
		ticketNumberLabel.setForeground(new Color(255, 255, 255));
		ticketNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		ticketNumberLabel.setBounds(26, 57, 98, 14);
		panel.add(ticketNumberLabel);
		
		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailLabel.setBounds(26, 102, 98, 14);
		panel.add(emailLabel);
		
		ticketNumber = new JTextField();
		ticketNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ticketNumber.setBounds(162, 55, 157, 20);
		panel.add(ticketNumber);
		ticketNumber.setColumns(10);
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		emailAddress.setBounds(162, 100, 157, 20);
		panel.add(emailAddress);
		
		cancelButton = new JButton("Cancel Ticket");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelButton.setBounds(144, 152, 125, 23);
		panel.add(cancelButton);
		
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mainMenuButton.setBounds(295, 11, 109, 23);
		panel.add(mainMenuButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTicketNumber() {
		return ticketNumber;
	}



	public JTextField getEmailAddress() {
		return emailAddress;
	}



	public JButton getCancelButton() {
		return cancelButton;
	}



	public JButton getMainMenuButton() {
		return mainMenuButton;
	}



	public void setTicketNumber(JTextField ticketNumber) {
		this.ticketNumber = ticketNumber;
	}



	public void setEmailAddress(JTextField emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	//private JButton cancelButton, mainMenuButton;
	
	public void addCancelButtonListener(ActionListener actionlistener) {
		cancelButton.addActionListener(actionlistener);
	}
	
	public void addMainMenuButtonListener(ActionListener actionlistener) {
		mainMenuButton.addActionListener(actionlistener);
	}
	
	
}
