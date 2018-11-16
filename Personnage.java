package marioV2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personnage implements Valeurs{
	
	
	public BufferedImage img[][] = new BufferedImage[2][9];
	
	
	public BufferedImage imgInstant;
	public int indiceImgDepl;
	
	public int posX;
	public int posY;
	
	public int longueur;
	public int hauteur;
	
	public boolean isJumping;
	public int incrJump;
	public int indiceIncrJump;
	
	public Map map;
	
	public Personnage persoAdv;
	public int score;
	
	

	public Personnage(int posX, int posY, Map map) 
	{
		this.map = map;
		
		initDeplImg();
		
		this.posX = posX;
		this.posY = posY;
		this.indiceImgDepl = 1 * multiplicateurImgAnimMove;
		this.isJumping = false;
		this.incrJump = 1;
		this.indiceIncrJump = 1;
		
		this.longueur = img[1][1].getWidth();
		this.hauteur = img[1][1].getHeight();
		
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
		
		imgInstant = img[1][1];
	}
	
	
	public void refreshScore()
	{
		/*
		 * La proximitée entre les pieds du joueur et la tête de son adv
		 * 
		 */
		int result = 0;
		
		result = scoreBase - ( Math.abs(this.posX - persoAdv.posX));// + Math.abs((this.posY + this.hauteur) - (persoAdv.posY + hauteurXHitTete)) );
		
		this.score = result;
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
		/*
		 * limite à indice max
		 */
		if(indiceBloc >= map.listeBloc.size())
		{
			indiceBloc = map.listeBloc.size() - 1;
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
		case LEFT:
			if(this.posX > 0)
			{
				/*
				 * Le perso est dans le level
				 */
				return false;
			}
			else if(this.posX <= 0)
			{
				//Le perso est à la limite du niveau
				return true;
			}
			break;
		case RIGHT:
			if( (this.posX + this.longueur + OffsetLongueurPerso) < longueurFenetre)
			{
				/*
				 * Le perso est dans le level
				 */
				return false;
			}
			else
			{
				return true;
			}
		case UP:
			/*
			 * Pour le jump
			 */
			return false;
		default:
			break;
		
		}
		return true;
	}
	
	public void gravite()
	{
		if(isCollisionToBloc(Direction.DOWN) == false)
		{
			/*
			 * On peut faire descendre le perso
			 */
			this.posY += graviteY;
		}
	}

	public boolean move(Direction direction)
	{
		//gravite();
		//jump();
		
		refreshScore();
		
		if( (isCollisionToBloc(direction) == false) && (isCollisionPerso(direction) == false) )
		{
			switch(direction)
			{
			case DOWN:
				this.posY += deplY;
				this.isJumping = false;
				break;
			case LEFT:
				this.posX -= deplX;
				break;
			case RIGHT:
				this.posX += deplX;
				break;
			case UP:
				/*
				 * On autorise le jump si le perso touche le sol
				 */
				if( (isCollisionToBloc(Direction.DOWN) == true) && (this.isJumping == false) )
				{
					resetJump();
					this.isJumping = true;
				}
				break;
			default:
				break;
			
			}
			
			gestionAnimMove(direction);
			
			return true;
		}
		else
		{
			/*
			 * Move impossible
			 */
			return false;
		}
	}
	
	public void jump()
	{
		if(isJumping == true)
		{
			/*
			 * On lance le jump
			 */
			if(indiceIncrJump == 1)
			{
				/*
				 * Départ du saut -> incr max
				 */
				incrJump = (longueurJumpX * longueurJumpX);
			}
			
			
			if(indiceIncrJump++ <= (longueurJumpX / 2))
			{
				/*
				 * on décr
				 */
				int v_Temp = longueurJumpX - indiceIncrJump;
				incrJump = (v_Temp * v_Temp);
				this.posY -= (incrJump / diviseurJumpY);
			}
			else if(indiceIncrJump <= longueurJumpX)
			{
				/*
				 * On incr
				 */
				int v_Temp = indiceIncrJump - (longueurJumpX / 2);
				incrJump = (v_Temp * v_Temp);
				this.posY += (incrJump / diviseurJumpY);
			}
			else
			{
				/*
				 * Fin de saut
				 */
				resetJump();
			}
			
			
		}
	}
	
	public void resetJump()
	{
		incrJump = 1;
		indiceIncrJump = 1;
		this.isJumping = false;
	}

	public void gestionAnimMove(Direction direction)
	{
		switch(direction)
		{
		case DOWN:
			break;
		case LEFT:
			if( (indiceImgDepl++) >= (9 * multiplicateurImgAnimMove) - 1)
			{
				indiceImgDepl = 1 * multiplicateurImgAnimMove;
			}
			imgInstant = img[0][ (indiceImgDepl / multiplicateurImgAnimMove) ];
			break;
		case RIGHT:
			if( (indiceImgDepl++) >= (9 * multiplicateurImgAnimMove) - 1)
			{
				indiceImgDepl = 1 * multiplicateurImgAnimMove;
			}
			imgInstant = img[1][ (indiceImgDepl / multiplicateurImgAnimMove) ];
			break;
		case UP:
			break;
		default:
			break;
		
		}
		
	}
	
	
	public boolean isCollisionPerso(Direction direction)
	{
		int newPosX = this.posX;
		
		switch(direction)
		{
		case DOWN:
			break;
		case LEFT:
			newPosX-=deplX;
			break;
		case RIGHT:
			newPosX+=deplX;
			break;
		case UP:
			break;
		default:
			break;
		
		}
		if(persoAdv != null)
		{
			if( (((newPosX + this.longueur) >= persoAdv.posX) && ((newPosX + this.longueur) <= (persoAdv.posX + persoAdv.longueur) ))
					|| ((newPosX >= persoAdv.posX) && (newPosX <= (persoAdv.posX + persoAdv.longueur))) )
			{
				/*
				 * Même x
				 */
				if( (this.posY + this.hauteur) >= (persoAdv.posY - hauteurXHitTete) )
				{
					return true;
				}
			}
					
		}
		
		return false;
	}
	
	public void draw(Graphics g)
	{
		if(imgInstant != null)
		{
			g.drawImage(imgInstant, this.posX, this.posY, null);
		}
	}
}
