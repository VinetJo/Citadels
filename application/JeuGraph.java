package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controleur.Interaction;
import controleur.GraphQuestion;
import modele.*;

public class JeuGraph {
	
	private PlateauDeJeu plateauDeJeu = new PlateauDeJeu(); 
	private int numeroConfiguration;
	private Random generateur = new Random();
	
	public JeuGraph(int numeroConfiguration) {
		this.plateauDeJeu = new PlateauDeJeu(); 
		this.numeroConfiguration = numeroConfiguration;
		this.generateur = new Random();
	}
	
	public void jouer() throws InterruptedException {
		System.out.println("Bienvenue dans le jeu citadelle");
		boolean continu=true;
		ArrayList<String> listeChoix = new ArrayList<String>();
		listeChoix.add("Afficher les règles");
		listeChoix.add("Jouer une partie");
		listeChoix.add("Quitter le jeu");
		do {
		/*
		System.out.println("Que souhaitez-vous faire :");
		System.out.println("1 - Afficher les règles");
		System.out.println("2 - Jouer une partie");
		System.out.println("3 - Quitter le jeu");
		int temp = Interaction.lireUnEntier(1, 4);
		*/
		int temp =0;
		
		temp = GraphQuestion.interfaceChoix("Que souhaitez-vous faire :", listeChoix);
		if(temp==0) {
			afficherLesRegles();
		}else if(temp==1) {
			jouerPartie();
		}else if(temp==2) {
			System.out.println("Au revoir");
			continu = false;
		}
		}while(continu);
	}
	
	private void afficherLesRegles() {
		System.out.println("Voici les règles :");
		System.out.println("Suivre le lien ci-dessous :");
		System.out.println("https://www.youtube.com/watch?v=RiiTeHYMDXg");
	}
	
	private void jouerPartie() throws InterruptedException {
		System.out.println("Vous débutez une partie");
		initialisation();
		do {
			tourDeJeu();
			gestionCouronne();
			reinitianilisationPersonnages();			
		}while(!partieFinie());
		calculDesPoints();
		System.out.println("LA PARTIE EST TERMINE");
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
			this.plateauDeJeu.getPersonnage(i).reinitialiser();
		}
		//System.out.println("Fin remise a 0");
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
	
