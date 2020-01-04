package main;

import fichier.Lecture;
import graphic.UI;
import ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static Matrice distance;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        distance = Lecture.creationMatrice();
        new UI(distance.getVilles());
    }
}
