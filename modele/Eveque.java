/**
 * 
 */
package modele;


public class Eveque extends Personnage{

	
	public Eveque() {
		super("Eveque", 5, Caracteristiques.EVEQUE);
	}
	
	public String percevoirRessourcesSpecifiques() {

		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
			
			if(super.getJoueur().getCite()[i].getType().equals("RELIGIEUX")) {
				super.getJoueur().ajouterPieces(1);
			}
		}
		
		return "";
		
	}

}
