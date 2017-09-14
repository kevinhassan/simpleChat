package entreprise;

import fr.polymontp.hassan.entreprise.Commercial;
import fr.polymontp.hassan.entreprise.Employe;
import fr.polymontp.hassan.entreprise.EmployeH;

public class Entreprise {

	private int nbCommerciauxCurrent = 0;
	private int nbMax;
	private int nbCommerciauxMax;
	private Employe[] employes;
	
	public Entreprise(int nbMaxEmployes, int nbCommerciauxMax){
		this.nbCommerciauxMax = nbCommerciauxMax;
		this.nbMax = nbMaxEmployes;
		this.employes = new Employe[nbMaxEmployes];
	}
	
	public static void main (String[] args) {
		Entreprise e = new Entreprise(5,3);
		
		Commercial c1 = new Commercial("John");
		c1.setInfoSalaire(20000, 2000);		
		Commercial c2 = new Commercial("Smith");
		c2.setInfoSalaire(20000, 1800);
		Commercial c3 = new Commercial("Jager");
		c3.setInfoSalaire(20000, 1600);
		Commercial c4 = new Commercial("LOL");
		c4.setInfoSalaire(20000, 1800);
		
		EmployeH e1 = new EmployeH("Peter");
		e1.setInfoSalaire(2, 100, 30); // 2h sup � 30% de plus pour 10�50 de l'heure
		EmployeH e2 = new EmployeH("Parker", 5, 80 ,50);

		try {
			e.ajouter(c1);
			e.ajouter(c2);
			e.ajouter(c3);
			e.ajouter(c4);
			
			e.ajouter(e1);
			e.ajouter(e2);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void ajouter(Employe e) throws Exception{
		if(e instanceof Commercial){
			if( this.nbCommerciauxCurrent >= this.nbCommerciauxMax){
				throw new EntrepriseSatureDeCommerciaux(this);
			}
			else{
				int i =0;
				while (this.employes[i]!= null && i<this.nbMax){
					i ++;
				}
				if(i<this.nbMax){
					this.employes[i]=e;
					this.nbCommerciauxCurrent = nbCommerciauxCurrent+1;
				}
			}
		}
	}
	public int getNbMaxCommerciaux(){
		return this.nbCommerciauxMax;
	}
}