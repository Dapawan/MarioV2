package marioV2;

import java.awt.Graphics;
import java.util.ArrayList;

public class Partie implements Valeurs{

	ArrayList<Personnage> joueurEquipeGauche = new ArrayList<>();
	ArrayList<Personnage> joueurEquipeDroite = new ArrayList<>();
	
	Map map;
	
	
	public Partie(Map map) 
	{
		this.map = map;
		
		initJoueur();
		
		
	}
	
	public void initJoueur()
	{
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
			}
			perso.draw(g);
		}
	}
	
	
	public void run()
	{
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
			System.out.println("CONTACT J gauche!");
		}
		else if(resultHit == 2)
		{
			System.out.println("CONTACT J droite!");
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
			
			int posXCentralePersoGauche = persoGauche.posX + (persoGauche.longueur / 2);
			int posXCentralePersoDroite = persoDroite.posX + (persoDroite.longueur / 2);
			
			
			if( (posXCentralePersoGauche >= persoDroite.posX) && ( posXCentralePersoGauche <= (persoDroite.posX + persoDroite.longueur) )  )
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

}