	private void tourDeJeu() throws InterruptedException {
		System.out.println("Debut du tour de jeu.");
		choixPersonnage();
		
		//TEST
		for(int b = 0; b<this.plateauDeJeu.getNombrePersonnages(); b++) {
			if(this.plateauDeJeu.getPersonnage(b).getJoueur()!=null) {
				System.out.println("perso " + this.plateauDeJeu.getPersonnage(b).getNom() +" attribué a " + this.plateauDeJeu.getPersonnage(b).getJoueur().getNom());
			}
		}
		
		for(int i = 0; i<this.plateauDeJeu.getNombrePersonnages(); i++) {
			System.out.println("Le personnage " + this.plateauDeJeu.getPersonnage(i).getNom() + " est appelé" );
			if(this.plateauDeJeu.getPersonnage(i).getJoueur()==null) {
				System.out.println("Personne ne possède ce personnage");
			}else {
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
						System.out.println(this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques()); 
						System.out.println("Nombre pièce trésor = " + this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
						System.out.println("Souhaitez vous utiliser votre pouvoir ?");
						boolean tempB = Interaction.lireOuiOuNon();
						if(tempB) {
							//System.out.println("test si rentre dans boucle");
							this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
						}else {
							if(this.plateauDeJeu.getPersonnage(i).getNom().equals("Roi")) {
								this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
								System.out.println("Vous êtes obligé d'utiliser votre pouvoir en tant que Roi");
							}else {
								System.out.println("Votre pouvoir n'est pas utilisé");
							}
							
						}
						
						
						System.out.println("Souhaitez vous construire un nouveaux quartier ?");
						tempB = Interaction.lireOuiOuNon();
						if(tempB) {
							for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size(); j++) {
								System.out.println((j+1)+" - "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j) + " "); 
							}							
							
							//Si architect :
							if(this.plateauDeJeu.getPersonnage(i).getNom().equals("Architecte")) {
								System.out.println("Vous êtes architecte vous pouvez construire jursqu'à 3 quartiers");
								System.out.println("Combien voulez en construire : ");
								int temp2 = Interaction.lireUnEntier(0, 4);
								for(int k = 0; k< temp2; k++) {
									boolean continu = false;
									if(k>0) {
										for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size(); j++) {
											System.out.println((j+1)+" - "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j) + " "); 
										}
									}
									
									System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
									int temp = 0;
									do {
										temp = Interaction.lireUnEntier(0, (this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1));
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
											/*
											boolean testQuartierIdentique = this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp));
											if(testQuartierIdentique) {
												quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
												this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
												continu = false;
											}else {
												System.out.println("Entrez une nouvelle valeur :");
												continu = true;
											}
											*/
											quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
											
											//Si manufacture dans la cité alors coût du quartier -1
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("manufacture")) {
												if(quart.getType().equals("MERVEILLE")) {
													this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(1);
												}
												
											}
											//Si quartier construit = Ecole magie alors choix du type
											if(quart.getNom().equals("école de magie")) {
												System.out.println("Vous devez choisir de quel type est la cours des miracles (RELIGIEUX, MILITAIRE, NOBLE, COMMERCANT, MERVEILLE)");
												String txt = Interaction.lireUneChaine();
												txt = txt.toUpperCase();
												for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite(); j++ ) {
													if(this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].getNom().equals("cour des miracles")) {
														this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].setType(txt);
													}
												}
											//Si quartier construit = tripot
											}else if(quart.getNom().equals("tripot")) {
												System.out.println("Vous construisez le tripot voulez vous payer avec des cartes de votre main (une carte = 1 pièces d'or)");
												boolean tripot = Interaction.lireOuiOuNon();
												if(tripot) {
													System.out.println("Combien de cartes souhaitez-vous supprimer ?");
													int tripotTemp = Interaction.lireUnEntier(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
													this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(tripotTemp);
													for(int l = 0; l<tripotTemp; l++) {
														Quartier quart1 = new Quartier();
														quart1 = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
														this.plateauDeJeu.getPioche().ajouter(quart1);
													}
												}
											}
											
											this.plateauDeJeu.getPersonnage(i).construire(quart);
											this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
											continu = false;
										}
									}while(continu);
								}
							}else {
								boolean continu = false;
								System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
								int temp = 0;
								do {
									temp = Interaction.lireUnEntier(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
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
										/*
										boolean testQuartierIdentique = this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp));
										if(testQuartierIdentique) {
											quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
											this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
											continu = false;
										}else {
											System.out.println("Entrez une nouvelle valeur :");
											continu = true;
										}
										*/		
												
										quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
										
										//Si manufacture dans la cité alors coût du quartier merveille  -1
										if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("manufacture")) {
											if(quart.getType().equals("MERVEILLE")) {
												this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(1);
											}
											
										}
										//Si quartier construit = Ecole magie alors choix du type
										if(quart.getNom().equals("école de magie")) {
											System.out.println("Vous devez choisir de quel type est la cours des miracles (RELIGIEUX, MILITAIRE, NOBLE, COMMERCANT, MERVEILLE)");
											String txt = Interaction.lireUneChaine();
											txt = txt.toUpperCase();
											for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite(); j++ ) {
												if(this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].getNom().equals("cour des miracles")) {
													this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].setType(txt);
												}
											}
										}else if(quart.getNom().equals("tripot")) {
											System.out.println("Vous construisez le tripot voulez vous payer avec des cartes de votre main (une carte = 1 pièces d'or)");
											boolean tripot = Interaction.lireOuiOuNon();
											if(tripot) {
												System.out.println("Combien de cartes souhaitez-vous supprimer ?");
												int tripotTemp = Interaction.lireUnEntier(0, this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
												this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(tripotTemp);
												for(int l = 0; l<tripotTemp; l++) {
													Quartier quart1 = new Quartier();
													quart1 = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
													this.plateauDeJeu.getPioche().ajouter(quart1);
												}
											}
										}
										this.plateauDeJeu.getPersonnage(i).construire(quart);
										this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
										continu = false;
										
									}
								}while(continu);						
							}
						}
						//Si laboratoire dans la cité 
						if(this.plateauDeJeu.getJoueur(0).quartierPresentDansCite("laboratoire")) {
							System.out.println("Vous possedez le laboratoire souhaitez-vous vous défaussez d'une carte de votre main pour recupérer 2 pièces d'or");
							boolean labo = Interaction.lireOuiOuNon();
							if(labo) {
								for(int k =0; k<this.plateauDeJeu.getJoueur(0).nbQuartiersDansMain(); k++) {
									System.out.println(k+1 + " - " +this.plateauDeJeu.getJoueur(0).getMain().get(k));
								}
								System.out.println("Quelle carte défaussez vous ?");
								int temp = Interaction.lireUnEntier(1, this.plateauDeJeu.getJoueur(0).nbQuartiersDansMain()+1);
								temp--;
								this.plateauDeJeu.getJoueur(0).retirerQuartierDansMainPrecis(temp);
								this.plateauDeJeu.getJoueur(0).ajouterPieces(2);
							}
						}
						
						//Affiche cité
						System.out.println("Votre cité actuelle :");
						for(int y = 0; y<this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite();y++) {
							System.out.println(y+1 +" - " +this.plateauDeJeu.getJoueur(0).getCite()[y].getNom());
						}
						
						
					//Bot appelé
					}else {
						percevoirRessource(i);
						System.out.println(this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques());
						System.out.println("Souhaitez vous utiliser votre pouvoir ?");
						boolean tempB = generateur.nextBoolean();
						System.out.println(tempB);
						if(tempB) {
							this.plateauDeJeu.getPersonnage(i).utiliserPouvoirAvatar();
						}else {
							if(this.plateauDeJeu.getPersonnage(i).getNom().equals("Roi")) {
								this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
								System.out.println("Vous êtes obligé d'utiliser votre pouvoir en tant que Roi");
							}else {
								System.out.println("Le pouvoir n'est pas utilisé");
							}
						}
						System.out.println("Souhaitez vous construire un nouveaux quartier ?");
						tempB =  generateur.nextBoolean();
						System.out.println(tempB);
						if(tempB) {
							//System.out.println("Quel quartier voulez vous construire ?");
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
										temp = generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
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
											/*
											boolean testQuartierIdentique = this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp));
											if(testQuartierIdentique) {
												quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
												this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
												continu = false;
											}else {
												continu = true;
											}
											*/
											quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
											//Si manufacture dans la cité alors coût du quartier merveille -1
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("manufacture")) {
												if(quart.getType().equals("MERVEILLE")) {
													this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(1);
												}
												
											}
											//Test merveille ecole magie
											if(quart.getNom().equals("école de magie")) {
											//Par défaut attribution type religieux pour le bot
												for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite(); j++ ) {
													if(this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].getNom().equals("cour des miracles")) {
														this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].setType("RELIGIEUX");
													}
												
												}
											//Test merveille tripot
											}else if(quart.getNom().equals("tripot")) {
												System.out.println("Vous construisez le tripot voulez vous payer avec des cartes de votre main (une carte = 1 pièces d'or)");
												boolean tripot = generateur.nextBoolean();
												System.out.println(tripot);
												if(tripot) {
													System.out.println("Combien de cartes souhaitez-vous supprimer ?");
													int tripotTemp = generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
													System.out.println(tripotTemp);
													this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(tripotTemp);
													for(int l = 0; l<tripotTemp; l++) {
														Quartier quart1 = new Quartier();
														quart1 = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
														this.plateauDeJeu.getPioche().ajouter(quart1);
													}
												}
											}
											this.plateauDeJeu.getPersonnage(i).construire(quart);
											this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
											continu = false;
										}
									}while(continu);
								}
							}else {
								boolean continu = false;
								System.out.println("Quel cartes construisez vous ? (pour annuler choisir 0)");
								int temp = 0;
								do {
									temp = generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
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
										/*
										boolean testQuartierIdentique = this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(temp));
										if(testQuartierIdentique) {
											quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
											this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
											continu = false;
										}else {
											continu = true;
										}
										*/
										quart = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
										//Si manufacture dans la cité alors coût du quartier merveille -1
										if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("manufacture")) {
											if(quart.getType().equals("MERVEILLE")) {
												this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(1);
											}
											
										}		
										//Test merveille ecole magie
										if(quart.getNom().equals("école de magie")) {
										//Par défaut attribution type religieux pour le bot
											for(int j = 0; j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite(); j++ ) {
												if(this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].getNom().equals("cour des miracles")) {
													this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[j].setType("RELIGIEUX");
												}
											
											}
										}else if(quart.getNom().equals("tripot")) {
											System.out.println("Vous construisez le tripot voulez vous payer avec des cartes de votre main (une carte = 1 pièces d'or)");
											boolean tripot = generateur.nextBoolean();
											System.out.println(tripot);
											if(tripot) {
												System.out.println("Combien de cartes souhaitez-vous supprimer ?");
												int tripotTemp = generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain()+1);
												System.out.println(tripotTemp);
												this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(tripotTemp);
												for(int l = 0; l<tripotTemp; l++) {
													Quartier quart1 = new Quartier();
													quart1 = this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMain();
													this.plateauDeJeu.getPioche().ajouter(quart1);
												}
											}
										}
										this.plateauDeJeu.getPersonnage(i).construire(quart);
										this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(quart.getCout());
										continu = false;
									}
								}while(continu);						
							}
						}
						//Si laboratoire dans la cité 
						if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("laboratoire")) {
							System.out.println("Vous possedez le laboratoire souhaitez-vous vous défaussez d'une carte de votre main pour recupérer 2 pièces d'or");
							boolean labo = generateur.nextBoolean();
							System.out.println(labo);
							if(labo) {
								int temp = generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain());
								this.plateauDeJeu.getPersonnage(i).getJoueur().retirerQuartierDansMainPrecis(temp);
								this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(2);
							}
						}
						//Affiche cité
						System.out.println("La cité actuelle :");
						for(int y = 0; y<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite();y++) {
							System.out.println(y+1 +" - " +this.plateauDeJeu.getPersonnage(i).getJoueur().getCite()[y].getNom());
						}
					}
				}
			}
			
			
			System.out.println("Fin tour du " + this.plateauDeJeu.getPersonnage(i).getNom());
		}
		for(int m = 0; m<this.plateauDeJeu.getNombreJoueurs(); m++) {
			System.out.println(this.plateauDeJeu.getJoueur(m).getNom() +" Pieces = " + this.plateauDeJeu.getJoueur(m).nbPieces());
		}
	}
	
	private void choixPersonnage() throws InterruptedException {
		ArrayList<Personnage> listePerso = new ArrayList<Personnage>();
		//Ajout perso dans une liste vide
		for(int i = 0; i<this.plateauDeJeu.getNombrePersonnages(); i++) {
			listePerso.add(this.plateauDeJeu.getPersonnage(i));
		}
		System.out.println("Choix des personnages : ");
		//Mise de coté cartes
		int temp = generateur.nextInt(listePerso.size());
		System.out.println("Le personnage '" + listePerso.get(temp).getNom() + "' est écarté face visible");	
		listePerso.remove(temp);
		for(int j=0; j<2; j++) {
			System.out.println("Un personnage est écarté face caché");	
			int temp2 = generateur.nextInt(listePerso.size());
			//Plus clair pdt test System.out.println("perso écarté caché : " + listePerso.get(temp2).getNom());
			listePerso.remove(temp2);
			
		}
		for(int k = 0; k<this.plateauDeJeu.getNombreJoueurs();k++) {
			//Si le joueur principal à la couronne
			if(this.plateauDeJeu.getJoueur(k).getPossedeCouronne()==true && this.plateauDeJeu.getJoueur(k).equals(this.plateauDeJeu.getJoueur(0))) {
				ArrayList<String> listeChoixPerso = new ArrayList<String>();
				System.out.println("Vous avez la couronne ! ");
				for(int l = 0; l<listePerso.size(); l++) {
					System.out.println((l+1) +": Rang " + listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
					listeChoixPerso.add("Rang " + listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
				}
				System.out.println("Quel personnage choisissez-vous ?");
				//do {
					
					int temp3 = GraphQuestion.interfaceChoix("Quel personnage choisissez-vous ?", listeChoixPerso);
					
					/* idée non aboutie
					if(!listePerso.get(temp3).equals(this.plateauDeJeu.getPersonnage(temp3))) {
						System.out.println("Mauvaise saisie veuillez recommencer");
						continu = true;
					}*/
					
				//}while(continu);
				for(int m = 0; m<this.plateauDeJeu.getNombrePersonnages();m++) {
					//System.out.println(this.plateauDeJeu.getPersonnage(m).getNom());
					if(listePerso.get(temp3).getNom().equals(this.plateauDeJeu.getPersonnage(m).getNom())) {
						this.plateauDeJeu.getPersonnage(m).setJoueur(this.plateauDeJeu.getJoueur(0));
						listePerso.remove(temp3);
						break;
					}
				}
				//Choix perso pour les bot
				for(int n = 1; n<this.plateauDeJeu.getNombreJoueurs(); n++) {
					int temp4 = generateur.nextInt(listePerso.size());
					for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
						if(listePerso.get(temp4).equals(this.plateauDeJeu.getPersonnage(o))) {
							this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(n));
							listePerso.remove(temp4);
							break;
						}
					}
				}
				
			}else if(this.plateauDeJeu.getJoueur(k).getPossedeCouronne()==true) {
				//Si un bot à la couronne
				int temp5 = generateur.nextInt(listePerso.size());
				for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
					if(listePerso.get(temp5).getNom().equals(this.plateauDeJeu.getPersonnage(o).getNom())) {
						this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(k));
						listePerso.remove(temp5);
						break;
					}
				}
				
				for(int n = 0; n<this.plateauDeJeu.getNombreJoueurs(); n++) {
					if(n==0) {
						ArrayList<String> listeChoixPerso = new ArrayList<String>();
						System.out.println("C'est à votre tour");
						for(int l = 0; l<listePerso.size(); l++) {
							System.out.println(l+1 + " : Rang "+listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
							listeChoixPerso.add("Rang " + listePerso.get(l).getRang() + " - " + listePerso.get(l).getNom());
						}
						int temp3 = GraphQuestion.interfaceChoix("Quel personnage choisissez-vous ?", listeChoixPerso);
							
						for(int m = 0; m<this.plateauDeJeu.getNombrePersonnages();m++) {
							if(listePerso.get(temp3).getNom().equals(this.plateauDeJeu.getPersonnage(m).getNom())) {
								this.plateauDeJeu.getPersonnage(m).setJoueur(this.plateauDeJeu.getJoueur(0));
								listePerso.remove(temp3);
								break;
							}
						}
					
					}else if(n!=k){
						int temp4 = generateur.nextInt(listePerso.size());
						for(int o = 0; o<this.plateauDeJeu.getNombrePersonnages();o++) {
							if(listePerso.get(temp4).equals(this.plateauDeJeu.getPersonnage(o))) {
								this.plateauDeJeu.getPersonnage(o).setJoueur(this.plateauDeJeu.getJoueur(n));
								listePerso.remove(temp4);
								break;
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
			ArrayList<String> ressourceChoix = new ArrayList<String>();
			int temp = Interaction.lireUnEntier(1,3);
			if(temp==1) {
				this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterPieces(2);
				
			}else if(temp==2) {
				ArrayList<Quartier> tempListe = new ArrayList<Quartier>();
				boolean forge = false;
				for(int j = 0; j<2; j++) {
					tempListe.add(this.plateauDeJeu.getPioche().piocher());
					System.out.println((j+1)+"- " +tempListe.get(j));
				}
				//Si forge dans cité
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("forge")) {
					System.out.println("Vous avez la forge souhaitez vous payer 2 pièces pour voir une 3ème carte ?");
					forge = Interaction.lireOuiOuNon();
					if(forge) {
						tempListe.add(this.plateauDeJeu.getPioche().piocher());
						System.out.println("3- " +tempListe.get(2));
						this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(2);
					}
				}
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("bibliothèque")) {
					System.out.println("Vous avez la bibliothèque dans votre cité vous gardez les cartes.");
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
					if(forge) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(2));
					}
				}else {
					int temp2 = 1;
					System.out.println("Quelle carte gardez-vous ?");
					if(forge) {
						temp2 = Interaction.lireUnEntier(1,4);
					}else {
						temp2 = Interaction.lireUnEntier(1,3);
					}
					if(temp2==1) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
					}else if(temp2==2) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
					}else if(temp2==3) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(2));
					}else {
						System.out.println("Erreur perception ressource");
					}
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
				boolean forge = false;
				ArrayList<Quartier> tempListe = new ArrayList<Quartier>();
				for(int j = 0; j<2; j++) {
					tempListe.add(this.plateauDeJeu.getPioche().piocher());
				}
				//Si forge dans cité
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("forge")) {
					System.out.println("Vous avez la forge souhaitez vous payer 2 pièces pour voir une 3ème carte ?");
					forge = generateur.nextBoolean();
					System.out.println(forge);
					if(forge) {
						tempListe.add(this.plateauDeJeu.getPioche().piocher());
						System.out.println("3- " +tempListe.get(2));
						this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(2);
					}
				}
				//Test merveille bibliothèque 
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("bibliothèque")) {
					System.out.println("Vous avez la bibliothèque dans votre cité vous gardez les deux cartes.");
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
					this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
					if(forge) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(2));
					}
				}else {
					int temp2 = 0;
					if(forge) {
						temp2 = generateur.nextInt(3);
					}else {
						temp2 = generateur.nextInt(2);
					}
					if(temp2==0) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(0));
					}else if(temp2==1) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(1));
					}else if(temp2==3) {
						this.plateauDeJeu.getPersonnage(i).getJoueur().ajouterQuartierDansMain(tempListe.get(2));
					}else {
						System.out.println("Erreur perception ressource");
					}
				}
				
			}else {
				System.out.println("Erreur manipulation annulation percepetion ressource");
			}
		}
		
	}
	
	private void calculDesPoints() {
		boolean prems = false;
		int resultat=0;
		int numJoueur=0;
		for(int i =0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
			int nbrPoints = 0;
			int qNoble =0;
			int qCommercant =0;
			int qReligieux =0;
			int qMilitaire =0;
			int qMerveille =0;
			System.out.println("Calcul points pour " + this.plateauDeJeu.getJoueur(i).getNom());
			
			//Merveille cours des miracles
			if(this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("cour des miracles")
					&& this.plateauDeJeu.getJoueur(i).equals(this.plateauDeJeu.getJoueur(0)) ) {
				System.out.println("Vous devez choisir de quel type est la cours des miracles (RELIGIEUX, MILITAIRE, NOBLE, COMMERCANT, MERVEILLE)");
				String txt = Interaction.lireUneChaine();
				txt = txt.toUpperCase();
				for(int j = 0; j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++ ) {
					if(this.plateauDeJeu.getJoueur(i).getCite()[j].getNom().equals("cour des miracles")) {
						this.plateauDeJeu.getJoueur(i).getCite()[j].setType(txt);
					}
					
				}
				
			}else if(this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("cour des miracles")) {
				//Par défaut attribution type religieux pour le bot
				for(int j = 0; j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++ ) {
					if(this.plateauDeJeu.getJoueur(i).getCite()[j].getNom().equals("cour des miracles")) {
						this.plateauDeJeu.getJoueur(i).getCite()[j].setType("RELIGIEUX");
					}
					
				}
			}
			
			//cout construction cite = pts
			for(int j = 0; j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++ ) {
				if(!this.plateauDeJeu.getJoueur(i).getCite()[j].equals(null)) {
					nbrPoints += this.plateauDeJeu.getJoueur(i).getCite()[j].getCout();
					if(this.plateauDeJeu.getJoueur(i).getCite()[j].getType().equals("NOBLE")) {
						qNoble++;
					}else if(this.plateauDeJeu.getJoueur(i).getCite()[j].getType().equals("COMMERCANT")) {
						qCommercant++;
					}else if(this.plateauDeJeu.getJoueur(i).getCite()[j].getType().equals("RELIGIEUX")) {
						qReligieux++;
					}else if(this.plateauDeJeu.getJoueur(i).getCite()[j].getType().equals("MILITAIRE")) {
						qMilitaire++;
					}else if(this.plateauDeJeu.getJoueur(i).getCite()[j].getType().equals("MERVEILLE")) {
						qMerveille++;
						calculPointsMerveille(this.plateauDeJeu.getJoueur(i).getCite()[j], i);
					}
					
					
				}
			}
			//Si tout type de quartier
			if(qNoble>=1 && qCommercant>=1 && qReligieux>=1 && qMilitaire>=1 && qMerveille>=1 ) {
				nbrPoints += 3;
			}
			//Si premier ou non et avoir 7 quartier 
			if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()==7 && prems==false) {
				nbrPoints +=4;
				prems = true;
			}else if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()==7 && prems==true) {
				nbrPoints +=2;
			}
			//Ajout spé merveille
			
			System.out.println("Le joueur "+ this.plateauDeJeu.getJoueur(i).getNom()+ " possède " + nbrPoints + " de points");
			if(nbrPoints > resultat) {
				resultat = nbrPoints;
				numJoueur = i;
			}
			
		}
		
		System.out.println("Et le gagnant est " + this.plateauDeJeu.getJoueur(numJoueur).getNom());
		
	}
	
	private int calculPointsMerveille(Quartier quart, int numJoueur) {
		int points = 0;
		switch(quart.getNom()) {
		
		case "dracoport":
			points +=2;
			break;
		
		case "fontaine aux souhaits":
			for(int k =0; k<this.plateauDeJeu.getJoueur(numJoueur).nbQuartiersDansCite();k++) {
				if(this.plateauDeJeu.getJoueur(numJoueur).getCite()[k].getType().equals("MERVEILLE")) {
					points+=1;
				}
				
			}
			System.out.println("Fontaine aux souhaits + " + points + " points");
			break;
		
		case "salle des cartes":
			points+= this.plateauDeJeu.getJoueur(numJoueur).nbQuartiersDansMain();
			System.out.println("Salle des cartes + " + points + " points");
			break;
		
		case "statue équestre":
			if(this.plateauDeJeu.getJoueur(numJoueur).getPossedeCouronne()) {
				System.out.println("Couronne + statue équestre = 5 points supplémentaire");
				points+=5;
			}
			break;
			
		case "trésor impérial" :
			points += this.plateauDeJeu.getJoueur(numJoueur).nbPieces();
			System.out.println("Trésor impérial + " + points + " points");
			break;
		
		}
		
		
		return points;
	}
	

}
