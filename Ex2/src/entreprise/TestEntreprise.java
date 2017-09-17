package entreprise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import fr.polymontp.hassan.entreprise.Commercial;

public class TestEntreprise {

	public static void main (String[] args) {
		Entreprise e = new Entreprise(5,3);
		
		Commercial c1 = new Commercial("John");
		c1.setInfoSalaire(20000, 2000);	
		e.enregistreToi(c1);
		String[] caracts = e.lire();
		System.out.println(Arrays.toString(caracts));
	}
}
