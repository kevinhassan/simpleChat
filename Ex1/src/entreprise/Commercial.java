package entreprise;

public class Commercial extends Employe { 
	private double ca; 
	private double sommeFixe;
	
	public Commercial (String nom){
		super(nom);
	}
	
	
	public Commercial (String nom, double ca, double sommeFixe){
		super(nom);
		this.ca = ca;
		this.sommeFixe = sommeFixe;
	}
	
	public double getSalaire(){
		return (0.01*this.ca+this.sommeFixe);
	}
	
	public void setInfoSalaire(double ca,double sommeFixe){
		this.ca = ca;
		this.sommeFixe = sommeFixe;
	}
}
