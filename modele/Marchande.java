package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}
	
	public String percevoirRessourcesSpecifiques() {
		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
					
					if(super.getJoueur().getCite()[i].getType().equals("COMMERCANT")) {
						super.getJoueur().ajouterPieces(1);
					}
		}
		
		return "";
		
	}
	
	public void utiliserPouvoir() {
		
		super.getJoueur().ajouterPieces(1);
	}

}
