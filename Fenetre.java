package marioV2;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Chess.Fenetre.Panel;

public class Fenetre extends JFrame implements Valeurs{
	
	private Panel panel = null; 
	private Map map = null;
	Fenetre fenetre = null;

	public Fenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(longueurFenetre, hauteurFenetre);
		setTitle("Jeux de mario V2");
		setVisible(true);
		
		
		panel = new Panel();
		
		
		
		map = new Map();
		
		
		map.creationFlat();
		
		panel.setVisible(true);
		panel.setFocusable(true);
		
		add(panel);
		
		this.resize(longueurFenetre, hauteurFenetre+1);
		
	}
		
	
	public class Panel extends JPanel
	{
		@Override
		public void paint(Graphics g) {

			super.paint(g);
				
			if(map != null)
			{
				map.dessin(g);
			}
			
			
		}
		
			
	}

}
