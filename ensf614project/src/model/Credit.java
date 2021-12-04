package ensf614project.src.model;
//package model;

public class Credit {
	private String code;

	private double balance;
	public Credit(double balance) {
		super();
		this.balance = balance;
	}
	public Credit(String code, double balance) {
		super();
		this.code = code;
		this.balance = balance;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Credit{" +
				"code='" + code + '\'' +
				", balance=" + balance +
				'}';
	}


}
