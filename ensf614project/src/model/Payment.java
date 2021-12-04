//package model;
package ensf614project.src.model;

import java.time.LocalDate;

public class Payment {
	private Ticket ticket;
	private double creditDiscount;
	public Payment(Ticket ticket, double creditDiscount) {
		super();
		this.ticket = ticket;
		this.creditDiscount = creditDiscount;
	}
	
	public void pay(String creditCardNum, String exp, int cvv) {
		LocalDate today = LocalDate.now();
		LocalDate expiry = LocalDate.parse(exp);
		
		if(expiry.getYear()>today.getYear()) {
			System.out.println("Paid Successfully");
		} else if(expiry.getYear()==today.getYear() && expiry.getMonthValue()>today.getMonthValue()) {
			System.out.println("Paid Successfully");
		} else {
			System.out.println("Payment Failure");
		}
	}

}
