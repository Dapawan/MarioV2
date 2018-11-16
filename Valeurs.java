package marioV2;

import java.io.File;

public interface Valeurs 
{
	
	int longueurFenetre = 900;
	int hauteurFenetre = 900;
		
	/*
	 * TimeOut IA
	 */
	long TimeOutIA = 2000;
	
	
	/*
	 * Nbr de joueur dans les équipes
	 */
	int nbrIAEquipeGauche = 1;
	int nbrIAEquipeDroite = 1;
	/*
	 * Contrôle user
	 * 0 : pas de contrôle
	 * 1 : ctrl joueur gauche
	 * 2 : ctrl joueur droite
	 */
	enum CtrlUser
	{
		Aucun,
		JGauche,
		JDroite
	};
	CtrlUser ctrlUser = CtrlUser.Aucun;
	
	/*
	 * HitBox tête
	 */
	int hauteurXHitTete = 10;
	
	/*
	 * Pos equipe gauche & equipe droite
	 */
	int posXGauche = 200;
	int posXDroite = 700;
	/*
	 * Print score
	 */
	int posXJGscore = posXGauche;
	int posXJDscore = posXDroite;
	int posYScore = 20;
	/*
	 * Print Chrono
	 */
	int posXJGchrono = posXJGscore;
	int posXJDchrono = posXJDscore;
	int posYChrono = posYScore + 20;
	/*
	 * Nbr de resultat d'ia gardé en mémoire
	 */
	int nbrIAResult = 5;
	
	/*
	 * Seuil d'activation neurone
	 */
	double seuilActivationNeurone = 0.75d;
	
	/*
	 * Neurone
	 */
	double biasMax = 5d;
	double weightMax = 5d;
	/*
	 * Reseau neuronale
	 */
	char nbrEntree = 4;
	char[] nbrNeuroneParCouche = {4,3,3,3,4};//{4,2,4};
	
	
	
	/*
	 * Chemin d'accès img
	 */
	String pathImg = new File("").getAbsolutePath() + "\\src\\marioV2\\Img\\";
	
	//String img
	String imgStr[] = { "perso_depl_gauche_", "perso_depl_droite_"};

	
	//Direction
	enum Direction
	{
		RIGHT,
		LEFT,
		UP,
		DOWN
	}
	
	//Gravité
	int graviteY = 1;
	//Score départ
	int scoreBase = 0;
	int scoreMAX = 10;
	//Move
	int deplX = 3;
	int deplY = 1;
	//Jump
	int longueurJumpX = 100;//100--> pour deplX = 1
	int diviseurJumpY = 1300;//1300
	//Multiplicateur imgAnimMove
	int multiplicateurImgAnimMove = 10;
	
	//OffsetLongueurPerso
	int OffsetLongueurPerso = 20;
	
	
	
}
