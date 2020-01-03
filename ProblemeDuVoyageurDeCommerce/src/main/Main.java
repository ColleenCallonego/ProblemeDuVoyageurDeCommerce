package main;

import algoGenetique.Genetique;
import fichier.Lecture;
import graphic.UI;
import ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import population.Individu;
import population.Population;

public class Main {
    public static Matrice distance;
    public static String villeDepart;
    public static String villeRetour;
    public static Integer taillePopulation;
    public static Population population;
    public static Double tauxMutation;
    public static Integer k;
    public static ArrayList<Individu> KMeilleursParents;
    public static Integer nbGeneration;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Matrice distance = Lecture.creationMatrice();
        /*villeDepart = distance.getVilles().get(0);
        villeRetour = distance.getVilles().get(distance.getVilles().size()-1);
        taillePopulation = 1000;
        population = new Population();
        Genetique g = new Genetique();
        g.lancer();*/
        new UI(distance.getVilles());
    }

}
