package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.GraphQuestion;
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
				if(super.getPlateau().getPersonnage(entier).getJoueur()!=null) {
					int nbPiecesTemp=super.getPlateau().getPersonnage(entier).getJoueur().nbPieces();
					super.getPlateau().getPersonnage(entier).setVole();
					super.getJoueur().ajouterPieces(nbPiecesTemp);
					super.getPlateau().getPersonnage(entier).getJoueur().retirerPieces(nbPiecesTemp);
					System.out.println("Vous avez desormais : " + super.getJoueur().nbPieces() + " pieces dans votre trésor");
				}else {
					System.out.println("Aucun joueur ne possédait ce personnage");
				}
				continu = false;
			}
		}while(continu);
	}
	
	public void utiliserPouvoirAvatar() {
		Random ran = new Random();
		
		boolean continu = true;
		do {
			int entierRan = ran.nextInt(super.getPlateau().getNombrePersonnages());
			if(!super.getPlateau().getPersonnage(entierRan).getNom().equals("Voleur") && 
					super.getPlateau().getPersonnage(entierRan).getRang()!=1) {
				
				continu = false;
				
				if(super.getPlateau().getPersonnage(entierRan).getJoueur()!=null) {
					int nbPiecesTemp = super.getPlateau().getPersonnage(entierRan).getJoueur().nbPieces();
					System.out.println("Vous volez : " + super.getPlateau().getPersonnage(entierRan).getNom());
					super.getPlateau().getPersonnage(entierRan).setVole();
					super.getJoueur().ajouterPieces(nbPiecesTemp);
					super.getPlateau().getPersonnage(entierRan).getJoueur().retirerPieces(nbPiecesTemp);
					System.out.println("Vous avez desormais : " + super.getJoueur().nbPieces() + " pieces dans votre trésor");
				}else {
					System.out.println("Aucun joueur ne possédait ce personnage");
				}
			}
			
		}while(continu);
	}
	public void utiliserPouvoirGraph() throws InterruptedException {
		boolean continu = true;
		ArrayList<String> choixPersonnage = new ArrayList<String>();
		for(int i = 0; i<super.getPlateau().getNombrePersonnages();i++) {
			System.out.println((i+1) +" - " + super.getPlateau().getPersonnage(i).getNom());
			choixPersonnage.add(super.getPlateau().getPersonnage(i).getNom());
		}
		do {
			int entier = GraphQuestion.interfaceChoix("Quel personnage voulez-vous voler : ",choixPersonnage);
			
			if(super.getPlateau().getPersonnage(entier).getNom().equals("Voleur")) {
				System.out.println("Vous ne pouvez pas vous voler.");
			}else if(super.getPlateau().getPersonnage(entier).getRang()==1) {
				System.out.println("Impossible de voler un personnage rang 1");
			}else {
				if(super.getPlateau().getPersonnage(entier).getJoueur()!=null) {
					int nbPiecesTemp=super.getPlateau().getPersonnage(entier).getJoueur().nbPieces();
					super.getPlateau().getPersonnage(entier).setVole();
					super.getJoueur().ajouterPieces(nbPiecesTemp);
					super.getPlateau().getPersonnage(entier).getJoueur().retirerPieces(nbPiecesTemp);
					System.out.println("Vous avez desormais : " + super.getJoueur().nbPieces() + " pieces dans votre trésor");
				}else {
					System.out.println("Aucun joueur ne possédait ce personnage");
				}
				continu = false;
			}
		}while(continu);
	}
}
