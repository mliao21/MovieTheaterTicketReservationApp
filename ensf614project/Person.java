package ensf614project;

public abstract class Person {
	protected String name;
	protected String email;
	protected Ticket[] tickets;
	public Person(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	abstract public Ticket getTicket(int creditCard);
	abstract public void refund(Ticket ticket, int creditCard); //if ticket found, 
	
	
	
	

}
