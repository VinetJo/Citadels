package modele;

public class Architecte extends Personnage{

	public Architecte() {
		super("Architecte", 7, Caracteristiques.ARCHITECTE);
	}
	
	public void utiliserPouvoir() {
		
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());	
		
	}
	
	public void utiliserPouvoirAvatar() {
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());	
	}
	public void utiliserPouvoirGraph() {
		
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
		super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());	
		
	}

}
