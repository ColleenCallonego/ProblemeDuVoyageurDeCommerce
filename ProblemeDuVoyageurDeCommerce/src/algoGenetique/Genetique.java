package algoGenetique;

import static main.Main.k;
import static main.Main.nbGeneration;
import static main.Main.population;
import static main.Main.KMeilleursParents;
import population.Individu;

/**
 *Classe pour lancer l'algo génétique.
 */
public class Genetique {

    public Genetique() {
        
    }
    
    /**
     *Méthode pour lancer l'algo génétique.
     */
    public void lancer(){
        Integer nbFois = 0;
        Individu meilleur = population.meilleur();
        System.out.println(meilleur.Path().getVilles());
        System.out.println(meilleur.getFitness());
        KMeilleursParents = population.selectionKMeilleur(k);
        population.remplacementTotal(population.creationEnfants());
        nbFois++;
        while (nbFois != nbGeneration){
            KMeilleursParents = population.selectionKMeilleur(k);
            population.remplacementTotal(population.creationEnfants());
            meilleur = population.meilleur();
            System.out.println(meilleur.Path().getVilles());
            System.out.println(meilleur.getFitness());
            nbFois++;
        }
    }
}
