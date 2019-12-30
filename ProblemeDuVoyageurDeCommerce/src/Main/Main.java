package Main;

import Fichier.Lecture;
import Ville.Matrice;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static Matrice distance;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Matrice distance = Lecture.creationMatrice();
    }
    
}
