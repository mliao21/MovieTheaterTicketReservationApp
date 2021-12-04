//package model;
package ensf614project.src.model;

import java.util.ArrayList;

public class Subscribers implements Observer {
	private Subject subject;
	private ArrayList<String> subscriberList(){
		return null;
	}
	
	
	
	public Subscribers(Subject subject) {
		super();
		this.subject = subject;
	}


	@Override
	public void update(String movieInfo) {
		System.out.println("Message: " + movieInfo + " Has been sent to Subscriber list");

	}

}
