package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class PaymentView extends JFrame{

	private JTextField cardNumber, cardExpiry, cardCvv, cardHolderFName, cardHolderLName;
	private JLabel cardNumberLabel, cardExpiryLabel, cardHolderFNameLabel, cardHolderLNameLabel, cardCvvLabel;
	private JButton submitButton;
	private JFrame frame;
	
	public PaymentView() {
		
		frame = new JFrame("Payment");
		frame.setBounds(100, 100, 491, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 455, 374);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		cardNumberLabel = new JLabel("Card Number:");
		cardNumberLabel.setForeground(new Color(255, 255, 255));
		cardNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardNumberLabel.setBounds(20, 92, 133, 14);
		panel.add(cardNumberLabel);
		
		cardExpiryLabel = new JLabel("Card Expiry:");
		cardExpiryLabel.setForeground(Color.WHITE);
		cardExpiryLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardExpiryLabel.setBounds(20, 133, 92, 14);
		panel.add(cardExpiryLabel);
		
		cardHolderFNameLabel = new JLabel("Card Holder First Name:");
		cardHolderFNameLabel.setForeground(Color.WHITE);
		cardHolderFNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardHolderFNameLabel.setBounds(20, 221, 160, 14);
		panel.add(cardHolderFNameLabel);
		
		cardHolderLNameLabel = new JLabel("Card Holder Last Name:");
		cardHolderLNameLabel.setForeground(Color.WHITE);
		cardHolderLNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardHolderLNameLabel.setBounds(20, 262, 160, 14);
		panel.add(cardHolderLNameLabel);
		
		cardCvvLabel = new JLabel("Card CVV:");
		cardCvvLabel.setForeground(Color.WHITE);
		cardCvvLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardCvvLabel.setBounds(20, 180, 73, 14);
		panel.add(cardCvvLabel);
		
		cardNumber = new JTextField();
		cardNumber.setBounds(187, 90, 200, 20);
		panel.add(cardNumber);
		cardNumber.setColumns(10);
		
		cardExpiry = new JTextField();
		cardExpiry.setColumns(10);
		cardExpiry.setBounds(187, 131, 86, 20);
		panel.add(cardExpiry);
		
		cardCvv = new JTextField();
		cardCvv.setColumns(10);
		cardCvv.setBounds(187, 178, 86, 20);
		panel.add(cardCvv);
		
		cardHolderFName = new JTextField();
		cardHolderFName.setColumns(10);
		cardHolderFName.setBounds(187, 219, 200, 20);
		panel.add(cardHolderFName);
		
		cardHolderLName = new JTextField();
		cardHolderLName.setColumns(10);
		cardHolderLName.setBounds(187, 260, 200, 20);
		panel.add(cardHolderLName);
		
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitButton.setBounds(181, 312, 92, 23);
		panel.add(submitButton);

	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getCardNumber() {
		return cardNumber;
	}


	public JTextField getCardExpiry() {
		return cardExpiry;
	}


	public JTextField getCardCvv() {
		return cardCvv;
	}


	public JTextField getCardHolderFName() {
		return cardHolderFName;
	}


	public JTextField getCardHolderLName() {
		return cardHolderLName;
	}


	public JLabel getCardNumberLabel() {
		return cardNumberLabel;
	}


	public JLabel getCardExpiryLabel() {
		return cardExpiryLabel;
	}


	public JLabel getCardHolderFNameLabel() {
		return cardHolderFNameLabel;
	}


	public JLabel getCardHolderLNameLabel() {
		return cardHolderLNameLabel;
	}


	public JLabel getCardCvvLabel() {
		return cardCvvLabel;
	}


	public JButton getSubmit() {
		return submitButton;
	}


	public void setCardNumber(JTextField cardNumber) {
		this.cardNumber = cardNumber;
	}


	public void setCardExpiry(JTextField cardExpiry) {
		this.cardExpiry = cardExpiry;
	}


	public void setCardCvv(JTextField cardCvv) {
		this.cardCvv = cardCvv;
	}


	public void setCardHolderFName(JTextField cardHolderFName) {
		this.cardHolderFName = cardHolderFName;
	}


	public void setCardHolderLName(JTextField cardHolderLName) {
		this.cardHolderLName = cardHolderLName;
	}


	public void setCardNumberLabel(JLabel cardNumberLabel) {
		this.cardNumberLabel = cardNumberLabel;
	}


	public void setCardExpiryLabel(JLabel cardExpiryLabel) {
		this.cardExpiryLabel = cardExpiryLabel;
	}


	public void setCardHolderFNameLabel(JLabel cardHolderFNameLabel) {
		this.cardHolderFNameLabel = cardHolderFNameLabel;
	}


	public void setCardHolderLNameLabel(JLabel cardHolderLNameLabel) {
		this.cardHolderLNameLabel = cardHolderLNameLabel;
	}


	public void setCardCvvLabel(JLabel cardCvvLabel) {
		this.cardCvvLabel = cardCvvLabel;
	}


	public void setSubmit(JButton submit) {
		this.submitButton = submit;
	}
	
	public void addSubmitButtonListener(ActionListener actionListener) {
		submitButton.addActionListener(actionListener);
	}
}
