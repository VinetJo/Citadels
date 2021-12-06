package modele;

import controleur.Interaction;

public class Voleur extends Personnage {

	public Voleur() {
		super("Voleur", 2, Caracteristiques.VOLEUR);
	}
	
	public void utiliserPouvoir() {
		boolean continu = true;
		System.out.println("Quel personnage voulez-vous voler : ");
		for(int i = 0; i<super.getPlateau().getNombrePersonnages();i++) {
			System.out.println((i+1) +" - " + super.getPlateau().getPersonnage(i).getNom());			
		}
		
		do {
			System.out.println("Votre choix : ");
			int entier = Interaction.lireUnEntier(1,(super.getPlateau().getNombrePersonnages()+1));
			entier--;
			if(super.getPlateau().getPersonnage(entier).getNom().equals("Voleur")) {
				System.out.println("Vous ne pouvez pas vous voler.");
			}else if(super.getPlateau().getPersonnage(entier).getRang()==1) {
				System.out.println("Impossible de voler un personnage rang 1");
			}else {
				System.out.println("Vous volez : " + super.getPlateau().getPersonnage(entier).getNom());
				super.getPlateau().getPersonnage(entier).setVole();
				super.getJoueur().ajouterPieces(super.getPlateau().getJoueur(entier).nbPieces());
				super.getPlateau().getJoueur(entier).retirerPieces(super.getPlateau().getJoueur(entier).nbPieces());
				continu = false;
			}
		}while(continu);
		
	}
}
