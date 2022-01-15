package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				//System.out.println(i + "dans interaction entier");
				if (i>=borneMin && i<borneMax) {
					continu = false;
				}else {
					System.out.println("Hors Bornes, nouveaux entier inférieur à " + borneMax + " : ");
				}
				
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next();
			}
		} while(continu);
			
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		String chaine="";
		boolean continu = true;
		if(!sc.nextLine().equals(null)) {
			System.out.println("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" :");
		}
		do {

				chaine = sc.nextLine();
				if(chaine.toLowerCase().equals("oui")||chaine.toLowerCase().equals("o")) {
					retour = true;
					continu = false;
				}else if(chaine.toLowerCase().equals("non")||chaine.toLowerCase().equals("n")) {
					retour = false;
					continu = false;
				}else {
					System.out.println("Caratère hors liste, nouvelle réponse : ");
				}
				
		} while(continu);
		return retour;
	}

	// renvoie une chaine de caractère lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
		retour = sc.nextLine();
		return retour;
	}


	
}