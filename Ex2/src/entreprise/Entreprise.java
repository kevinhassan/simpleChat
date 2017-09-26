package entreprise;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;






import fr.polymontp.hassan.entreprise.Commercial;
import fr.polymontp.hassan.entreprise.Employe;
import fr.polymontp.hassan.entreprise.EmployeH;

public class Entreprise {

	private String nom;
	private int nbCommerciauxCurrent = 0;
	private int nbMax;
	private int nbCommerciauxMax;
	private ArrayList <Employe> employes;
	
	public ArrayList<Employe> getEmployes() {
		return employes;
	}

	public Entreprise(String nom, int nbMaxEmployes, int nbCommerciauxMax){
		
		this.nom = nom;
		this.nbCommerciauxMax = nbCommerciauxMax;
		this.nbMax = nbMaxEmployes;
		this.employes = new ArrayList <Employe>();
	}
	
	public void enregistreToi(Commercial c) {
		File file = new File("Commercial.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(c.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void enregistreToiBinary(Commercial c) {
		File file = new File("Commercial_Binary.txt");
		
		try {
			file.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			for(int i=0; i<c.toDouble().length; i++){
				dos.writeDouble(c.toDouble()[i]);
			}
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public String[] lire() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("commercial.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			String[] caracts = line.split("\\|");
			bufferedReader.close();
			return caracts;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	public String lireBinary() {
		//FileReader fileReader = null;
		try {
			InputStream is = null;
	      	DataInputStream dis = null;
	      	String output = "";
			is = new FileInputStream("commercial_binary.txt");
			dis = new DataInputStream(is);
			while(dis.available()>0) {
	             // read character
	             double c = dis.readDouble();
	             output += Double.toString(c);
	             // print
	             System.out.print(c + " ");
	          }
			return output;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated ca Iterator itr = al.iterator();tch block
			e.printStackTrace();
		}
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	public static void main (String[] args) {
		Entreprise e = new Entreprise("Roussel",5,3);
		
		Commercial c1 = new Commercial("John");
		c1.setInfoSalaire(20000, 2000);		
		Commercial c2 = new Commercial("Smith");
		c2.setInfoSalaire(20000, 1800);
		Commercial c3 = new Commercial("Jager");
		c3.setInfoSalaire(20000, 1600);
		Commercial c4 = new Commercial("Titi");
		c4.setInfoSalaire(20000, 1800);
		
		EmployeH e1 = new EmployeH("Peter");
		e1.setInfoSalaire(2, 100, 30); // 2h sup � 30% de plus pour 10�50 de l'heure
		EmployeH e2 = new EmployeH("Parker", 5, 80 ,50);
		try {
			e.ajouter(c1);
			e.ajouter(c2);
			e.ajouter(c3);
			e.enlever(c3);
			e.ajouter(c4);
			
			e.ajouter(e1);
			e.ajouter(e2);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		System.out.println(e);
		Iterator itr = e.iterEmployes();
		double salaire = 0;
		while (itr.hasNext()) {
		      Employe element = (Employe) itr.next();
		      salaire = salaire + element.getSalaire();
		    }
		System.out.println("Salaire total : "+salaire+"€");
		Collections.sort(e.getEmployes());
		System.out.println(e);
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void ajouter(Employe e) throws Exception{
		if(e instanceof Commercial){
			if( this.nbCommerciauxCurrent >= getNbMaxCommerciaux()){
				throw new EntrepriseSatureDeCommerciaux(this);
			}
			else{
				this.nbCommerciauxCurrent = this.nbCommerciauxCurrent + 1;
			}
		}
		this.employes.add(e);
		
	}
	
	public void enlever(Employe e) {
		boolean answer = this.employes.remove(e);
		if (answer){
			this.nbCommerciauxCurrent = this.nbCommerciauxCurrent -1;
		}
		
	}
	public int getNbMaxCommerciaux(){
		return this.nbCommerciauxMax;
	}
	
	public String toString() {
		String answer = "Nom Entreprise : " +this.getNom()+"\n";
		for (Employe e : this.employes){
			answer = answer + "Employé : " + e.getName() + "\n";
		}
		return answer;
	}
	
	
	public Iterator<Employe> iterEmployes(){
		return this.employes.iterator();
	}

}