package application;

import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Configuration {
	
	
	public static Pioche nouvellePioche() {
		Pioche p = new Pioche();
		Quartier q = new Quartier();
		for(int i = 0; i<5; i++) {
			
			if(i<2) {
				q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5);p.ajouter(q); //2
				q = new Quartier("forteresse",Quartier.TYPE_QUARTIERS[1],5);p.ajouter(q); //2
				q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5); p.ajouter(q);//2
			}
			
			if(i<3) {
				q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); p.ajouter(q); //3
				q = new Quartier("église",Quartier.TYPE_QUARTIERS[0],2); p.ajouter(q); //3
				q = new Quartier("monastère",Quartier.TYPE_QUARTIERS[0],3); p.ajouter(q); //3
				q = new Quartier("tour de guet",Quartier.TYPE_QUARTIERS[1],1); p.ajouter(q);//3
				q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); p.ajouter(q);//3
				q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); p.ajouter(q);//3
				q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); p.ajouter(q);//3
				q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); p.ajouter(q);//3
				q = new Quartier("comptoir",Quartier.TYPE_QUARTIERS[3],3); p.ajouter(q);//3
				q = new Quartier("port",Quartier.TYPE_QUARTIERS[3],4); p.ajouter(q);//3
			}

			if(i<4) {
				q = new Quartier("château",Quartier.TYPE_QUARTIERS[2],4); p.ajouter(q);//4
				q = new Quartier("marché",Quartier.TYPE_QUARTIERS[3],2); p.ajouter(q);//4
			}
			
			q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); p.ajouter(q);//5
			q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); p.ajouter(q);//5
			
		}
		
		return p;
		
	}
	
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		
		return null;
		
	}
	
	public Configuration() {
		
	}

}
