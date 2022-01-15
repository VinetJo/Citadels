package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}
	
	public String percevoirRessourcesSpecifiques() {
		int compteur=0;
		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
					
					if(super.getJoueur().getCite()[i].getType().equals("COMMERCANT")) {
						super.getJoueur().ajouterPieces(1);
						compteur ++;
					}
		}
		
		return "Ajout de " + compteur + " pièces dans le trésor pour les quartiers Commerçant";
		
	}
	
	public void utiliserPouvoir() {
		
		super.getJoueur().ajouterPieces(1);
	}
	
	public void utiliserPouvoirAvatar() {
		
		super.getJoueur().ajouterPieces(1);
	}

}
