package Fichier;
import Ville.Matrice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

public class Lecture {
    
    public static Matrice creationMatrice() throws FileNotFoundException, IOException{
        //Ouverture du fichier json 
        FileInputStream f = new FileInputStream("Villes.json");
        String json = new String();
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            json += s.nextLine();
        }
        f.close();
        //Création d'un objet JSON à partir du String du fichier
        JSONObject o = new JSONObject(json);
        JSONArray a = o.names();
        //Création de la liste des villes dans l'ordre pour la matrice
        ArrayList<String> villes = new ArrayList();
        while (!a.isEmpty()){
            String key = a.getString(0);
            a.remove(0);
            villes.add(key);
        }
        //Création de la matrice des distances
        Integer[][] matrice = creerMatrice(villes.size());
        a = o.names();
        while (!a.isEmpty()){
            String key = a.getString(0);
            a.remove(0);
            JSONObject jo = o.getJSONObject(key);
            JSONObject jo2 = jo.optJSONObject("Distances");
            JSONArray a2 = jo2.names();
            while (!a2.isEmpty()){
                String key2 = a2.getString(0);
                a2.remove(0);
                int pos1 = villes.indexOf(key);
                int pos2 = villes.indexOf(key2);
                int dist = jo2.getInt(key2);
                ajoutDistance(matrice, pos1, pos2, dist);
            }
        }
        Matrice distance = new Matrice(matrice, villes);
        return distance;
    }
    
    public static Integer[][] creerMatrice(int taille){
        Integer[][] matrice = new Integer[taille][taille];
        for (int i = 0; i < taille; i++){
    //Diagonale à 0 car la distance entre une ville et elle même est de 0
            matrice[i][i] = 0;
        }
        return matrice;
    }
    
    public static void ajoutDistance(Integer mat[][], Integer i, Integer j, Integer dis){
    //Ajout de la distance entre deux villes, de façon sysmétrique
        mat[i][j] = dis;
        mat[j][i] = dis;
    }
}
