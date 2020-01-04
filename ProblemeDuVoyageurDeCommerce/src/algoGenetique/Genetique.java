package algoGenetique;

import static main.Main.k;
import static main.Main.nbGenerationSeconde;
import static main.Main.population;
import static main.Main.KMeilleursParents;
import org.apache.commons.lang.time.StopWatch;
import population.Individu;

/**
 *Classe pour lancer l'algo génétique.
 */
public class Genetique {
    private Boolean b;
    
    public Genetique() {
        b = true;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }
    
    /**
     *Méthode pour lancer l'algo génétique.
     */
    public void lancer(){
        StopWatch s = new StopWatch();
        s.start();
        Integer nbFois = 0; 
        Individu meilleur = population.meilleur();
        System.out.println(meilleur.Path().getVilles());
        System.out.println(meilleur.getFitness());
        //KMeilleursParents = population.selectionKMeilleur(k);
        KMeilleursParents = population.selectionTournoi();
        population.remplacementTotal(population.creationEnfants());
        nbFois++;
        //while (nbFois != nbGenerationSeconde){
        while (s.getTime() < (long)nbGenerationSeconde*1000){
            //KMeilleursParents = population.selectionKMeilleur(k);
            KMeilleursParents = population.selectionTournoi();
            population.remplacementTotal(population.creationEnfants());
            meilleur = population.meilleur();
            System.out.println(meilleur.Path().getVilles());
            System.out.println(meilleur.getFitness());
            nbFois++;
        }
        s.stop();
    }
}
