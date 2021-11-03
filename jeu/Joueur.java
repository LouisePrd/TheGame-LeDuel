package jeu;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Joueur {
	private static final int MAX_CARTES = 60;
	private String nom;
	private LinkedList<Integer> pioche;
	private LinkedList<Integer> main;
	private int sommetPileA;
	private int sommetPileD;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.pioche = new LinkedList();
		this.main = new LinkedList();
		this.sommetPileA = 1;
		this.sommetPileD = MAX_CARTES;

		this.initialiserPioche();
		this.piocher(6);
	}

	public Joueur(Joueur J) {
		this.nom = J.getNom();
		this.pioche = new LinkedList<>(J.getPioche());
		this.main = new LinkedList<>(J.getMain());
		this.sommetPileA = J.getSommetPileA();
		this.sommetPileD = J.getSommetPileD();
	}

	/**
	 * Permet d'initialiser la pioche en lui donnant les cartes nécessaires
	 */
	private void initialiserPioche() {
		for (int i = 2; i < MAX_CARTES; ++i) {
			this.pioche.add(i);
		}
	}

	/**
	 * Permet à un joueur de piocher en fonction des cartes qu'il pose
	 * 
	 * @param nbCartesAPiocher le nombre de cartes à piocher
	 */
	public void piocher(int nbCartesAPiocher) {
		Collections.shuffle(this.getPioche());
		
		for (int i = 0; i < nbCartesAPiocher; i++) {
			// transfère les cartes de la pioche à la main
			this.getMain().add(this.getPioche().get(0));
			this.getPioche().remove(0);
		}
		Collections.sort(this.main);
	}

	/**
	 * Le joueur peut-il poser sur sa pile ascendante ?
	 * 
	 * @param carte
	 * @return boolean
	 */
	public boolean poserPileA(int carte) {
		if ((carte > this.getSommetPileA() || carte == this.getSommetPileA() - 10) && carte != this.getSommetPileA()) {
			this.sommetPileA = carte;
			this.getMain().remove(this.getCarte(carte));
			return true;
		}
		return false;
	}

	/**
	 * Le joueur peut-il poser sur la pile ascendante ennemie ?
	 * 
	 * @param adversaire
	 * @param carte
	 * @return boolean
	 */
	public boolean poserPileAA(Joueur adversaire, int carte) {
		if (carte < adversaire.getSommetPileA() && carte != adversaire.getSommetPileA()) {
			adversaire.sommetPileA = carte;
			this.getMain().remove(this.getCarte(carte));
			return true;
		}
		return false;
	}

	/**
	 * Le joueur peut-il poser sur sa pile descendante ?
	 * 
	 * @param carte
	 * @return boolean
	 */
	public boolean poserPileD(int carte) {
		if ((carte < this.getSommetPileD() || carte == this.getSommetPileD() + 10) && carte != this.getSommetPileD()) {
			this.sommetPileD = carte;
			this.getMain().remove(this.getCarte(carte));
			return true;
		}
		return false;
	}

	/**
	 * Le joueur peut-il poser sur la pile descendante ennemie ?
	 * 
	 * @param adversaire
	 * @param carte
	 * @return boolean
	 */
	public boolean poserPileDA(Joueur adversaire, int carte) {
		if (carte > adversaire.getSommetPileD() && carte != adversaire.getSommetPileD()) {
			adversaire.sommetPileD = carte;
			this.getMain().remove(this.getCarte(carte));
			return true;
		}
		return false;
	}
	
	/**
	 * Affichage des mains d'un joueur
	 * @return l'affichage des cartes du joueur
	 */
	public String affichageCarte() {
		StringBuilder sB = new StringBuilder();
		sB.append("cartes " + this.getNom() + " { ");
		for (int i = 0; i < this.main.size(); ++i) {
			sB.append(String.format("%02d", this.main.get(i)) + " ");
		}
		sB.append("}");
		return sB.toString();
	}
	

	/**
	 * Permet de récupérer une carte
	 * 
	 * @param n
	 * @return la carte ou rien
	 */
	public Integer getCarte(Integer n) {
		for (int carte : this.getMain()) {
			if (n == carte)
				return carte;
		}
		return 0;
	}

	/**
	 * La carte se trouve-telle dans la main ?
	 * 
	 * @param n le numéro de la carte
	 * @return boolean
	 */
	public boolean dansLaMain(Integer n) {
		for (int carte : this.getMain()) {
			if (n == carte)
				return true;
		}
		return false;
	}

	/** Affichage des sommets de chaque joueur au début d'un tour,
	 * du nombre de cartes dans la main
	 * et du nombre de cartes dans la pioche
	 * 
	 */
	public String toString() {
		return this.nom + 
		" ^[" + String.format("%02d", this.sommetPileA) + "] v["
				+ String.format("%02d", this.sommetPileD) + "] (m" 
				+ this.main.size() + "p" + this.pioche.size() + ")";
	}

	/**
	 * Getter du sommet de la pile ascendante d'un joueur
	 * @return le sommet de la pile ascendante
	 */
	public int getSommetPileA() {
		return sommetPileA;
	}

	/**
	 * Getter du sommet de la pile descendante d'un joueur
	 * @return le sommet de la pile descendante
	 */
	public int getSommetPileD() {
		return sommetPileD;
	}

	/**
	 * Getter du nom d'un joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Getter de la pioche d'un joueur
	 * @return la pioche du joueur
	 */
	public LinkedList<Integer> getPioche() {
		return pioche;
	}

	/**
	 * Getter de la main d'un joueur
	 * @return la main du joueur
	 */
	public LinkedList<Integer> getMain() {
		return main;
	}

	public void setMain(LinkedList<Integer> main) {
		this.main = main;
	}

}
