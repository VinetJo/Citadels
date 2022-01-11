package modele;

public class Roi extends Personnage{
	

	public Roi() {
		super("Roi",4,Caracteristiques.ROI);
	}
	
	
	public void utiliserPouvoir() {
		boolean possedeC = true;
		if(super.getJoueur()!=null) {
			super.getJoueur().setPossedeCouronne(possedeC);
			System.out.println("Je prend la couronne");
		}
	}
	
	public void utiliserPouvoirAvatar() {
		boolean possedeC = true;
		if(super.getJoueur()!=null) {
			super.getJoueur().setPossedeCouronne(possedeC);
			System.out.println("Je prend la couronne");
		}
	}
	
	public String percevoirRessourcesSpecifiques() {
		int compteur =0;
		if(super.getJoueur()!=null) {
			System.out.println(super.getJoueur().nbQuartiersDansCite());
			for(int j=0; j<super.getJoueur().nbQuartiersDansCite(); j++) {
				if(super.getJoueur().getCite()[j].getType().equals("NOBLE")) {
					compteur++;
					System.out.println("test");
				}
			}
			super.getJoueur().ajouterPieces(compteur);
		}
		
		
		return "Ajout de" + compteur + " pieces dans le trésor avec les quartier nobles";
		
	}
	
	

}
