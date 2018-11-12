package marioV2;

import java.awt.Graphics;
import java.util.ArrayList;

import marioV2.Fenetre.Panel;

public class Map implements Valeurs{
	
	ArrayList<Bloc> listeBloc;

	public Map() {
		listeBloc = new ArrayList<>();
	}
	
	
	/*
	 * Cr�ation de la carte
	 */
	public void creationFlat()
	{
		/*
		 * On cr�e une map avec le sol de hauteur uniforme
		 */
			
		/*
		 * Bloc temp pour dimensions
		 */
		Bloc blocTemp = new Bloc(-1, -1);
		int longueur = blocTemp.longueur;
		int hauteur = blocTemp.hauteur;
		
		for(int x = 0; x <= (longueurFenetre - longueur); x+=longueur)
		{
			listeBloc.add(new Bloc(x,(hauteurFenetre - hauteur - 30) ));
		}
		
	}
	
	/*
	 * Dessine tous les blocs
	 */
	public void dessin(Graphics g)
	{
		if(listeBloc != null) 
		{
			for(Bloc bloc : listeBloc)
			{
				g.drawImage(bloc.img, bloc.posX, bloc.posY, null);
			}
		}
	}
	

}
