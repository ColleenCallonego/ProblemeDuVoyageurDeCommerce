package algoGenetique;

import static graphic.UI.k;
import static graphic.UI.nbGenerationSeconde;
import static graphic.UI.stratRecombinaison;
import static graphic.UI.stratRemplissage;
import static graphic.UI.stratSelection;
import static graphic.UI.taillePopulation;
import static graphic.UI.tempsCalcul;
import static graphic.UI.villeDepart;
import static graphic.UI.villeRetour;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JTextField;
import static main.Main.distance;
import org.apache.commons.lang.time.StopWatch;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
    public void lancer(XYPlot plot, JTextField solutionChemin, JTextField solutionFitness) throws InterruptedException{
        StopWatch s = new StopWatch();
        s.start();
        Double nbFois = 0.0; 
        int nbFoisI = 0;
        Individu meilleur = population.meilleur();
        System.out.println(meilleur.Path().getVilles());
        System.out.println(meilleur.getFitness());
        XYSeries series = new XYSeries("Fitness");
        series.add(nbFois, meilleur.getFitness());
        XYDataset dataset = new XYSeriesCollection(series);
        plot.setDataset(dataset);
        if (tempsCalcul.equals("Par génération")){
            System.out.println("MERDE");
            while (nbFoisI != nbGenerationSeconde){
                if (stratSelection.equals("Elitiste")){
                    KMeilleursParents = population.selectionKMeilleur(k);
                }
                else{
                    KMeilleursParents = population.selectionTournoi();
                }
                if (stratRemplissage.equals("Parents et enfants")){
                    population.remplacementPartiel(population.creationEnfants());
                }
                else{
                    population.remplacementTotal(population.creationEnfants());
                }
                nbFois++;
                nbFoisI++;
                meilleur = population.meilleur();
                
        System.out.println(meilleur.Path().getVilles());
        System.out.println(meilleur.getFitness());
                series.add(nbFois, meilleur.getFitness());
                dataset = new XYSeriesCollection(series);
                plot.setDataset(dataset);
            }
        }
        else if (tempsCalcul.equals("Par seconde")){
            while (s.getTime() < (long)nbGenerationSeconde*1000){
                if (stratSelection.equals("Elitiste")){
                    KMeilleursParents = population.selectionKMeilleur(k);
                }
                else{
                    KMeilleursParents = population.selectionTournoi();
                }
                if (stratRemplissage.equals("Parents et enfants")){
                    population.remplacementPartiel(population.creationEnfants());
                }
                else{
                    population.remplacementTotal(population.creationEnfants());
                }
                nbFois++;
                nbFoisI++;
                meilleur = population.meilleur();
                series.add(nbFois, meilleur.getFitness());
                dataset = new XYSeriesCollection(series);
                plot.setDataset(dataset);
            }
        }
        else{
            while (b){
                if (stratSelection.equals("Elitiste")){
                    KMeilleursParents = population.selectionKMeilleur(k);
                }
                else{
                    KMeilleursParents = population.selectionTournoi();
                }
                if (stratRemplissage.equals("Parents et enfants")){
                    population.remplacementPartiel(population.creationEnfants());
                }
                else{
                    population.remplacementTotal(population.creationEnfants());
                }
                nbFois++;
                nbFoisI++;
                meilleur = population.meilleur();
                series.add(nbFois, meilleur.getFitness());
                dataset = new XYSeriesCollection(series);
                plot.setDataset(dataset);
                sleep(3000);
            }
        }
        s.stop();
    }
}