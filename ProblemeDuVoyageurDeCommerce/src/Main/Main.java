package Main;

import Fichier.Lecture;
import Interface.Interface;
import Ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Matrice distance;
    public static String villeDepart;
    public static String villeRetour;
    public static Integer taillePopulation;
    public static ArrayList[] population;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Matrice distance = Lecture.creationMatrice();
        villeDepart = distance.villes.get(0);
        villeRetour = distance.villes.get(distance.villes.size()-1);
        taillePopulation = 1000;
        new Interface(distance.villes);
    }
    
}
