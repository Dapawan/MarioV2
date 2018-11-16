package marioV2;

import java.util.ArrayList;
import java.util.Comparator;

public class ResultIA implements Valeurs{
	
	ArrayList<IA> listeIA = new ArrayList<>();

	public ResultIA() {
		// TODO Auto-generated constructor stub
	}
	
	public void sortScoreIA()
	{
		listeIA.sort(new Comparator<IA>() {

			@Override
			public int compare(IA o1, IA o2) {
				// TODO Auto-generated method stub
				return (o2.personnage.score - o1.personnage.score);
			}
		});
		/*
		while(listeIA.size() >= (nbrIAResult + 1) )
		{
			listeIA.remove(nbrIAResult);
		}*/
	}
	
	
	
	
	public void addIA(IA ia) throws CloneNotSupportedException
	{
		IA ia_result;
		
		this.listeIA.add(this.listeIA.size(),(IA) ia.clone());
		sortScoreIA();
		System.out.println(this.toString());
		
		//Clone
		ia_result = (IA) this.listeIA.get(0).clone();
		
		//ia_result.reseauNeuronale.
		
	}
	
	@Override
	public String toString() 
	{
		String str = "**Résultats\n";
		int index = 0;
		
		for(IA ia : listeIA)
		{
			str += "N°" + index + " score = " + ia.personnage.score + " pts\n";
			index++;
		}
		
		return str;
	}

}
