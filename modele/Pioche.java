package modele;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	private ArrayList<Quartier> liste = new ArrayList<Quartier>();
	
	public ArrayList<Quartier> getListe() {
		return liste;
	}

	public Pioche() {
		this.liste = new ArrayList<Quartier>();
	}
	
	public Quartier piocher() {
		Quartier quart = new Quartier();
		if(this.liste.size()>0) {
			quart = this.liste.get(0);
			this.liste.remove(0);
			return quart;
		}else {
			System.out.println("La pioche est vide");
			return null;
		}
		
	}
	
	public void  ajouter(Quartier quart) {
		this.liste.add(quart);
	}
	
	public int nombreElements() {
		return this.liste.size();
	}
	
	public void melanger() {
		Collections.shuffle(this.liste);
	}
	

}
