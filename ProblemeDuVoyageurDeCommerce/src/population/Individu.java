package population;
import ville.*;
import fichier.*;
import java.util.ArrayList;
import java.util.Collections;

public class Individu {
  private Chemin path;
  private Double fitness;

  public Individu(String begin, String end, Matrice datas) {
    path = datas.randomWalk(begin,end);
    fitness = path.getLength();
  }
  
	/**
	* Returns value of path
	* @return
	*/
	public Chemin getPath() {
		return path;
	}

	/**
	* Returns value of fitness
	* @return
	*/
	public Double getFitness() {
		return fitness;
	}
}
