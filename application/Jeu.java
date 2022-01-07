package application;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;
import modele.*;

public class Jeu {
	
	private PlateauDeJeu plateauDeJeu = new PlateauDeJeu(); 
	private int numeroConfiguration;
	private Random generateur = new Random();
	
	public Jeu(PlateauDeJeu plateauDeJeu, int numeroConfiguration) {
		this.plateauDeJeu = plateauDeJeu;
		this.numeroConfiguration = numeroConfiguration;
		this.generateur = new Random();
	}
	
	public void jouer() {
		System.out.println("Bienvenue dans le jeu citadelle");
		boolean continu=true;
		do {
		System.out.println("Que souhaitez-vous faire :");
		System.out.println("1 - Afficher les règles");
		System.out.println("2 - Jouer une partie");
		System.out.println("3 - Quitter le jeu");
		int temp = Interaction.lireUnEntier(1, 4);
		if(temp==1) {
			afficherLesRegles();
		}else if(temp==2) {
			jouerPartie();
		}else if(temp==3) {
			System.out.println("Au revoir");
			continu = false;
		}
		}while(continu);
	}
	
	private void afficherLesRegles() {
		System.out.println("Voici les règles :");
	}
	
	private void jouerPartie() {
		System.out.println("Vous débutez une partie");
		initialisation();
		do {
			tourDeJeu();
			gestionCouronne();
			reinitianilisationPersonnages();			
		}while(!partieFinie());
		
	}
	
	private void initialisation() {
		System.out.println(" - INITIALISATION - ");
		Pioche pioche  = Configuration.nouvellePioche();
		this.plateauDeJeu = Configuration.configurationDeBase(pioche);
		for(int i=0; i<4; i++) {
			this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for(int j = 0; j<4; j++) {
				this.plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
			}			
		}
		this.plateauDeJeu.getJoueur(0).setPossedeCouronne(true);
		
		
	}
	
	private void gestionCouronne() {
		
	}
	
	private void reinitianilisationPersonnages() {
		
	}
	
	private boolean partieFinie() {
		
		return true;
		
	}
	
	private void tourDeJeu() {
		
	}
	
	private void choixPersonnage() {
		ArrayList<Personnage> listePerso = new ArrayList<Personnage>();
		//Ajout perso dans une liste vide
		for(int i = 0; i<this.plateauDeJeu.getNombrePersonnages(); i++) {
			listePerso.add(this.plateauDeJeu.getPersonnage(i));
		}
		System.out.println("Choix des personnages : ");
		//Mise de coté cartes
		int temp = generateur.nextInt(generateur.nextInt(listePerso.size()+1));
		System.out.println("Le personnage '" + listePerso.get(temp).getNom() + "' est écarté face visible");	
		listePerso.remove(temp);
		for(int j=0; j<2; j++) {
			System.out.println("Un personnage est écarté face caché");	
			listePerso.remove(generateur.nextInt(listePerso.size()+1));
		}
		
		for(int k = 0; k<this.plateauDeJeu.getNombreJoueurs();k++) {
			//Si le joueur principal à la couronne
			if(this.plateauDeJeu.getJoueur(k).getPossedeCouronne()==true && this.plateauDeJeu.getJoueur(k).equals(this.plateauDeJeu.getJoueur(0))) {
				System.out.println("Vous avez la couronne ! ");
				for(int l = 0; l<listePerso.size(); l++) {
					System.out.println(listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
				}
				System.out.println("Quel personnage choisissez-vous ?");
				boolean continu;
				int temp3;
				//do {
					continu = false;
					temp3 = Interaction.lireUnEntier(1, listePerso.size()+1);
					temp3--;
					/*
					if(!listePerso.get(temp3).equals(this.plateauDeJeu.getPersonnage(temp3))) {
						System.out.println("Mauvaise saisie veuillez recommencer");
						continu = true;
					}*/
					
				//}while(continu);
				for(int m = 0; m<this.plateauDeJeu.getNombrePersonnages();m++) {
					if(listePerso.get(temp3).equals(this.plateauDeJeu.getPersonnage(m))) {
						this.plateauDeJeu.getPersonnage(m).setJoueur(this.plateauDeJeu.getJoueur(0));
						listePerso.remove(temp3);
					}
				}
				for(int n = 1; n<this.plateauDeJeu.getNombreJoueurs(); n++) {
					int temp4 = generateur.nextInt(listePerso.size());
					for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
						if(listePerso.get(temp4).equals(this.plateauDeJeu.getPersonnage(o))) {
							this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(n));
							listePerso.remove(temp4);
						}
					}
				}
				
			}else if(this.plateauDeJeu.getJoueur(k).getPossedeCouronne()==true) {
				//Si un bot à la couronne
				int temp5 = generateur.nextInt(listePerso.size());
				for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
					if(listePerso.get(temp5).equals(this.plateauDeJeu.getPersonnage(o))) {
						this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(k));
						listePerso.remove(temp5);
					}
				}
				
				for(int n = 0; n<this.plateauDeJeu.getNombreJoueurs(); n++) {
					if(n==0) {
						System.out.println("C'est à votre tour");
						for(int l = 0; l<listePerso.size(); l++) {
							System.out.println((l+1)+" : "+listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
						}
						System.out.println("Quel personnage choisissez-vous ?");
						int temp3 = Interaction.lireUnEntier(1, listePerso.size()+1);
						temp3--;
							
						for(int m = 0; m<this.plateauDeJeu.getNombrePersonnages();m++) {
							if(listePerso.get(temp3).equals(this.plateauDeJeu.getPersonnage(m))) {
								this.plateauDeJeu.getPersonnage(m).setJoueur(this.plateauDeJeu.getJoueur(0));
								listePerso.remove(temp3);
							}
						}
					
					}else if(n!=k){
						int temp4 = generateur.nextInt(listePerso.size());
						for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
							if(listePerso.get(temp4).equals(this.plateauDeJeu.getPersonnage(o))) {
								this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(n));
								listePerso.remove(temp4);
							}
						}
					}
				}
				
				
			}
		}
		
		
		System.out.println("Tout le monde à son personnage attribué !");
		
		
		
		
	}
	
	private void percevoirRessource() {
		
	}
	
	private void calculDesPoints() {
		
	}
	

}
