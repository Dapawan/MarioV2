package marioV2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bloc implements Valeurs{

	public BufferedImage img;
	
	public int posX;
	public int posY;
	public int longueur;
	public int hauteur;
	
	
	public Bloc(int posX, int posY) 
	{
		try {
			this.img = ImageIO.read(new File("" + pathImg + "terreherbe.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.longueur = img.getWidth();
		this.hauteur = img.getHeight();
		
		this.posX = posX;
		this.posY = posY;
	}

}
