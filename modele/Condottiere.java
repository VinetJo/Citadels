package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage{

	public Condottiere() {
		super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
		// TODO Auto-generated constructor stub
	}
	
	public void utiliserPouvoir() {
			for(int i =0 ; i< super.getPlateau().getNombreJoueurs() ; i++) {
				System.out.println((i+1) + " - " + super.getPlateau().getJoueur(i).getNom()+ ": ");
				for(int j = 0; j<super.getPlateau().getJoueur(i).nbQuartiersDansCite(); j++) {
					System.out.println("-> "+(j+1) + "-" + super.getPlateau().getJoueur(i).getCite()[j].getNom()
							+ "(coût " +(super.getPlateau().getJoueur(i).getCite()[j].getCout()-1) + ")," );
					
				}
			}
			System.out.println("Pour information vous avez : " + super.getJoueur().nbPieces() + " pieces dans votre trésor");
			System.out.println("Quel joueurs choisissez-vous ? (0 pour ne rien faire)");
			int temp2  = Interaction.lireUnEntier(0, (super.getPlateau().getNombreJoueurs()+1));
			if(temp2==0) {
				System.out.println("Pouvoir annulé");
			}else if(super.getPlateau().getJoueur(temp2-1).getPersonnage().getNom().equals("Eveque")){
				System.out.println("Votre pouvoir ne peux pas choisir cette personne");
			}else {
				temp2--;
				boolean boucle;
				do {
					boucle = false;
					System.out.println("Quel quartier voulez vous détruire ?");
					int temp3 = Interaction.lireUnEntier(0, super.getPlateau().getJoueur(temp2).getCite().length+1);
					if(temp3==0) {
						System.out.println("Pouvoir annulé");
						break;
					}
					temp3--;
					if(super.getJoueur().nbPieces()<(super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1)) {
						System.out.println("Votre trésor n'est pas suffisant");
						boucle = true;
					}else {
						super.getJoueur().retirerPieces((super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1));
						
						System.out.println("=> On retire " + super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom() 
								+ " de " + super.getPlateau().getJoueur(temp2).getNom());
						
						super.getPlateau().getJoueur(temp2).retirerQuartierDansCite
						(super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom());
						
						
						System.out.println("Il vous reste "+ super.getJoueur().nbPieces()  + " pièces dans votre trésor");
					}
				}while(boucle);
				
			}
			
			
		
	}
	
	public void utiliserPouvoirAvatar() {
		Random ran = new Random();
			int temp2  = ran.nextInt(super.getPlateau().getNombreJoueurs());
			if(!super.getPlateau().getJoueur(temp2).getPersonnage().getNom().equals("Eveque") 
					&& super.getPlateau().getJoueur(temp2).nbQuartiersDansCite()>0) {
				boolean boucle;
				do {
					boucle = false;
					int temp3 = ran.nextInt(super.getPlateau().getJoueur(temp2).nbQuartiersDansCite()+1);
					if(temp3==0) {
						break;
					}
					temp3--;
					if(super.getJoueur().nbPieces()<(super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1)) {
						System.out.println("Votre trésor n'est pas suffisant");
						boucle = true;
					}else {
						super.getJoueur().retirerPieces((super.getPlateau().getJoueur(temp2).getCite()[temp3].getCout()-1));
						
						System.out.println("=> On retire " + super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom() 
								+ " de " + super.getPlateau().getJoueur(temp2).getNom());
						
						super.getPlateau().getJoueur(temp2).retirerQuartierDansCite
						(super.getPlateau().getJoueur(temp2).getCite()[temp3].getNom()); 
						
						
						System.out.println("Il vous reste "+ super.getJoueur().nbPieces()  + " pièces dans votre trésor");
					}
				}while(boucle);
				
			}
			
			
		
	}
	
	public String percevoirRessourcesSpecifiques() {
		for(int i =0; i<super.getJoueur().nbQuartiersDansCite();i++) {
					
					if(super.getJoueur().getCite()[i].getType().equals("MILITAIRE")) {
						super.getJoueur().ajouterPieces(1);
					}
				}
		
		return "";
		
	}

}
