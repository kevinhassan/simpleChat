package entreprise;

public class EmployeH extends Employe{
	private double nbHeure;
	private double tauxH;
	private double pourcentage;
	
	public EmployeH(String nom) {
		super(nom);
	}
	
	public EmployeH(String nom,double nbHeure,double tauxH,double pourcentage) {
		super(nom);
		this.nbHeure = nbHeure;
		this.tauxH = tauxH;
		this.pourcentage = pourcentage;
	}
	
	public double getSalaire(){
		return (this.tauxH*(1+this.pourcentage/100)*this.nbHeure+this.tauxH*35);
	}
	
	public void setInfoSalaire(double nbHeure,double tauxH,double pourcentage){
		this.nbHeure = nbHeure;
		this.tauxH = tauxH;
		this.pourcentage = pourcentage;
		
	}
}
