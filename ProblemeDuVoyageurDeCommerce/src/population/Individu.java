package population;

import ville.*;
import java.util.ArrayList;
import java.util.Collections;
import static main.Main.distance;

public class Individu {
  private Chemin path;
  private Double fitness;

    /**
     *Méthode pour créer un individu lorsqu'on a déjà le chemin et la fitness.
     * @param cities Chemin des différentes villes.
     * @param fitness Ditance du chemin.
     */
    public Individu(String begin, String end, Matrice datas) {
        path = datas.randomWalk(begin,end);
        fitness = path.getLength();
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
        Double copieFitness = fitness;
				Individu copieIndividu = this;
				int nbAlea1 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1)); //il ne faut pas changer la ville de départ et de retour
				int nbAlea2 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1));
				String tempVille = copieVilles.get(nbAlea1);
				copieVilles.set(nbAlea1, path.getVilles().get(nbAlea2));
				copieVilles.set(nbAlea2, tempVille);
				while (!verificationChemin()){ //Si la mutation a fait un cities impossible
					//On revient à avant la mutation
					copieVilles = path.getVilles();
	    		copieFitness = fitness;
					//On refait une mutation
					nbAlea1 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1));
					nbAlea2 = (int) (Math.random()*((distance.getVilles().size() - 1) - 1));
					tempVille = copieVilles.get(nbAlea1);
					copieVilles.set(nbAlea1, path.getVilles().get(nbAlea2));
					copieVilles.set(nbAlea2, tempVille);
				}
				this.cities = copieVilles;
        this.fitness = calculFitness(this.cities); //on modifie la fitness de l'individu car ce n'est plus le même chemin
    }

    /**
     *Méthode pour vérifier si le chemin créé est valide.
     * @return True ou False en fonction de la validité du chemin passé en argument.
     */
    public Boolean verificationChemin(){
        Boolean bool = true;
	Integer i = 0;
	while (bool && i < this.cities.size() - 1){ //< et  -1 car on ne peut pas vérifier si il existe un cities entre la dernière ville et rien
		int pos1 = distance.getVilles().indexOf(this.cities.get(i));
		int pos2 = distance.getVilles().indexOf(this.cities.get(i + 1));
		if (distance.getMatrice().get(pos1).get(pos2) == -1 || distance.getMatrice().get(pos1).get(pos2) == 0){ //si il n'y a pas de chemin entre deux villes ou si on va à la même ville alors le chemin n'est pas valide
			bool = false;
		}
		i++;
	}
	return bool;
    }

    public static Integer calculFitness(ArrayList<String> villes){
        return 0; //
    }
}
