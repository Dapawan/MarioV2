package marioV2;

public class Main {

	private static Fenetre fenetre;
	
	public static void main(String[] args){

		fenetre = new Fenetre();

		while(true)
		{
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
