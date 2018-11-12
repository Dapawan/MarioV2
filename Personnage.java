package marioV2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personnage implements Valeurs{
	
	
	public BufferedImage img[][] = new BufferedImage[2][9];
	public int posX;
	public int posY;
	
	

	public Personnage(int posX, int posY) 
	{
		
		initDeplImg();
		
		this.posX = posX;
		this.posY = posY;
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

}
