package fr.polymontp.hassan.entreprise;

import java.util.ArrayList;

import fr.polymontp.hassan.entreprise.Employe;
import fr.polymontp.hassan.entreprise.Commercial;

public class Directeur extends Employe{

	private double sommeFixe; 
	private static Directeur instance = null;
	private ArrayList<Commercial> commerciaux;
	
	private Directeur(String nom, double sommeFixe, ArrayList<Commercial> commerciaux) {
		super(nom);
		this.sommeFixe = sommeFixe;
		this.commerciaux = commerciaux;
		
	}
	public static Directeur creerDirecteur(String nom, double sommeFixe, ArrayList<Commercial> commerciaux){
		if(Directeur.instance == null){
			return new Directeur(nom, sommeFixe, commerciaux);
		}else{
			return Directeur.instance;
		}
	}
	public double getSalaire(){
		double gains = 0; 
		for (Commercial c : this.commerciaux){
			gains += c.getCA();
		}
		return this.sommeFixe + (0.4/100 *gains);
	}
}
