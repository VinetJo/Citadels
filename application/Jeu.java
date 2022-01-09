package application;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;
import modele.*;

public class Jeu {
	
	private PlateauDeJeu plateauDeJeu = new PlateauDeJeu(); 
	private int numeroConfiguration;
	private Random generateur = new Random();
	
	public Jeu(int numeroConfiguration) {
		this.plateauDeJeu = new PlateauDeJeu(); 
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
		calculDesPoints();
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
		System.out.println("Attribution couronne nouveau roi sinon l'ancien roi garde sa couronne.");
		for(int i =0; i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if(this.plateauDeJeu.getJoueur(i).getPersonnage().getNom().equals("Roi")) {
				System.out.println("Attribution de la couronne au joueur " + this.plateauDeJeu.getJoueur(i).getNom());
				for(int j = 0; j<this.plateauDeJeu.getNombreJoueurs();j++) {
					if(this.plateauDeJeu.getJoueur(j).getPossedeCouronne()) {
						//Mise a false du dernier detenteur de la couronne
						this.plateauDeJeu.getJoueur(j).setPossedeCouronne(false);
					}
				this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
				}
			}
		}
		
	}
	
	private void reinitianilisationPersonnages() {
		System.out.println("- Reinitialisation des personnages -");
		for(int i =0 ; i<this.plateauDeJeu.getNombrePersonnages();i++) {
			this.plateauDeJeu.getPersonnage(i).setJoueur(null);
		}
	}
	
