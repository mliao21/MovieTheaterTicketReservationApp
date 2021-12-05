package View;

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

public class SignUpView {

	private JTextField firstName, lastName, emailAddress, password;
	private JButton enterPayment, register;


	private SignUpView() {
		
		JFrame frame = new JFrame("SignUpView");
		frame.setBounds(100, 100, 423, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 401, 306);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setForeground(new Color(255, 255, 255));
		firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		firstNameLabel.setBounds(38, 56, 88, 14);
		panel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lastNameLabel.setBounds(38, 92, 88, 14);
		panel.add(lastNameLabel);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmailAddress.setBounds(38, 130, 88, 14);
		panel.add(lblEmailAddress);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(38, 166, 88, 14);
		panel.add(passwordLabel);
		
		firstName = new JTextField();
		firstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstName.setBounds(154, 54, 189, 20);
		panel.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastName.setColumns(10);
		lastName.setBounds(154, 90, 189, 20);
		panel.add(lastName);
		
		emailAddress = new JTextField();
		emailAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailAddress.setColumns(10);
		emailAddress.setBounds(154, 128, 189, 20);
		panel.add(emailAddress);
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setColumns(10);
		password.setBounds(154, 164, 189, 20);
		panel.add(password);
		
		enterPayment = new JButton("Make Payment");
		enterPayment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		enterPayment.setBounds(133, 210, 116, 23);
		panel.add(enterPayment);
		
		register = new JButton("Register");
		register.setFont(new Font("Tahoma", Font.PLAIN, 12));
		register.setBounds(133, 254, 116, 23);
		panel.add(register);
		
		frame.setVisible(true);
	}


	public JTextField getFirstName() {
		return firstName;
	}


	public JTextField getLastName() {
		return lastName;
	}


	public JTextField getEmailAddress() {
		return emailAddress;
	}


	public JTextField getPassword() {
		return password;
	}


	public JButton getEnterPayment() {
		return enterPayment;
	}


	public JButton getRegister() {
		return register;
	}


	public void setFirstName(JTextField firstName) {
		this.firstName = firstName;
	}


	public void setLastName(JTextField lastName) {
		this.lastName = lastName;
	}


	public void setEmailAddress(JTextField emailAddress) {
		this.emailAddress = emailAddress;
	}


	public void setPassword(JTextField password) {
		this.password = password;
	}


	public void setEnterPayment(JButton enterPayment) {
		this.enterPayment = enterPayment;
	}


	public void setRegister(JButton register) {
		this.register = register;
	}
	
	// private JButton enterPayment, register;
	
	public void addRegisterListener(ActionListener actionListener) {
		register.addActionListener(actionListener);
	}
	
	public void addEnterPaymentListener(ActionListener actionListener) {
		enterPayment.addActionListener(actionListener);
	}	

}