package marioV2;

import java.io.File;

public interface Valeurs 
{
	
	int longueurFenetre = 900;
	int hauteurFenetre = 900;
		
	
	/*
	 * Neurone
	 */
	double biasMax = 5d;
	double weightMax = 5d;
	/*
	 * Reseau neuronale
	 */
	char nbrEntree = 4;
	char[] nbrNeuroneParCouche = {4,2,4};
	
	
	
	/*
	 * Chemin d'accès img
	 */
	String pathImg = new File("").getAbsolutePath() + "\\src\\marioV2\\Img\\";
	
	//String img
	String imgStr[] = { "depl_gauche_", "depl_droite_"};

	
	//Direction
	enum Direction
	{
		INIT,
		RIGHT,
		LEFT,
		UP,
		DOWN
	}
	
}
