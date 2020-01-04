package main;

import algoGenetique.Genetique;
import fichier.Lecture;
import graphic.UI;
import ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static Matrice distance;
    public static Genetique g;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        distance = Lecture.creationMatrice();
        /*nbGenerationSeconde = 5;
        System.out.println(nbGenerationSeconde);
        k = 20;
        nbIndividuTournoi = 4;
        tauxMutation = 0.05;
        villeDepart = distance.getVilles().get(9);
        villeRetour = distance.getVilles().get(15);
        taillePopulation = 100;
        population = new Population(); 
        population.creerPopulation(distance, villeDepart, villeRetour, taillePopulation);
        sleep(500);
        population.verifPopulation();
        g = new Genetique();
        
        g.lancer();*/
        
        new UI(distance.getVilles());
    }
}
