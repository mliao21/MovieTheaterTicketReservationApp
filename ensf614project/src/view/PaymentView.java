package ensf614project.src.view;

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

	private JTextField cardNumber, email, coupon;
	private JLabel cardNumberLabel, emailLabel, couponLabel;
	private JButton submitButton;
	private JFrame frame;
	
	public PaymentView() {
		
		frame = new JFrame("Payment");
		frame.setBounds(100, 100, 440, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 407, 309);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		cardNumberLabel = new JLabel("Card Number:");
		cardNumberLabel.setForeground(new Color(255, 255, 255));
		cardNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cardNumberLabel.setBounds(20, 92, 133, 14);
		panel.add(cardNumberLabel);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailLabel.setBounds(20, 133, 92, 14);
		panel.add(emailLabel);
		
		couponLabel = new JLabel("Coupon:");
		couponLabel.setForeground(Color.WHITE);
		couponLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		couponLabel.setBounds(20, 180, 73, 14);
		panel.add(couponLabel);
		
		cardNumber = new JTextField();
		cardNumber.setBounds(145, 90, 200, 20);
		panel.add(cardNumber);
		cardNumber.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(145, 131, 200, 20);
		panel.add(email);
		
		coupon = new JTextField();
		coupon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coupon.setColumns(10);
		coupon.setBounds(145, 178, 200, 20);
		panel.add(coupon);
		
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitButton.setBounds(146, 230, 92, 23);
		panel.add(submitButton);

	}
	
	public String getCardNumber() {
		return cardNumber.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public String getCoupon() {
		return coupon.getText();
	}

	public JLabel getCardNumberLabel() {
		return cardNumberLabel;
	}

	public JLabel getEmailLabel() {
		return emailLabel;
	}

	public JLabel getCouponLabel() {
		return couponLabel;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber.setText(cardNumber);
	}

	public void setEmail(String email) {
		this.email.setText(email);
	}

	public void setCoupon(String coupon) {
		this.coupon.setText(coupon);
	}

	public void setCardNumberLabel(JLabel cardNumberLabel) {
		this.cardNumberLabel = cardNumberLabel;
	}

	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	public void setCouponLabel(JLabel couponLabel) {
		this.couponLabel = couponLabel;
	}

	public JFrame getFrame() {
		return frame;
	}


	
	public void addSubmitButtonListener(ActionListener actionListener) {
		submitButton.addActionListener(actionListener);
	}
}
