package marioV2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Chess.Fenetre.Panel;
import mario.Chrono;
import mario.Valeurs.Direction;

public class Fenetre extends JFrame implements Valeurs{
	
	private Panel panel = null; 
	public Map map = null;
	Fenetre fenetre = null;
	
	private ArrayList<Integer> touche_tapee = new ArrayList<>();
	
	public Partie partie;
	
	public volatile Personnage perso;
	

	public Fenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(longueurFenetre, hauteurFenetre);
		setTitle("Jeux de mario V2");
		setVisible(true);
		
		
		panel = new Panel();
		
		
		
		map = new Map();
		
		
		map.creationFlat();
		
		partie = new Partie(map,this);
		
		panel.setVisible(true);
		panel.setFocusable(true);
		
		add(panel);
		
		//Retourne le perso a contrôler
		perso = partie.ctrlUser();
		
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				for(int i = 0; i < touche_tapee.size(); i++)
				{
					if(touche_tapee.get(i) == arg0.getKeyCode())
					{
						touche_tapee.remove(i);
						//System.out.println("Touche relachée");
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub		
				
				boolean test = false;
				for(Integer touche : touche_tapee)
				{
					if(touche == arg0.getKeyCode())
					{
						test = true;
						//System.out.println("Touche already exist");
					}
				}
				
				if( (test == false) && (touche_tapee.size() <= 2) )
				{
					//La touche n'est pas encore mémorisée && que 2 touches max
					touche_tapee.add(arg0.getKeyCode());
					//System.out.println("Touche ajoutée");
				}
				
			}
		});
		
		this.resize(longueurFenetre, hauteurFenetre+1);
		
	}
		
	public void gestionDeplacementClavier()
	{
		if( (touche_tapee != null) && (map != null) && (perso != null))
		{			
			for(Integer touche : touche_tapee)
			{
				if(touche == KeyEvent.VK_LEFT)
				{
					perso.move(Direction.LEFT);
				}
				if(touche == KeyEvent.VK_RIGHT)
				{
					perso.move(Direction.RIGHT);
				}
				if(touche == KeyEvent.VK_UP)
				{
					perso.move(Direction.UP);
				}
				if(touche == KeyEvent.VK_DOWN)
				{
					perso.move(Direction.DOWN);
				}
			}
		}
	}
	
	
	
	
	
	
	public class Panel extends JPanel
	{
		@Override
		public void paint(Graphics g) {

			super.paint(g);
				
			if(map != null)
			{
				map.dessin(g);
				
				partie.draw(g);
				
			}
			
			
		}
		
			
	}

}
