package fr.polymontp.hassan.entreprise;

public abstract class Employe {
	private final String nom;
	
	public Employe(String nom){
		this.nom = nom;
	}
	public String getName(){
		return this.nom;
	}
	public abstract double getSalaire();
}

