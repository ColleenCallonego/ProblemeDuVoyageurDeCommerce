package main;

import fichier.Lecture;
import graphic.Interface;
import ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static Matrice distance;
    public static String villeDepart;
    public static String villeRetour;
    public static Integer taillePopulation;
    public static ArrayList[] population;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Matrice distance = Lecture.creationMatrice();
        Lecture.villeDepart = distance.villes.get(0);
        Lecture.villeRetour = distance.villes.get(distance.villes.size()-1);
        Lecture.taillePopulation = 1000;
        new Interface();
    }

}
