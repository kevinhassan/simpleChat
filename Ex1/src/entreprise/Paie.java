package entreprise;

import entreprise.Commercial;
import entreprise.EmployeH;
import java.util.ArrayList;

public class Paie {

	public static void main (String[] args) {
		ArrayList<Employe> employes =  new ArrayList<Employe>();
		
		Commercial c1 = new Commercial("John");
		c1.setInfoSalaire(20000, 2000);
		employes.add(c1);
		Commercial c2 = new Commercial("Smith");
		c2.setInfoSalaire(20000, 1800);
		employes.add(c2);
		Commercial c3 = new Commercial("Jager");
		c3.setInfoSalaire(20000, 1600);
		employes.add(c3);
		
		EmployeH e1 = new EmployeH("Peter");
		e1.setInfoSalaire(2, 100, 30); // 2h sup � 30% de plus pour 10�50 de l'heure
		employes.add(e1);

		EmployeH e2 = new EmployeH("Parker", 5, 80 ,50);
		employes.add(e2);
		
		for(int i = 0 ; i< employes.size(); i++) {
			System.out.println(employes.get(i).getName()+ " gagne "+employes.get(i).getSalaire()+ "€");
		}
	}
}