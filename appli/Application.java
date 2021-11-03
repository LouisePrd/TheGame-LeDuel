package appli;

import java.util.LinkedList;
import java.util.Scanner;

import jeu.Joueur;
import jeu.Tour;

public class Application {

	public static void main(String[] args) {
		Joueur Nord = new Joueur("NORD ");
		Joueur Sud = new Joueur("SUD ");

		while (!(estFini(Nord, Sud)))  {
			System.out.println(Nord);
			System.out.println(Sud);

			System.out.println(Nord.affichageCarte());
			tour(Nord, Sud);

            if(!(estFini(Nord, Sud))) {
				System.out.println(Nord);
				System.out.println(Sud);

				System.out.println(Sud.affichageCarte());
    			tour(Sud, Nord);
            }
			else {
            	break;
            }
		}
	}
	
        
		/**
		 * Vérifie si le jeu est terminé : 
		 * le jeu est terminé lorsqu'un joueur a posé toutes ses cartes
		 * ou lorsqu'un joueur est bloqué c'est-à-dire qu'il ne peut pas jouer 
		 * au minimum 2 cartes
		 * @param J le joueur qui joue
		 * @param J2 le joueur adverse
		 * @return un boolean en fonction de si la partie est terminée ou non
		 */
	public static boolean estFini(Joueur J, Joueur J2) {
			boolean vide = false;
			int nbCartesPouvantEtreJoué = 0;
			int nbCartesPouvantEtreJouéAdv = 0;
			// vérifie si la main et la pioche sont vides
			if (J.getMain().size() == 0 && J.getPioche().size() == 0) {
				vide = true;
			}
			// vérifie si on on peut jouer au moins 2 cartes
			for (int carte : J.getMain()) {
				if (carte > J.getSommetPileA() || carte > J2.getSommetPileD() ||
						carte < J.getSommetPileD() || carte < J2.getSommetPileA() 
						|| carte == J.getSommetPileA() - 10) {
					nbCartesPouvantEtreJoué++;
					if(carte > J2.getSommetPileD() || carte < J2.getSommetPileA()) {
						nbCartesPouvantEtreJouéAdv++;
					}
				}
			}
			// si l'une des conditions est vérifiée : alors fin de tour donc return true
			if (vide) {
				System.out.println("partie finie, " + J.getNom() + " a gagné");
				return true;
			}
			if (nbCartesPouvantEtreJoué < 2 && nbCartesPouvantEtreJouéAdv <= 1) {
				System.out.println("partie finie, " + J2.getNom() + " a gagné");
				return true;
			}
			return false;
		}

	/**
	 * Gère la saisie en cas d'erreur
	 * 
	 */
	public static String[] ecrireCoupErreur() {
		Scanner sc = new Scanner(System.in);
		String s;
		System.out.print("#> ");
		s = sc.nextLine();
		String[] propositions = s.split(" ");
		return propositions;
	}

	/**
	 * Gère la première saisie d'un tour
	 * @return le tableaux de propositions
	 */
	public static String[] ecrireCoup() {
		Scanner sc = new Scanner(System.in);
		String s;
		System.out.print("> ");
		s = sc.nextLine();
		String[] propositions = s.split(" ");
		// sépare les éléments du scanner
		return propositions;
	}
	
	/**
	 * Permet à un joueur de jouer tout en prenant en compte les erreurs
	 * potentielles
	 * @param joueur le joueur qui joue
	 * @param joueurAdv le joueur adverse
	 */
	public static void tour(Joueur joueur, Joueur joueurAdv) {
		int nbCartesP = 0; // nb cartes piochées

		String[] tab = ecrireCoup(); // propositions du joueur

		// tant que lire et jouer renvoie false on ecrit un coup erreur
		
		while (true) {
			try {
				new Tour(new Joueur(joueur), new Joueur(joueurAdv)).jouer(tab);
				break;
			}
			catch(IllegalArgumentException e) {
				tab = ecrireCoupErreur();
			}
		}
		int nbCarteJouéesAdv = new Tour(joueur,joueurAdv).jouer(tab);

		// si le joueur a joué sur la pile ennemie
		// on pioche le nombre de cartes nécessaires pour remplir la main
		if (nbCarteJouéesAdv == 1) {
			nbCartesP = 6 - joueur.getMain().size();
			if (joueur.getPioche().size() < nbCartesP) {
				nbCartesP = joueur.getPioche().size();
				joueur.piocher(nbCartesP);
			} else {
				joueur.piocher(nbCartesP);
			}
			// sinon on pioche 2 cartes
		} else if (joueur.getPioche().size() < 2) {
			nbCartesP = joueur.getPioche().size();
			joueur.piocher(nbCartesP);
		} else {
			nbCartesP = 2;
			joueur.piocher(2);
		}

		// affichage du nombre de cartes jouées et piochées
		System.out.println(tab.length + " cartes posées, " + nbCartesP + " cartes piochées");
	}

	

}