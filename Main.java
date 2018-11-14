package marioV2;

public class Main {

	private static Fenetre fenetre;
	private static Personnage perso;
	
	public static void main(String[] args){

		fenetre = new Fenetre();
		
		Personnage perso_ = new Personnage(0,0,fenetre.map);
		
		perso = new Personnage(200, (fenetre.map.listeBloc.get(0).posY - perso_.hauteur) ,fenetre.map);
		fenetre.perso = perso;

		while(true)
		{
			perso.jump();
			perso.gravite();
			
			fenetre.gestionDeplacementClavier();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fenetre.repaint();
			
		}

	}

}
