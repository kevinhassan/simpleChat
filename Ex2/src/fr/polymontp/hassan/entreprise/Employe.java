package fr.polymontp.hassan.entreprise;

public abstract class Employe implements Comparable<Object>{
	private final String nom;
	
	public Employe(String nom){
		this.nom = nom;
	}
	public String getName(){
		return this.nom;
	}
	public abstract double getSalaire();
	
	public int compareTo(Object e2){
		return -((Employe) e2).getName().compareTo(this.getName());
	}
}

