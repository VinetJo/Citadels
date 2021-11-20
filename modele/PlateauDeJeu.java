package modele;

public class PlateauDeJeu {
	
	private Personnage [] listePersonnage;
	private Joueur [] listeJoueur;
	private Pioche pioche = new Pioche();
	private int nombrePersonnages = 0;
	private int nombreJoueurs = 0;
	
	public PlateauDeJeu() {
		this.listeJoueur = new Joueur[9];
		this.listePersonnage = new Personnage[9];
		this.pioche = new Pioche();
		this.nombrePersonnages = 0;
		this.nombreJoueurs =0;
	}

	public Personnage getPersonnage(int i) {
		if(i>=0 && i<(this.listePersonnage.length-1)) {
			return listePersonnage[i];
		}else {
			return null;
		}
		
	}

	public Joueur getJoueur(int i) {
		if(i>=0 && i<(this.listeJoueur.length-1)) {
			return listeJoueur[i];
		}else {
			return null;
		}
	}

	public Pioche getPioche() {
		return pioche;
	}

	public int getNombrePersonnages() {
		return nombrePersonnages;
	}

	public int getNombreJoueurs() {
		return nombreJoueurs;
	}
	
	public void ajouterPersonnage(Personnage person) {
		if(person!=null && this.nombrePersonnages<9) {
			this.listePersonnage[this.nombrePersonnages] = person;
			person.setPlateau(this);
			this.nombrePersonnages++;
		}
	}
	
	public void ajouterJoueur(Joueur joueur) {
		if(joueur!=null && this.nombreJoueurs<9) {
			this.listeJoueur[this.nombreJoueurs] = joueur;
			this.nombreJoueurs++;
		}
	}
	
	

}