	private boolean partieFinie() {
		for(int i = 0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
			if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7) {
				System.out.println("Le joueur " + this.plateauDeJeu.getJoueur(i).getNom() + " possède une cité complète, la partie est finie");
				return true;
			}
		}
		return false;
		
	}
	
	private void tourDeJeu() {
		System.out.println("Debut du tour de jeu.");
		choixPersonnage();
		for(int i = 0; i<this.plateauDeJeu.getNombrePersonnages(); i++) {
			System.out.println("Le personnage " + this.plateauDeJeu.getPersonnage(i).getNom() + " est appelé" );
			if(this.plateauDeJeu.getPersonnage(i).getAssassine()) {
				System.out.println("Le personnage est assassiné, il ne peut pas jouer");
			}else {
				if(this.plateauDeJeu.getPersonnage(i).getVole()) {
					System.out.println("Le personnage est volé, le joueur perd ses pièces.");
				}
				//Personnage utilisateur appelé
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().equals(this.plateauDeJeu.getJoueur(0))) {
					System.out.println("Votre personnage est appelé");
					percevoirRessource(i);
					System.out.println("Recupération ressources spécifiques");
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					System.out.println("Souhaitez vous utiliser votre pouvoir ?");
					boolean tempB = Interaction.lireOuiOuNon();
					if(tempB) {
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
					}else {
						System.out.println("Votre pouvoir n'est pas utilisé");
					}
					System.out.println("Souhaitez vous construire un nouveaux quartier ?");
					tempB = Interaction.lireOuiOuNon();
					if(tempB) {
						System.out.println("Quel quartier voulez vous construire ?");
						for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size(); j++) {
							System.out.println((j+1)+" - "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j) + " "); 
						}
						if(this.plateauDeJeu.getPersonnage(i).getNom().equals("Architecte")) {
							System.out.println("Vous êtes architecte vous pouvez construire jursqu'à 3 quartiers");
							System.out.println("Combien voulez en construire : ");
							int temp2 = Interaction.lireUnEntier(0, 4);
							for(int k = 0; k< temp2; k++) {
								boolean continu = false;
								System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
								int temp = 0;
								do {
									temp = Interaction.lireUnEntier(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain());
									temp--;
									if(temp==-1) {
										System.out.println("Annulation construction");
										continu = false;
									} else if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp).getCout()>
									this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
										System.out.println("Impossible vous n'avez plus assez de pièces choisir un autre quartier.");
										continu = true;
									}else {
										Quartier quart =  new Quartier();
										quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
										this.plateauDeJeu.getPersonnage(i).construire(quart);
										continu = false;
									}
								}while(continu);
							}
						}else {
							boolean continu = false;
							System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
							int temp = 0;
							do {
								temp = Interaction.lireUnEntier(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain());
								temp--;
								if(temp==-1) {
									System.out.println("Annulation construction");
									continu = false;
								} else if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp).getCout()>
								this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									System.out.println("Impossible vous n'avez plus assez de pièces choisir un autre quartier.");
									continu = true;
								}else {
									Quartier quart =  new Quartier();
									quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
									this.plateauDeJeu.getPersonnage(i).construire(quart);
									continu = false;
								}
							}while(continu);						
						}
					}
					//Bot appelé
				}else {
					percevoirRessource(i);
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					System.out.println("Souhaitez vous utiliser votre pouvoir ?");
					boolean tempB = generateur.nextBoolean();
					System.out.println(tempB);
					if(tempB) {
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoirAvatar();
					}else {
						System.out.println("Le pouvoir n'est pas utilisé");
					}
					System.out.println("Souhaitez vous construire un nouveaux quartier ?");
					tempB =  generateur.nextBoolean();
					System.out.println(tempB);
					if(tempB) {
						System.out.println("Quel quartier voulez vous construire ?");
						if(this.plateauDeJeu.getPersonnage(i).getNom().equals("Architecte")) {
							System.out.println("Vous êtes architecte vous pouvez construire jursqu'à 3 quartiers");
							System.out.println("Combien voulez en construire : ");
							int temp2 = generateur.nextInt(4);
							System.out.println(temp2);
							for(int k = 0; k< temp2; k++) {
								boolean continu = false;
								System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
								int temp = 0;
								do {
									temp = generateur.nextInt(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain());
									temp--;
									if(temp==-1) {
										System.out.println("Annulation construction");
										continu = false;
									} else if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp).getCout()>
									this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
										System.out.println("Impossible le nombre de pieces est insufisant.");
										continu = true;
									}else {
										Quartier quart =  new Quartier();
										quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
										this.plateauDeJeu.getPersonnage(i).construire(quart);
										System.out.println(quart.getNom());
										continu = false;
									}
								}while(continu);
							}
						}else {
							boolean continu = false;
							System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
							int temp = 0;
							do {
								temp = generateur.nextInt(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain());
								temp--;
								if(temp==-1) {
									System.out.println("Annulation construction");
									continu = false;
								} else if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp).getCout()>
								this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									System.out.println("Impossible le nombre de pieces est insufisant.");
									continu = true;
								}else {
									Quartier quart =  new Quartier();
									quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
									this.plateauDeJeu.getPersonnage(i).construire(quart);
									System.out.println(quart.getNom());
									continu = false;
								}
							}while(continu);						
						}
					}
				}
			}
			System.out.println("Fin tour du " + this.plateauDeJeu.getPersonnage(i).getNom());
		}
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
				//do {
					int temp3 = Interaction.lireUnEntier(1, listePerso.size()+1);
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
							System.out.println(listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
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
	
	private void percevoirRessource(int i) {
		if(this.plateauDeJeu.getPersonnage(i).getJoueur().equals(this.plateauDeJeu.getJoueur(0))) {
			//Utilisateur perception
			System.out.println("Choisissez entre deux pièces d'or (1) ou deux cartes de la pioche et n'en garder qu'une seule (2)");
			int temp = Interaction.lireUnEntier(1,3);
			if(temp==1) {
				this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(2);
			}else if(temp==2) {
				ArrayList<Quartier> tempListe = new ArrayList<Quartier>();
				for(int j = 0; j<2; j++) {
					tempListe.add(this.plateauDeJeu.getPioche().piocher());
					System.out.println((j+1)+"- " +tempListe.get(j));
				}
				System.out.println("Quelle carte gardez-vous ?");
				int temp2 = Interaction.lireUnEntier(1,3);
				if(temp2==1) {
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
				}else if(temp2==2) {
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
				}else {
					System.out.println("Erreur perception ressource");
				}
				
				
			}else {
				System.out.println("Erreur manipulation annulation percepetion ressource");
			}
		}else {
			//Bot perception
			int temp = generateur.nextInt(2);
			if(temp==0) {
				System.out.println("Pieces +2");
				this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(2);
			}else if(temp==1) {
				System.out.println("Pioche cartes");
				ArrayList<Quartier> tempListe = new ArrayList<Quartier>();
				for(int j = 0; j<2; j++) {
					tempListe.add(this.plateauDeJeu.getPioche().piocher());
				}
				int temp2 = generateur.nextInt(2);
				if(temp2==0) {
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
				}else if(temp2==1) {
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
				}else {
					System.out.println("Erreur perception ressource");
				}
				
				
			}else {
				System.out.println("Erreur manipulation annulation percepetion ressource");
			}
		}
		
	}
	
	private void calculDesPoints() {
		for(int i =0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
			int nbrPoints = 0;
			int qNoble =0;
			int qCommercant =0;
			int qReligieux =0;
			int qMilitaire =0;
			int qMerveille =0;
			System.out.println("Calcul points pour le joueur " + this.plateauDeJeu.getJoueur(i).getNom());
			for(int j = 0; j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++ ) {
				nbrPoints += this.plateauDeJeu.getJoueur(i).getCite()[j].getCout();
				if(this.plateauDeJeu.getJoueur(i).getCite()[i].getType().equals("NOBLE")) {
					qNoble++;
				}else if(this.plateauDeJeu.getJoueur(i).getCite()[i].getType().equals("COMMERCANT")) {
					qCommercant++;
				}else if(this.plateauDeJeu.getJoueur(i).getCite()[i].getType().equals("RELIGIEUX")) {
					qReligieux++;
				}else if(this.plateauDeJeu.getJoueur(i).getCite()[i].getType().equals("MILITAIRE")) {
					qMilitaire++;
				}else if(this.plateauDeJeu.getJoueur(i).getCite()[i].getType().equals("MERVEILLE")) {
					qMerveille++;
				}
			}
			if(qNoble>=1 && qCommercant>=1 && qReligieux>=1 && qMilitaire>=1 && qMerveille>=1 ) {
				nbrPoints += 3;
			}
		}
	}
	

}
