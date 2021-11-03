package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import jeu.*;

class TestTour {

	@Test
	void testJouer() {
		Joueur J = new Joueur("J");
		J.setMain(new LinkedList<>());
		Joueur JAdv = new Joueur("JAvd");

		Tour tour = new Tour(J, JAdv);

		// au départ, la pile ascendante du joueur est à 1
		// au départ, la pile descendante du joueur est à 60

		// proposition qui marche

		// pour cela il faut ajouter des cartes dans la main qui est pour l'instant vide
		J.getMain().add(12);
		J.getMain().add(13);
		J.getMain().add(55);

		String[] propositionsOK = { "12^", "13^", "55v" };

		try {
			tour.jouer(propositionsOK);
		} catch (IllegalArgumentException e) {
			fail("Ce coup est censé marcher");
		}

		// moins de 2 propositions (rate)
		J.getMain().add(14);

		String[] pasAssezDeProp = { "14^" };

		try {
			tour.jouer(pasAssezDeProp);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}

		// 2 premier caractères sont pas des chiffres (rate)

		String[] pasDeChiffres = { "courgette^", "pamplemoussev" };

		try {
			tour.jouer(pasDeChiffres);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}

		// 3e caractere ni ^ ni v (rate)

		String[] TroisiemeCaracPasOK = { "12$", "13j" };

		try {
			tour.jouer(TroisiemeCaracPasOK);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}

		// 4e caractere n'est pas ' (rate)
		
		String[] QuatriemeCaracPasOK = { "12^r", "13^" };

		try {
			tour.jouer(QuatriemeCaracPasOK);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}

		// poser deux fois chez l'adversaire (rate)
		
		String[] jouerDeuxFoisChezAdv = { "12^'", "13^'" };

		try {
			tour.jouer(jouerDeuxFoisChezAdv);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}


		// test contient main
		//pour cela on va tester de jouer des cartes qui ne sont pas dans la main (rate)
		//dans la main nous avons actuellement les cartes : 16 17 
		J.getMain().add(16);
		J.getMain().add(17);
		
		String[] pasDansLaMain = { "18^", "19^" };

		try {
			tour.jouer(pasDansLaMain);
			fail("Ce coup est censé rater");
		} catch (IllegalArgumentException e) {
			// s'il rentre ici c'est que ça a marché
		}
		
	}
}