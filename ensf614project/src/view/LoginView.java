package ensf614project.src.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	
	private JTextField emailAddress;
	private JTextField password;
	private JButton loginButton;
	private JFrame frame;

	public LoginView() {
		
		frame = new JFrame("Login");
		frame.setBounds(100, 100, 400, 246);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 364, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel emailAddressLabel = new JLabel("Email Address:");
		emailAddressLabel.setForeground(new Color(255, 255, 255));
		emailAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailAddressLabel.setBounds(28, 57, 102, 14);
		panel.add(emailAddressLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(28, 93, 102, 14);
		panel.add(passwordLabel);
		
		emailAddress = new JTextField();
		emailAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailAddress.setBounds(140, 54, 156, 20);
		panel.add(emailAddress);
		emailAddress.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setColumns(10);
		password.setBounds(140, 90, 156, 20);
		panel.add(password);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(120, 140, 89, 23);
		panel.add(loginButton);
		
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public JTextField getEmailAddress() {
		return emailAddress;
	}


	public JTextField getPassword() {
		return password;
	}


	public JButton getLoginButton() {
		return loginButton;
	}


	public void setEmailAddress(JTextField emailAddress) {
		this.emailAddress = emailAddress;
	}


	public void setPassword(JTextField password) {
		this.password = password;
	}


	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
	
	public void addLoginButtonListener(ActionListener actionListener) {
		loginButton.addActionListener(actionListener);
	}

}
