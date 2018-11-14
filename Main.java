package marioV2;

public class Main {

	private static Fenetre fenetre;
	private static Personnage perso;
	
	public static void main(String[] args){

		fenetre = new Fenetre();
		
		while(true)
		{
			fenetre.partie.run();
			
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
