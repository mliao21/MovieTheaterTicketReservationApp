//package model;
package ensf614project.src.model;

import java.util.ArrayList;

public class Subscribers implements Observer {
	private Subject subject;
	private ArrayList<String> subscriberList;
	
	
	
	
	
	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public ArrayList<String> getSubscriberList() {
		return subscriberList;
	}


	public void setSubscriberList(ArrayList<String> subscriberList) {
		this.subscriberList = subscriberList;
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
