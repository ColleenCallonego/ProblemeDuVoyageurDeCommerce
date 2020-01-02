package population;
import ville.*;
import fichier.*;
import java.util.ArrayList;
import java.util.Collections;

public class Individu {
  private ArrayList<String> cities;
  private Integer fitness;

  public Individu(String begin, String end, Matrice datas) {
    cities = new ArrayList<String>();
    fitness=0;
    path(begin,end,datas);
  }

  private void path(String begin, String end, Matrice datas) {
    cities.add(begin);
    //get datas
    ArrayList<String> indexes = datas.getVilles();
    ArrayList<ArrayList<Integer>> length = datas.getMatrice();
    Integer endIndex = indexes.indexOf(end);
    Integer beginIndex = indexes.indexOf(begin);

    //local variables
    Integer tryFitness = 0;
    boolean found=false;
    Integer currentIndex = beginIndex;
    while(!found) {
      
    }

  }

  private Integer randomCity(Integer[] cities) {
    //return (int)(Math.random() * max + min);
  }
}
