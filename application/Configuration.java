package application;

import modele.*;

public class Configuration {
	
	
	public static Pioche nouvellePioche() {
		Pioche p = new Pioche();
		Quartier q = new Quartier();
		
		for(int i = 0; i<5; i++) {			
			if(i<2) {
				q = new Quartier("cath�drale",Quartier.TYPE_QUARTIERS[0],5);p.ajouter(q); //2
				q = new Quartier("forteresse",Quartier.TYPE_QUARTIERS[1],5);p.ajouter(q); //2
				q = new Quartier("h�tel de ville",Quartier.TYPE_QUARTIERS[3],5); p.ajouter(q);//2
			}			
			if(i<3) {
				q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); p.ajouter(q); //3
				q = new Quartier("�glise",Quartier.TYPE_QUARTIERS[0],2); p.ajouter(q); //3
				q = new Quartier("monast�re",Quartier.TYPE_QUARTIERS[0],3); p.ajouter(q); //3
				q = new Quartier("tour de guet",Quartier.TYPE_QUARTIERS[1],1); p.ajouter(q);//3
				q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); p.ajouter(q);//3
				q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); p.ajouter(q);//3
				q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); p.ajouter(q);//3
				q = new Quartier("�choppe",Quartier.TYPE_QUARTIERS[3],2); p.ajouter(q);//3
				q = new Quartier("comptoir",Quartier.TYPE_QUARTIERS[3],3); p.ajouter(q);//3
				q = new Quartier("port",Quartier.TYPE_QUARTIERS[3],4); p.ajouter(q);//3
			}
			if(i<4) {
				q = new Quartier("ch�teau",Quartier.TYPE_QUARTIERS[2],4); p.ajouter(q);//4
				q = new Quartier("march�",Quartier.TYPE_QUARTIERS[3],2); p.ajouter(q);//4
			}
			q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); p.ajouter(q);//5
			q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); p.ajouter(q);//5
		}
		return p;		
	}
	
	
	
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plat = new PlateauDeJeu();
		Quartier q = new Quartier();
		//Ajout Personnages
		
		Assassin assassin = new Assassin();
		plat.ajouterPersonnage(assassin);
		Voleur voleur = new Voleur();
		plat.ajouterPersonnage(voleur);
		Magicienne magicienne = new Magicienne();
		plat.ajouterPersonnage(magicienne);
		Roi roi = new Roi();
		plat.ajouterPersonnage(roi);
		Eveque eveque = new Eveque();
		plat.ajouterPersonnage(eveque);
		Marchande marchande = new Marchande();
		plat.ajouterPersonnage(marchande);
		Architecte architecte = new Architecte();
		plat.ajouterPersonnage(architecte);
		Condottiere condottiere = new Condottiere();
		plat.ajouterPersonnage(condottiere);
		
		q = new Quartier("biblioth�que", Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez de piocher des cartes au d�but du tour, conservez-les toutes.");
		p.ajouter(q);
		q = new Quartier("carri�re", Quartier.TYPE_QUARTIERS[4],5,"Vous pouvez b�tir des quartiers identiques � d�autres quartiers de votre cit�e. Le propri�taire de la carri�re peut b�tir autant de quartiers identiques qu�il le souhaite");
		p.ajouter(q);
		q = new Quartier("cour des miracles", Quartier.TYPE_QUARTIERS[4],2,"Pour le calcul du score final, la Cour des Miracles est consid�r�e comme un quartier de type (couleur) de votre choix. Dans la cas o� le propri�taire la consid�re comme un quartier noble, militaire, marchant ou religieux, la Cour des Miracles ne peut plus �tre consid�r�e comme une merveille.");
		p.ajouter(q);
		q = new Quartier("donjon", Quartier.TYPE_QUARTIERS[4],3,"Le Donjon ne peut �tre affect�e par les pouvoirs des personnages de rang 8.");
		p.ajouter(q);
		q = new Quartier("dracoport", Quartier.TYPE_QUARTIERS[4],6,"Marquez 2 points suppl�mentaires � la fin de la partie.");
		p.ajouter(q);
		q = new Quartier("�cole de magie", Quartier.TYPE_QUARTIERS[4],6,"Pour la perception des revenus des personnages, l�Ecole de Magie est consid�r�e comme un quartier du type (couleur) de votre choix.");
		p.ajouter(q);
		q = new Quartier("fontaine aux souhaits", Quartier.TYPE_QUARTIERS[4],5,"A la fin de la partie, marquez 1 point suppl�mentaire par merveille dans votre cit�, y compris la Fontaine aux Souhaits. ");
		p.ajouter(q);
		q = new Quartier("forge", Quartier.TYPE_QUARTIERS[4],5,"Une fois par tour, vous pouvez payez 2 pi�ces d�or pour piocher 3 cartes");
		p.ajouter(q);
		q = new Quartier("laboratoire", Quartier.TYPE_QUARTIERS[4],5," Une fois par tour, vous pouvez d�fausser 1 carte pour recevoir 2 pi�ces d�or.");
		p.ajouter(q);
		q = new Quartier("manufacture", Quartier.TYPE_QUARTIERS[4],5," Payez 1 pi�ce d�or de moins lorsque vous b�tissez une autre merveille.");
		p.ajouter(q);
		q = new Quartier("salle des cartes", Quartier.TYPE_QUARTIERS[4],5,"A la fin de la partie, marquez 1 point suppl�ementaire par carte dans votre main.");
		p.ajouter(q);
		q = new Quartier("statue �questre", Quartier.TYPE_QUARTIERS[4],3," Si vous d�tenez le Couronne � la fin de la partie, marquez 5 points suppl�mentaires.");
		p.ajouter(q);
		q = new Quartier("tr�sor imp�rial", Quartier.TYPE_QUARTIERS[4],5," A la fin de la partie, marquez 1 point suppl�mentaire par pi�ce d�or dans votre tr�sor.");
		p.ajouter(q);
		q = new Quartier("tripot", Quartier.TYPE_QUARTIERS[4],6,"Vous pouvez payer tout ou partie du co�t de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 pi�ce d�or. ");
		p.ajouter(q);
		
		return plat;
		
		
	}
	

}
