/**
 * 
 */
package modele;


public class Eveque extends Personnage{

	
	public Eveque() {
		super("Eveque", 5, Caracteristiques.EVEQUE);
	}
	
	public String percevoirRessourcesSpecifiques() {
		int compteur =0;
		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
			
			if(super.getJoueur().getCite()[i].getType().equals("RELIGIEUX")) {
				super.getJoueur().ajouterPieces(1);
				compteur++;
			}
		}
		
		return "Ajout de " + compteur + " pièces dans le trésor avec les quartiers Religieux";
		
	}

}
