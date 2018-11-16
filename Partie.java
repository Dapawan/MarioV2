package marioV2;

import java.awt.Graphics;
import java.util.ArrayList;

public class Partie implements Valeurs{

	ArrayList<Personnage> joueurEquipeGauche = new ArrayList<>();
	ArrayList<Personnage> joueurEquipeDroite = new ArrayList<>();
	
	Map map;
	Fenetre fenetre;
	
	ArrayList<IA> iaEquipeGauche = new ArrayList<>();
	ArrayList<IA> iaEquipeDroite = new ArrayList<>();
	
	ResultIA resultIA = new ResultIA();
	
	
	public Partie(Map map, Fenetre fenetre) 
	{
		this.map = map;
		this.fenetre = fenetre;
		
		resetGame();
		
		
	}
		
	public void givePersoAdv()
	{
		/*
		 * On donne les persos adv --> Détection de collision
		 */
		for(int i = 0; i < nbrIAEquipeGauche; i++)
		{
			joueurEquipeGauche.get(i).persoAdv = joueurEquipeDroite.get(i);
			joueurEquipeDroite.get(i).persoAdv = joueurEquipeGauche.get(i);
		}
	}
	
	public Personnage ctrlUser()
	{
		switch(ctrlUser)
		{
		case Aucun:
			return null;
		case JDroite:
			return joueurEquipeDroite.get(0);
		case JGauche:
			return joueurEquipeGauche.get(0);
		default:
			break;
		
		}
		return null;
	}
	
	public void ctrlUserVoid()
	{
		switch(ctrlUser)
		{
		case Aucun:
			break;
		case JDroite:
			fenetre.perso = joueurEquipeDroite.get(0);
		case JGauche:
			fenetre.perso = joueurEquipeGauche.get(0);
		default:
			break;
		
		}
	}
	
