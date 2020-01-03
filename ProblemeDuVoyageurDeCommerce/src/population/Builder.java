package population;
import ville.*;
import java.util.ArrayList;

public class Builder extends Thread {
  private Matrice matrice;
  private String begin;
  private String end;
  private int number;
	private ArrayList<Individu> individus;

	/**
	* Default Builder constructor
	*/
	public Builder(Matrice matrice, String begin, String end, int number) {
		this.matrice = matrice;
		this.begin = begin;
		this.end = end;
		this.number = number;
		individus = new ArrayList<Individu>();
	}

	public void run() {
		for(int i=0; i<number; i++) {
			Chemin ah = matrice.randomWalk(begin,end);
			individus.add(new Individu(ah,ah.getLength()));
		}
	}

	public ArrayList<Individu> getIndividus() {
		return individus;
	}
}
