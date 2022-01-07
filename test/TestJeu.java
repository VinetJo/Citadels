package test;

import application.*;
import modele.*;

public class TestJeu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	public static void test1() {
		Pioche p = new Pioche();
		Configuration config = new Configuration();
		p = config.nouvellePioche();
		PlateauDeJeu plateau = new PlateauDeJeu();
		plateau = config.configurationDeBase(p);
		Jeu partieJeu = new Jeu(plateau, 0);
		partieJeu.jouer();
		
	}

}
