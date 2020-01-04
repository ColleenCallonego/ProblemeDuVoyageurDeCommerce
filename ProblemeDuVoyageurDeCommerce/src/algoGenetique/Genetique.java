package algoGenetique;

import static graphic.UI.nbGenerationSeconde;
import static graphic.UI.taillePopulation;
import static graphic.UI.villeDepart;
import static graphic.UI.villeRetour;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import static main.Main.distance;
import org.apache.commons.lang.time.StopWatch;
import population.Individu;
import population.Population;

/**
 *Classe pour lancer l'algo génétique.
 */
public class Genetique {
    public static ArrayList<Individu> KMeilleursParents;
    public static Population population;
    private Boolean b;
    
    public Genetique() throws InterruptedException {
        population = new Population();
        population.creerPopulation(distance, villeDepart, villeRetour, taillePopulation);
        sleep(500);
        population.verifPopulation();
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