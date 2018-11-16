package marioV2;

import java.util.Comparator;

public class IA implements Valeurs,Cloneable{
	
	public Personnage personnage;
	public Personnage personnageAdv;
	public ReseauNeuronale reseauNeuronale;
	
	public Chrono chrono;
	public long oldTime;
	
	public int scoreOld;
	
	public boolean scoreUP = true;

	public IA() {
		// TODO Auto-generated constructor stub
	}
	
	public void reset(Personnage personnage, Personnage personnageAdv)
	{
		this.chrono = null;
		this.oldTime = 0;
		this.scoreUP = true;
		
		this.personnage = personnage;
		this.personnageAdv = personnageAdv;
		/*
		 * Nouveau réseau de neurone
		 */
		this.reseauNeuronale = new ReseauNeuronale();
	}
	
	
	public double[] returnEntree()
	{
		double[] entree = new double[nbrEntree];
		
		/*
		 * Entree
		 * 1 : diff X
		 * 2 : diff Y
		 */
		
		entree[0] = sigmoideFunction(this.personnage.posX - this.personnageAdv.posX);
		entree[1] = sigmoideFunction(this.personnage.posY - this.personnageAdv.posY);
		
		return entree;
	}
	
	
	public void calculDeplacement()
	{
		//Lancement du chrono
		if(this.chrono == null)
		{
			this.chrono = new Chrono();
			this.chrono.start();
		}
		
		double[] entree = returnEntree();
		
		double[] sortie = new double[nbrNeuroneParCouche[nbrNeuroneParCouche.length - 1]];
		
		sortie = this.reseauNeuronale.result(entree);
		
		for(int i = 0; i < sortie.length; i++)
		{
			if(sortie[i] >= seuilActivationNeurone)
			{
				/*
				 * On fait l'action
				 */
				if(i == Direction.DOWN.ordinal())
				{
					this.personnage.move(Direction.DOWN);
				}
				else if(i == Direction.UP.ordinal())
				{
					this.personnage.move(Direction.UP);
				}
				else if(i == Direction.RIGHT.ordinal())
				{
					this.personnage.move(Direction.RIGHT);
				}
				else if(i == Direction.LEFT.ordinal())
				{
					this.personnage.move(Direction.LEFT);
				}
			}
		}
		
		
		/*
		 * Check si le score UP
		 */
		if( (chrono.getTime() - oldTime) >= TimeOutIA)
		{
			if(this.scoreOld < this.personnage.score)
			{
				this.scoreOld = this.personnage.score;
				oldTime = chrono.getTime();
			}
			else
			{
				/*
				 * Le score n'augmente pas
				 */
				this.scoreUP = false;
			}
			
		}
		
	}
	
	
	public double sigmoideFunction(double value)
	{
		double result = 0.0;
		
		/*
		 * 1/(1+exp(-k*x))
		 */
		result = (1/ (1 + Math.exp(value*-1)) ); //On prend k = 1 pour la fonction
		
		return result;
		
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
