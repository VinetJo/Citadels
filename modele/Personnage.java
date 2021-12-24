/**
 * 
 */
package modele;

import java.util.Random;

public abstract class Personnage {
	
	private String nom="";
	private int rang=0;
	private String caracteristiques ="";
	private Joueur joueur;
	private boolean assassine = false;
	private boolean vole = false;
	private PlateauDeJeu plateau;
	
	public Personnage(String nom, int rang, String caracteristiques) {
		this.nom = nom;
		this.rang = rang;
		this.caracteristiques = caracteristiques;
		this.joueur = null;
		this.assassine = false;
		this.vole = false;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}
	

	/**
	 * @return the caracteristiques
	 */
	public String getCaracteristiques() {
		return caracteristiques;
	}

	/**
	 * @return the joueur
	 */
	public Joueur getJoueur() {
		if(this.joueur != null) {
			return joueur;
		}else {
			return null;
		}
		
	}

	/**
	 * @return the assassine
	 */
	public boolean getAssassine() {
		return assassine;
	}

	/**
	 * @return the vole
	 */
	public boolean getVole() {
		return vole;
	}

	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		this.joueur.monPersonnage = this;
	}

	/**
	 * @param assassine the assassine to set
	 */
	public void setAssassine() {
		this.assassine = true;
	}

	/**
	 * @param vole the vole to set
	 */
	public void setVole() {
		this.vole = true;
	}
	
	public void ajouterPieces() {
		if(!this.getAssassine() && this.joueur!=null ) {
			this.joueur.ajouterPieces(2);
		}
	}
	
	public void ajouterQuartier(Quartier quart) {
		if(!this.getAssassine() && this.joueur!=null ) {
			this.joueur.ajouterQuartierDansMain(quart);;
		}
	}
	
	public void construire(Quartier quart) {
		if(!this.getAssassine() && this.joueur!=null ) {
			this.joueur.ajouterQuartierDansCite(quart);
		}
	}
	
	public String percevoirRessourcesSpecifiques() {
		if(!this.getAssassine() && this.joueur!=null ) {
			return "Aucune Ressource Sp�cifique";
		}
		return "";
		
	}
	
	

	public void utiliserPouvoir() {
		
		
	}
	
	public void utiliserPouvoirAvatar() {
		
	}
	
	public void reinitialiser() {
		this.joueur = null;
		this.assassine = false;
		this.vole = false;
		this.joueur.monPersonnage = null;
	}

	public PlateauDeJeu getPlateau() {
		return plateau;
	}

	public void setPlateau(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}
	
	
	
	
	
	/* Pas utilis�
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}
	*/

}
