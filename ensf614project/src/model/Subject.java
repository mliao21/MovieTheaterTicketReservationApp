package ensf614project.src.model;

/**
 * This class represents a subject for observer pattern
 */
public interface Subject {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyAllObservers(String msg);

}
