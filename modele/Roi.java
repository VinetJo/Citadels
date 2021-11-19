package modele;

public class Roi extends Personnage{
	

	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
	}
	
	
	public void utiliserPouvoir() {
		boolean possedeC = true;
		super.getJoueur().setPossedeCouronne(possedeC);
		System.out.println("Je prend la couronne");
	}
	
	public String percevoirRessourcesSpecifiques() {
		int compteur =0;
		for(int j=0; j<super.getJoueur().nbQuartiersDansCite(); j++) {
			if(super.getJoueur().getCite()[j].getCaracteristiques().equals("noble")) {
				compteur++;
			}
		}
		super.getJoueur().ajouterPieces(compteur);
		
		return "Ajout de" + compteur + " pieces dans le trésor avec les quartier nobles";
		
	}
	
	

}
