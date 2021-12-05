package ensf614project.src.model;
//package ensf614project.src.model;

import java.util.ArrayList;

public class MovieNotification implements Subject {
	
	private ArrayList<Observer> observers;
	
	
	

	public MovieNotification() {
		super();
		
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		if(o == null) {
			throw new NullPointerException("Observer not found");
		}
			
		if(this.observers.contains(o) == false) {
			this.observers.add(o);
		}

	}

	@Override
	public void removeObserver(Observer o) {
		if(this.observers.contains(o)) {
			this.observers.remove(o);
		}

	}

	

	@Override
	public void notifyAllObservers(String msg) {
		for(Observer observer:this.observers) {
			observer.update(msg);
		}
		
	}

}
