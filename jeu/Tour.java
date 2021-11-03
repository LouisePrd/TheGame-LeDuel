package jeu;

public class Tour {
	private Joueur joueur;
	private Joueur joueurAdv;
	private int nbCartesJouéesAdv;

	public Tour(Joueur joueur, Joueur joueurAdv) {
		this.joueur = joueur;
		this.joueurAdv = joueurAdv;
		this.nbCartesJouéesAdv = 0;
	}
	
	/**
	 * Tente de jouer un tableau contenant les propositions du joueur
	 * @param tab le tableau de propositions
	 * @return le nombre de cartes jouées chez l'adversaire
	 */
	public int jouer(String[] tab) {
		//variable qui va contenir le numéro de la carte
		int n = 0;
		// on vérifie si le tableau où se trouvent les coups joués est bien supérieur à 2
		if (tab.length < 2) { 
			throw new IllegalArgumentException();
		}
		for (String mot : tab) {
			if (mot.length() < 3) {
				throw new IllegalArgumentException();
			}
			if (Character.isDigit(mot.charAt(0)) && Character.isDigit(mot.charAt(1))) {
				n = Integer.parseInt(("" + mot.charAt(0) + mot.charAt(1)));
				if (joueur.dansLaMain(n)) {
					if (mot.charAt(2) == '^') {
						if (mot.length() == 4) {
							if (mot.charAt(3) == '\'') {
								if (joueur.poserPileAA(joueurAdv, n)) {
									this.nbCartesJouéesAdv++;
								} else {
									throw new IllegalArgumentException();
								}
							} else
								throw new IllegalArgumentException();
						} else {
							if (!(joueur.poserPileA(n))) {
								throw new IllegalArgumentException();
							}
						}
					} else if (mot.charAt(2) == 'v') {
						if (mot.length() == 4) {
							if (mot.charAt(3) == '\'') {
								if (joueur.poserPileDA(joueurAdv, n)) {
									this.nbCartesJouéesAdv++;
								} else {
									throw new IllegalArgumentException();
								}
							}
						} else {
							if (!(joueur.poserPileD(n))) {
								throw new IllegalArgumentException();
							}
						}

					} else {
						throw new IllegalArgumentException();
					}

				} else {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException();
			}

		}
		// si le joueur joue plus de 2 cartes chez l'adversaire, on redemande une
		// saisie
		if (nbCartesJouéesAdv >= 2) {
			throw new IllegalArgumentException();
		}
		return nbCartesJouéesAdv;
	}
}
