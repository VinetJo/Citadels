/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Magicienne extends Personnage{

	
	
	public Magicienne() {
		super("Magicienne", 3, Caracteristiques.MAGICIENNE);
		
	}
	
	public void utiliserPouvoir() {
		ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(super.getJoueur().getMain());
		if(super.getJoueur().getMain().size()==0) {
			
			System.out.println("IMPOSSIBLE d'utiliser votre pouvoir, vous n'avez aucune carte");
			
		}else {
			
			boolean continu = false;
			System.out.println("Voulez vous échanger vos cartes avec un autre joueur ?");
			boolean accept = Interaction.lireOuiOuNon();
			if(accept) {
				//échange avec un joueur
				for(int i = 0; i<super.getPlateau().getNombreJoueurs();i++) {
					System.out.println(i + " - " + super.getPlateau().getJoueur(i).getNom() + " / nb cartes : " + super.getPlateau().getJoueur(i).getMain().size());
				}
				do {
					
					System.out.println("Quel joueur choisir (chiffre attendu) :");
					int temp = Interaction.lireUnEntier(0, super.getPlateau().getNombreJoueurs());
					
					if(super.getPlateau().getJoueur(temp) == super.getJoueur()) {
						continu = true;
						System.out.println("Vous ne pouvez pas vous choisir.");
						
					}else {
						ArrayList<Quartier> copieTableau2 = new ArrayList<Quartier>(super.getPlateau().getJoueur(temp).getMain());
						for(int j = 0; j<copieTableau.size();j++) {
							super.getJoueur().retirerQuartierDansMain();
						}
						for(int k = 0; k<copieTableau2.size();k++) {
							super.getJoueur().ajouterQuartierDansMain(copieTableau2.get(k));
						}
						//super.getJoueur().getMain().addAll(copieTableau2);
						
						for(int j = 0; j<copieTableau2.size();j++) {
							super.getPlateau().getJoueur(temp).retirerQuartierDansMain();
						}
						for(int k = 0; k<copieTableau.size();k++) {
							super.getPlateau().getJoueur(temp).ajouterQuartierDansMain(copieTableau.get(k));
						}
						//super.getPlateau().getJoueur(temp).getMain().addAll(copieTableau);
						continu = false;
					}
				}while(continu);
				
			}else if(!accept) {
				//échange avec la pioche
				
				System.out.println("Combien de cartes voulez vous prendre dans la pioche : ");
				int temp2 = Interaction.lireUnEntier(0, (super.getJoueur().getMain().size()+1));
				
				if(temp2==0) {
					System.out.println("Aucune action effectué fin du pouvoir.");
					
				}else if(temp2==super.getJoueur().getMain().size()) {
					for(int j = 0; j<copieTableau.size();j++) {
						Quartier quart = super.getJoueur().retirerQuartierDansMain();
						super.getPlateau().getPioche().ajouter(quart);
					}
					for(int j = 0; j<copieTableau.size();j++) {
						super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
					}
				}else {
					for(int l =0; l<temp2; l++) {
						
						System.out.println("Voici les cartes de votre main :");
						for(int k = 0; k<super.getJoueur().getMain().size();k++) {
							System.out.println((k+1) + " " +super.getJoueur().getMain().get(k).toString());
						}
						System.out.println("Quel carte voulez vous retirer :");
						int temp3 = Interaction.lireUnEntier(1, (super.getJoueur().getMain().size()+1));
						temp3--;
						super.getPlateau().getPioche().ajouter(copieTableau.get(temp3));

						copieTableau.remove(temp3);

					}
					
					for(int l = 0; l<temp2; l++) {
						copieTableau.add(super.getPlateau().getPioche().piocher());
					}
					int temp4 = super.getJoueur().getMain().size();
					for(int l = 0; l<temp4; l++) {
						super.getJoueur().retirerQuartierDansMain();
						
					}
					for(int k = 0; k<copieTableau.size();k++) {
						super.getJoueur().ajouterQuartierDansMain(copieTableau.get(k));
					}
					//super.getJoueur().getMain().addAll(copieTableau);
					
				}
				
				
				
			}
			System.out.println("Voici vos cartes :");
			for(int i = 0; i<super.getJoueur().nbQuartiersDansMain(); i++) {
				System.out.println(i+1 + "-" + super.getJoueur().getMain().get(i).getNom());
			}
			
			
		}
		
		
		
	}
	
	
	public void utiliserPouvoirAvatar() {
		Random ran = new Random();
		ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(super.getJoueur().getMain());
		if(super.getJoueur().nbQuartiersDansMain()==0) {
			
			System.out.println("IMPOSSIBLE d'utiliser votre pouvoir, vous n'avez aucune carte");
			
		}else {
			
			boolean continu = false;
			boolean accept = ran.nextBoolean();
			if(accept) {
				System.out.println("ECHANGE AVEC UN JOUEUR");
				//échange avec un joueur
				do {
					
					int temp = ran.nextInt(super.getPlateau().getNombreJoueurs());
					System.out.println("nom avec qui il echange" +super.getPlateau().getJoueur(temp).getNom());
					if(super.getPlateau().getJoueur(temp) == super.getJoueur()) {
						continu = true;
						System.out.println("Vous ne pouvez pas vous choisir.");
						
					}else {
						ArrayList<Quartier> copieTableau2 = new ArrayList<Quartier>(super.getPlateau().getJoueur(temp).getMain());
						System.out.println(copieTableau.size() + " size tab bot");
						for(int j = 0; j<copieTableau.size();j++) {
							super.getJoueur().retirerQuartierDansMain();
						}
						
						for(int k = 0; k<copieTableau2.size();k++) {
							super.getJoueur().ajouterQuartierDansMain(copieTableau2.get(k));
						}
						//super.getJoueur().getMain().addAll(copieTableau2);
						
						for(int j = 0; j<copieTableau2.size();j++) {
							super.getPlateau().getJoueur(temp).retirerQuartierDansMain();
						}
						for(int k = 0; k<copieTableau.size();k++) {
							super.getPlateau().getJoueur(temp).ajouterQuartierDansMain(copieTableau.get(k));
						}
						//super.getPlateau().getJoueur(temp).getMain().addAll(copieTableau);
						System.out.println("Echange entre le joueur :" + super.getJoueur().getNom() + " et le joueur :" +super.getPlateau().getJoueur(temp).getNom());
						continu = false;
					}
				}while(continu);
				
			}else if(!accept) {
				System.out.println("ECHANGE AVCE LA PIOCHE");
				//échange avec la pioche
				
				int temp2 = ran.nextInt((super.getJoueur().getMain().size()+1));
				System.out.println("Nbr cartes échangés" + temp2);
				if(temp2==0) {
					System.out.println("Aucune action effectué fin du pouvoir.");
					
				}else if(temp2==super.getJoueur().getMain().size()) {
					for(int j = 0; j<copieTableau.size();j++) {
						Quartier quart = super.getJoueur().retirerQuartierDansMain();
						super.getPlateau().getPioche().ajouter(quart);
					}
					for(int j = 0; j<copieTableau.size();j++) {
						super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
					}
				}else {
					for(int l =0; l<temp2; l++) {
												
						int temp3 = ran.nextInt(super.getJoueur().getMain().size());
						temp3--;
						super.getPlateau().getPioche().ajouter(copieTableau.get(temp3));

						copieTableau.remove(temp3);

					}
					
					for(int l = 0; l<temp2; l++) {
						copieTableau.add(super.getPlateau().getPioche().piocher());
					}
					int temp4 = super.getJoueur().getMain().size();
					for(int l = 0; l<temp4; l++) {
						super.getJoueur().retirerQuartierDansMain();
						
					}
					for(int k = 0; k<copieTableau.size();k++) {
						super.getJoueur().ajouterQuartierDansMain(copieTableau.get(k));
					}
					//super.getJoueur().getMain().addAll(copieTableau);
					
				}
				
				
				
			}
			
		}
	}

}
