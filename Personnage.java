package marioV2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personnage implements Valeurs{
	
	
	public BufferedImage img[][] = new BufferedImage[2][9];
	public int indiceImgDepl;
	
	public int posX;
	public int posY;
	
	public int longueur;
	public int hauteur;
	
	public Map map;
	
	

	public Personnage(int posX, int posY) 
	{
		
		initDeplImg();
		
		this.posX = posX;
		this.posY = posY;
		this.indiceImgDepl = 0;
		
		this.longueur = img[1][indiceImgDepl].getWidth();
		this.hauteur = img[1][indiceImgDepl].getHeight();
		
	}
	
	public void initDeplImg()
	{
		for(int a = 0; a <= 1; a ++)
		{
			for(int i = 1; i <= 8; i++)
			{
				try {
					img[a][i] = ImageIO.read(new File("" + pathImg + imgStr[a] + i + ".png"));
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public boolean isCollisionToBloc(Direction direction)
	{
		/*
		 * On récup les 3 blocs a traiter
		 * 1 bloc av. pos, le bloc actuel, le bloc suiv.
		 */
		int indiceBloc = 0;
		for(Bloc bloc : map.listeBloc)
		{
			if(this.posX <= bloc.posX)
			{
				break;
			}
			indiceBloc++;
		}
		
		
		switch(direction)
		{
		case DOWN:
			if(map.listeBloc.get(indiceBloc).posY > (this.posY + this.hauteur) )
			{
				/*
				 * Le bloc est plus bas que le perso
				 */
				return false;
			}
			else if(map.listeBloc.get(indiceBloc).posY >= (this.posY + this.hauteur) )
			{
				return true;
			}
			break;
		case INIT:
			break;
		case LEFT:
			if(this.posX > 0)
			{
				/*
				 * Le bloc est plus bas que le perso
				 */
				return false;
			}
			else if(this.posX == 0)
			{
				return true;
			}
			break;
		case RIGHT:
			break;
		case UP:
			break;
		default:
			break;
		
		}
		return true;
	}
	
	public void gravite()
	{
		
	}

}
