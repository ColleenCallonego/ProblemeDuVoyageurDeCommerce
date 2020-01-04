package population;

import ville.*;
import java.util.ArrayList;
import java.util.Random;
import static main.Main.distance;

public class Individu implements Comparable<Individu>{
  private Chemin path;
  private Double fitness;

    /**
     *Méthode pour créer un individu lorsqu'on a déjà le chemin et la fitness.
     * @param path Chemin des différentes villes.
     * @param fitness Ditance du chemin.
     */
    public Individu(Chemin path, Double fitness){
        this.path = path;
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Individu{" + "path=" + path + ", fitness=" + fitness + '}';
    }
    
    /**
     *Méthode pour parser le chemin en String.
     * @return
     */
    public String parsPathToString(){
        return "" + path.getVilles();
    }
    
    /**
     *Méthode pour parse la fitness en String.
     * @return
     */
    public String parsFitnessToString(){
        return "" + fitness;
    }

    public Individu(String begin, String end, Matrice datas) {
        path = datas.randomWalk(begin,end);
        fitness = path.getLength();
    }

    public Chemin getPath() {
        return path;
    }

    /**
     *Getteur du chemin.
     * @return Une ArrayList de String contenant les villes du chemin.
     */
    public Chemin Path() {
        return path;
    }

    /**
     *Getteur de la fitness/distance du chemin.
     * @return Un Double qui correspond à la distance en kilomètre du chemin.
     */
    public Double getFitness() {
        return fitness;
    }

    /**
     *Méthode pour muter un individu.
     */
    public void mutation(){
        ArrayList<String> copieVilles = path.getVilles();
        Random r = new Random();
        Individu copieIndividu = this;
        Integer nbAlea1 = r.nextInt(path.getVilles().size() - 2) + 1;
        Integer nbAlea2 = r.nextInt(path.getVilles().size() - 2) + 1;
        /*int nbAlea1 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1)); //il ne faut pas changer la ville de départ et de retour
        int nbAlea2 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1));*/
        String tempVille = copieVilles.get(nbAlea1);
        copieVilles.set(nbAlea1, path.getVilles().get(nbAlea2));
        copieVilles.set(nbAlea2, tempVille);
        this.path.setVilles(copieVilles);
        this.path.setLength(calculFitness(copieVilles));
        this.fitness = calculFitness(this.path.getVilles()); //on modifie la fitness de l'individu car ce n'est plus le même chemin
    }

    /**
     *Méthode pour calculer la fitness d'une ArrayList.
     * @param villes ArrayList de villes.
     * @return La fitness de l'ArrayList.
     */
    public static Double calculFitness(ArrayList<String> villes){
        Double kilometre = 0.0;
        for(int i = 0; i < villes.size()- 1; i++){
            kilometre = kilometre + distance.getMatrice().get(distance.getVilles().indexOf(villes.get(i))).get(distance.getVilles().indexOf(villes.get(i + 1)));
        }
        return kilometre;
    }

    /**
     *Méthode pour comparer deux individus.
     * @param t Un individu à comparer avec celui qui appelle la méthode.
     * @return -1 si t plus grand, 0 si egal et 1 si t plus petit.
     */
    @Override
    public int compareTo(Individu t) {
        Double d = this.fitness;
        Individu I2 = t;
        Double d2 = I2.fitness;
        if (d-d2 < 0.0){
            return -1;
        }
        else if (d-d2 > 0.0){
            return 1;
        }
        else{
            return 0;
        }
    }
}