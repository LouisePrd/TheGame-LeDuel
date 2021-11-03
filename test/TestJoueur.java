package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeu.Joueur;

class TestJoueur {

	@Test
	void testPoserPileA() {
		// Joueur depart sommet Ascendant 1
		Joueur J = new Joueur("J");

		// Tester quand la carte est plus grande (true)
		assertTrue(J.poserPileA(13));

		// Tester quand la carte est la dizaine en dessous (true)
		assertTrue(J.poserPileA(3));

		// Tester poser une carte plus petite (false) (pas de la dizaine d'en dessous)
		assertFalse(J.poserPileA(2));

		// tester pareil que sommet (false)
		assertFalse(J.poserPileA(3));

	}

	void testPoserPileAA() {
		Joueur J = new Joueur("J");
		Joueur JAdv = new Joueur("JAdv");
		// il faut d'abord poser une carte sur la pile ascendante
		// car on ne peut pas jouer sur la pile ennemie si elle est à 1
		JAdv.poserPileA(13);
		//la pile ascendante adverse est à 13

		// Tester quand la carte est plus petite que le sommet Ascendant adv (true)
		assertTrue(J.poserPileAA(JAdv, 10));

		// Tester quand la carte est plus grande que le sommet Ascendant adv (false)
		assertFalse(J.poserPileAA(JAdv, 15));

		// Tester de poser une carte identique (false)
		assertFalse(J.poserPileAA(JAdv, 13));

	}

	void testPoserPileD() {
		// Joueur depart sommet Ascendant 60
		Joueur J = new Joueur("J");

		// Tester quand la carte est plus petite (true)
		assertTrue(J.poserPileD(13));

		// Tester quand la carte est la dizaine au dessus (true)
		assertTrue(J.poserPileA(23));

		// Tester poser une carte plus grande (false) (pas de la dizaine d'en dessous)
		assertFalse(J.poserPileA(24));

		// tester pareil que sommet (false)
		assertFalse(J.poserPileA(23));
	}

	void testPoserPileDA() {
		Joueur J = new Joueur("J");
		Joueur JAdv = new Joueur("JAdv");
		// il faut d'abord poser une carte sur la pile descendante
		// car on ne peut pas jouer sur la pile ennemie si elle est à 60
		JAdv.poserPileD(13);
		//la pile descendante adverse est à 13

		// Tester quand la carte est plus grande que le sommet Descendant adv (true)
		assertTrue(J.poserPileAA(JAdv, 14));

		// Tester quand la carte est plus petite que le sommet Descendant adv (false)
		assertFalse(J.poserPileAA(JAdv, 12));

		// Tester de poser une carte identique (false)
		assertFalse(J.poserPileAA(JAdv, 14));
	}

}
