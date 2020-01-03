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
import ville.Chemin;

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
        taillePopulation = 3;
        k = 2;
        villeDepart = "Nancy";
        villeRetour = "Lille";
        Matrice distance = Lecture.creationMatrice();
        ArrayList<String> a = new ArrayList<String>();
        a.add("Nancy");
        a.add("Paris");
        a.add("Brest");
        a.add("Lille");
        ArrayList<String> a2 = new ArrayList<String>();
        a2.add("Nancy");
        a2.add("Marseille");
        a2.add("Le Havre");
        a2.add("Lille");
        ArrayList<String> a3 = new ArrayList<String>();
        a3.add("Nancy");
        a3.add("Strasbourg");
        a3.add("Lyon");
        a3.add("Lille");
        Individu i1 = new Individu(new Chemin(a, 59.0), 59.0);
        Individu i2 = new Individu(new Chemin(a2, 35.0), 35.0);
        Individu i3 = new Individu(new Chemin(a3, 100.0), 100.0);
        ArrayList<Individu> pop = new ArrayList<Individu>();
        pop.add(i1);
        pop.add(i2);
        pop.add(i3);
        Population p = new Population(pop);
        p.triePopulation();
        System.out.println(p.getPopulation());
        KMeilleursParents = p.selectionKMeilleur(2);
        System.out.println(p.creationEnfants());
        p.remplacementPartiel(p.creationEnfants());
        System.out.println(p.getPopulation());
        /*villeDepart = distance.getVilles().get(0);
        villeRetour = distance.getVilles().get(distance.getVilles().size()-1);
        taillePopulation = 1000;
        population = new Population();
        Genetique g = new Genetique();
        g.lancer();*/
        //new UI(distance.getVilles());
    }

}
