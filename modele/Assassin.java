package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.GraphQuestion;
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
			if(super.getPlateau().getPersonnage(entier).getNom().equals("Assassin")) {
				System.out.println("Vous ne pouvez pas vous assassiner.");
			}else {
				
				if(super.getPlateau().getPersonnage(entier).getJoueur()!=null) {
					System.out.println("Vous assassinez : " + super.getPlateau().getPersonnage(entier).getNom());
					super.getPlateau().getPersonnage(entier).setAssassine();					
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
			
			if(!super.getPlateau().getPersonnage(entierRan).getNom().equals("Assassin")) {
				
				if(super.getPlateau().getPersonnage(entierRan).getJoueur()!=null) {
					System.out.println("Vous assassinez : " + super.getPlateau().getPersonnage(entierRan).getNom());
					super.getPlateau().getPersonnage(entierRan).setAssassine();
				}else {
					System.out.println("Aucun joueur ne possédait le personnage " + super.getPlateau().getPersonnage(entierRan).getNom());
				}
				
				continu = false;
				
			}
		}while(continu);
		
	}
	
	public void utiliserPouvoirGraph() throws InterruptedException {
		ArrayList<String> choixJoueur = new ArrayList<String>();
		boolean continu = true;
		System.out.println("Quel personnage voulez-vous assassiner : ");
		for(int i = 0; i<super.getPlateau().getNombrePersonnages();i++) {
			System.out.println((i+1) +" - " + super.getPlateau().getPersonnage(i).getNom());	
			choixJoueur.add(super.getPlateau().getPersonnage(i).getNom());
		}
		
		do {
			int entier = GraphQuestion.interfaceChoix("Quel personnage voulez-vous assassiner ? ",choixJoueur);
			if(super.getPlateau().getPersonnage(entier).getNom().equals("Assassin")) {
				System.out.println("Vous ne pouvez pas vous assassiner.");
			}else {
				
				if(super.getPlateau().getPersonnage(entier).getJoueur()!=null) {
					System.out.println("Vous assassinez : " + super.getPlateau().getPersonnage(entier).getNom());
					super.getPlateau().getPersonnage(entier).setAssassine();					
				}else {
					System.out.println("Aucun joueur ne possédait ce personnage");
				}

				continu = false;
			}
		}while(continu);
	}

}
