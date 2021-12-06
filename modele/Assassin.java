package modele;

import controleur.Interaction;

public class Assassin extends Personnage{

	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}
	
	public void utiliserPouvoir() {
		boolean continu = true;
		System.out.println("Quel personnage voulez-vous assassiner : ");
		for(int i = 0; i<super.getPlateau().getNombrePersonnages();i++) {
			System.out.println((i+1) +" - " + super.getPlateau().getPersonnage(i).getNom());			
		}
		
		do {
			System.out.println("Votre choix : ");
			int entier = Interaction.lireUnEntier(1,(super.getPlateau().getNombrePersonnages()+1));
			entier--;
			if(entier==0) {
				System.out.println("Vous ne pouvez pas vous assassiner.");
			}else {
				System.out.println("Vous assassinez : " + super.getPlateau().getPersonnage(entier).getNom());
				super.getPlateau().getPersonnage(entier).setAssassine();
				continu = false;
			}
		}while(continu);
	}

}
