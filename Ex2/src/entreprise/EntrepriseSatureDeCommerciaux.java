package entreprise;

public class EntrepriseSatureDeCommerciaux extends Exception {
	private Entreprise entrepriseFailure;
	
	EntrepriseSatureDeCommerciaux(Entreprise e){
		super("Impossible d'ajouter un commercial à cette entreprise car elle ne peut contenir que " + e.getNbMaxCommerciaux()+ " commerciaux");
		this.entrepriseFailure = e;
	}
}
