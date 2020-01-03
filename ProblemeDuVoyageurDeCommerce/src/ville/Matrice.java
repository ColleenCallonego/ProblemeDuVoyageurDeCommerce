package ville;

import java.util.ArrayList;

/**
 *Classe qui correspond à toutes les informations nécéssaires à propos
 * des distances et des villes.
 * On peut y trouver une ArrayList de String et un tableau/matrice en deux
 * dimensions.
 * On s'aide de l'ArrayList pour savoir à quelle ville correpond un indice dans
 * le tableau.
 * L'indice d'une ville dans l'ArrayList correspond donc à l'indice de cette
 * même ville dans le tableau/matrice.
 */
public class Matrice {
    private ArrayList<String> villes; //ArrayList des villes à visiter
    private ArrayList<ArrayList<Double>> matrice; //Tableau/matrice des distances entre toutes les villes

    /**
     *Constructeur de la classe Matrice.
     * @param matrice Tableau/matrice créé(e) où l'on retrouve toutes les distances.
     * @param villes ArrayList de toutes les villes.
     */
    public Matrice(ArrayList<ArrayList<Double>> matrice, ArrayList<String> villes) {
        this.villes = villes;
        this.matrice = matrice;
    }

    public Chemin randomWalk(String begin, String end) {
      ArrayList<String> indexes = new ArrayList<String>();
      Double length = 0.0;
      String last = begin;
      indexes.add(begin);
      int i = 0;
      int nbVillesChemin;
      if (begin.equals(end)){
          nbVillesChemin = villes.size() - 1;
      }
      else{
          nbVillesChemin = villes.size() - 2;
      }
      while(i<nbVillesChemin) {
          String current = getRandomCity(last, indexes);
          if(!current.equals(end)) {
            indexes.add(current);
            length += matrice.get(villes.indexOf(last)).get(villes.indexOf(current));
            last = current;
            i++;
          }
      }
      indexes.add(end);
            length += matrice.get(villes.indexOf(last)).get(villes.indexOf(end));
      return new Chemin(indexes,length);
    }

    private String getRandomCity(String city, ArrayList<String> indexes){
        Double value = -1.0;
        Integer index = 0;
        Integer max = villes.size();
        do {
            index = random(0,max);
            value = matrice.get(villes.indexOf(city)).get(index);
        } while((value==-1 || value==0 || indexes.contains(villes.get(index))));
        return villes.get(index);
    }

    private int random(int min, int max) {
        return (int)(Math.random() * max + min);
    }

    public ArrayList<ArrayList<Double>> getMatrice() {
      return matrice;
    }

    public ArrayList<String> getVilles() {
      return villes;
    }
}
