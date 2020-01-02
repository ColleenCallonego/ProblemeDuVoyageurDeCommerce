package fichier;
import ville.Matrice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

/**
 *Classe pour lire et stocker toutes les informations contenues dans le fichier.
 *
 */
public class Lecture {
    public static Matrice distance;

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
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
        ArrayList<String> villes = new ArrayList<String>();
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

    /**
     *Méthode pour créer la matrice/le tableau des distances
     * @param taille Nombre de ville contenue dans le fichier.
     * @return Le tableau/matrice créé(e).
     */
    private static Integer[][] creerMatrice(int taille){
        Integer[][] matrice = new Integer[taille][taille];
        for (int i = 0; i < taille; i++){
    //Diagonale à 0 car la distance entre une ville et elle même est de 0
            matrice[i][i] = 0;
        }
        return matrice;
    }

    /**
     *Méthode pour ajouter une distance entre deux villes dans
     * le tableau/matrice.
     * @param mat Tableau/matrice où il faut ajouter une distance.
     * @param i Position de la première ville.
     * @param j Position de la seconde ville.
     * @param dis Distance entre les deux villes.
     */
    private static void ajoutDistance(Integer mat[][], Integer i, Integer j, Integer dis){
    //Ajout de la distance entre deux villes, de façon sysmétrique
        mat[i][j] = dis;
        mat[j][i] = dis;
    }
}
