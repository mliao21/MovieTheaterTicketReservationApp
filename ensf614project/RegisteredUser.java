package ensf614project;

public class RegisteredUser extends Person{
	private int creditcard;

	public RegisteredUser(String name, String email, int creditcard) {
		super(name, email);
		this.creditcard = creditcard;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ticket getTicket(int creditCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refund(Ticket ticket, int creditCard) {
		// TODO Auto-generated method stub
		
	}

	

}
