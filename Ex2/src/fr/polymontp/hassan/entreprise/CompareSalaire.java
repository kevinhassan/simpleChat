package fr.polymontp.hassan.entreprise;

import java.util.Comparator;

public class CompareSalaire implements Comparator {
	public final static int ASCENDING_ORDER = 1; 
	public final static int DESCENDING_ORDER = -1; 
	private int sign;
	public CompareSalaire(int sign) {
		super();
		this.sign = sign;
	}
	public int compare(Object e1, Object e2){
			double s1 = ((Employe) e1).getSalaire();
			double s2 = ((Employe) e2).getSalaire();
			
			return (int) (s1-s2)*sign;
	}
}
