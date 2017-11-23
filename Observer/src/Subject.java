public interface Subject {
	// tell the Subject you are interested in changes
	public void registerInterest(Observer obs);
}
