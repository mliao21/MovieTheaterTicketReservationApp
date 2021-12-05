//package ensf614project.src.model;
package ensf614project.src.model;

public class Credit {
	private String code;

	private int balance;
	
	public Credit(String code, int balance) {
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
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
