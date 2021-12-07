//package model;
package ensf614project.src.model;

public interface Subject {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyAllObservers(String msg);

}