	public void draw(Graphics g)
	{
		for(Personnage perso : joueurEquipeGauche)
		{
			if(ctrlUser == CtrlUser.JGauche)
			{
				g.drawString("User", perso.posX, perso.posY - 10);
			}
			else
			{
				g.drawString("IA Gauche", perso.posX, perso.posY - 10);
				if(iaEquipeGauche.get(0).chrono != null)
				{
					g.drawString("Chrono IA G : " + iaEquipeGauche.get(0).chrono.toString(), posXJGscore, posYChrono);
				}
			}
			perso.draw(g);
		}
		
		for(Personnage perso : joueurEquipeDroite)
		{
			if(ctrlUser == CtrlUser.JDroite)
			{
				g.drawString("User", perso.posX, perso.posY - 10);
			}
			else
			{
				g.drawString("IA Droite", perso.posX, perso.posY - 10);
				if(iaEquipeDroite.get(0).chrono != null)
				{
					g.drawString("Chrono IA D : " + iaEquipeDroite.get(0).chrono.toString(), posXJDscore, posYChrono);
				}
			}
			perso.draw(g);
		}
		
		/*
		 * Score
		 */
		if( (joueurEquipeGauche.size() > 0) && (joueurEquipeDroite.size() > 0))
		{
			g.drawString("Score JG : " + joueurEquipeGauche.get(0).score, posXJGscore, posYScore);
			g.drawString("Score JD : " + joueurEquipeDroite.get(0).score, posXJDscore, posYScore);
		}
	}
	
	
	public void run() throws CloneNotSupportedException
	{
		for(IA ia : iaEquipeGauche)
		{
			ia.calculDeplacement();
		}
		
		for(IA ia : iaEquipeDroite)
		{
			ia.calculDeplacement();
		}
		
		
		/*
		 * Check si l'IA a le bon comportement
		 */
		if( (iaEquipeGauche.size() > 0) && (iaEquipeGauche.get(0).scoreUP == false))
		{
			iaEquipeGauche.get(0).personnage.posX = posXGauche;
			iaEquipeGauche.get(0).reset(iaEquipeGauche.get(0).personnage, iaEquipeDroite.get(0).personnage);
			//System.out.println("Reset");
			iaEquipeGauche.get(0).personnage.refreshScore();
			resultIA.addIA((IA) iaEquipeGauche.get(0).clone());
			
			
		}
		if( (iaEquipeDroite.size() > 0) && (iaEquipeDroite.get(0).scoreUP == false))
		{
			if(iaEquipeGauche.size() == 0)
			{
				iaEquipeDroite.get(0).reset(iaEquipeDroite.get(0).personnage, joueurEquipeGauche.get(0));
			}
			else
			{
				iaEquipeDroite.get(0).personnage.posX = posXDroite;
				iaEquipeDroite.get(0).reset(iaEquipeDroite.get(0).personnage, iaEquipeGauche.get(0).personnage);
			}
			//System.out.println("Reset");
			iaEquipeDroite.get(0).personnage.refreshScore();
			resultIA.addIA((IA) iaEquipeDroite.get(0).clone());
			
			
		}
		
		for(Personnage perso : joueurEquipeGauche)
		{
			perso.gravite();
			perso.jump();
		}
		
		for(Personnage perso : joueurEquipeDroite)
		{
			perso.gravite();
			perso.jump();
		}
		
		int resultHit = isContactHead();
		if(resultHit == 1)
		{
			joueurEquipeGauche.get(0).score = scoreMAX;
			resetGame();
		}
		else if(resultHit == 2)
		{
			joueurEquipeDroite.get(0).score = scoreMAX;
			resetGame();
		}
	}
	
	
	public int isContactHead()
	{
		Personnage persoGauche;
		Personnage persoDroite;
		
		for(int i = 0; i < nbrIAEquipeGauche; i++)
		{
			persoGauche = joueurEquipeGauche.get(i);
			persoDroite = joueurEquipeDroite.get(i);
			
			
			if( (((persoGauche.posX + persoGauche.longueur) >= persoDroite.posX) && ((persoGauche.posX + persoGauche.longueur) <= (persoDroite.posX + persoDroite.longueur) ))
					|| ((persoGauche.posX >= persoDroite.posX) && (persoGauche.posX <= (persoDroite.posX + persoDroite.longueur))) )
			{
				if( ( (persoGauche.posY + persoGauche.hauteur) >= persoDroite.posY) && ((persoGauche.posY + persoGauche.hauteur) <= (persoDroite.posY + hauteurXHitTete))  )
				{
					return 1;
				}
				else if( ( (persoDroite.posY + persoDroite.hauteur) >= persoGauche.posY) && ((persoDroite.posY + persoDroite.hauteur) <= (persoGauche.posY + hauteurXHitTete))  )
				{
					return 2;
				}
			}
			
		}
		return 0;
	}
	
	
	public void resetGame()
	{
		
		/*
		 * On suppr tout
		 */
		joueurEquipeGauche.removeAll(joueurEquipeGauche);
		joueurEquipeDroite.removeAll(joueurEquipeDroite);
		
		Personnage perso_ = new Personnage(0, 0, map);
		
		int posY = map.listeBloc.get(0).posY - perso_.longueur - 50;
		
		for(int i = 0; i < nbrIAEquipeGauche; i++)
		{
			joueurEquipeGauche.add(new Personnage(posXGauche, posY, map));
		}
		
		for(int i = 0; i < nbrIAEquipeDroite; i++)
		{
			joueurEquipeDroite.add(new Personnage(posXDroite, posY, map));
		}
		
		//Passe les persos aux IA
		setIA();
		
		//Renvoie le nouveau perso user (ctrl clavier)
		ctrlUserVoid();
		
		/*
		 * On donne les persos adv --> Détection de collision
		 */
		givePersoAdv();
	}
	
	
	
	/*
	 * Gestion IA
	 * 
	 */
	public void setIA()
	{
		/*
		 * On suppr les IA
		 */
		iaEquipeGauche.removeAll(iaEquipeGauche);
		iaEquipeDroite.removeAll(iaEquipeDroite);
		
		for(int i = 0; i < nbrIAEquipeGauche; i++)
		{
			if(ctrlUser != CtrlUser.JGauche)
			{
				IA ia_temp = new IA();
				
				ia_temp.reset(joueurEquipeGauche.get(i), joueurEquipeDroite.get(i));
				iaEquipeGauche.add(ia_temp);
			}
		}
		
		for(int i = 0; i < nbrIAEquipeDroite; i++)
		{
			if(ctrlUser != CtrlUser.JDroite)
			{
				IA ia_temp = new IA();
				
				ia_temp.reset(joueurEquipeDroite.get(i), joueurEquipeGauche.get(i));
				iaEquipeDroite.add(ia_temp);
			}
		}
	}
	
	
	
	
	
	
	

}
