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

public class SignUpView extends JFrame{

	private JTextField firstName, lastName, emailAddress, password;
	private JButton enterPaymentButton, registerButton, mainMenuButton;
	private JLabel firstNameLabel, lastNameLabel, emailAddressLabel, passwordLabel;
	private JFrame frame;

	public SignUpView() {
		
		frame = new JFrame("SignUp");
		frame.setBounds(100, 100, 423, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 401, 306);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setForeground(new Color(255, 255, 255));
		firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		firstNameLabel.setBounds(38, 56, 88, 14);
		panel.add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lastNameLabel.setBounds(38, 92, 88, 14);
		panel.add(lastNameLabel);
		
		emailAddressLabel = new JLabel("Email Address:");
		emailAddressLabel.setForeground(Color.WHITE);
		emailAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailAddressLabel.setBounds(38, 130, 88, 14);
		panel.add(emailAddressLabel);
		
		passwordLabel = new JLabel("Password:");
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
		
		enterPaymentButton = new JButton("Make Payment");
		enterPaymentButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		enterPaymentButton.setBounds(133, 210, 116, 23);
		panel.add(enterPaymentButton);
		
		registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerButton.setBounds(133, 254, 116, 23);
		panel.add(registerButton);
		
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mainMenuButton.setBounds(264, 11, 116, 23);
		panel.add(mainMenuButton);
		
	}

	public JFrame getFrame() {
		return frame;
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
		return enterPaymentButton;
	}


	public JButton getRegister() {
		return registerButton;
	}


	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	public JLabel getLastNameLabel() {
		return lastNameLabel;
	}

	public JLabel getEmailAddressLabel() {
		return emailAddressLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public JButton getEnterPaymentButton() {
		return enterPaymentButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getMainMenuButton() {
		return mainMenuButton;
	}

	public void setEnterPaymentButton(JButton enterPaymentButton) {
		this.enterPaymentButton = enterPaymentButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public void setMainMenuButton(JButton mainMenuButton) {
		this.mainMenuButton = mainMenuButton;
	}

	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public void setLastNameLabel(JLabel lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	public void setEmailAddressLabel(JLabel emailAddressLabel) {
		this.emailAddressLabel = emailAddressLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
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
		this.enterPaymentButton = enterPayment;
	}


	public void setRegister(JButton register) {
		this.registerButton = register;
	}
	
	public void addRegisterButtonListener(ActionListener actionListener) {
		registerButton.addActionListener(actionListener);
	}
	
	public void addEnterPaymentButtonListener(ActionListener actionListener) {
		enterPaymentButton.addActionListener(actionListener);
	}
	
	public void addMainMenuButtonListener(ActionListener actionListener) {
		mainMenuButton.addActionListener(actionListener);
	}

}
