package fichier;
import java.io.File;
import ville.Matrice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.json.*;

/**
 *Classe pour lire et stocker toutes les informations contenues dans le fichier.
 *
 */
public class Lecture {

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Matrice creationMatrice() throws FileNotFoundException, IOException{

        //choix du fichier
        FileSystemView vueSysteme = FileSystemView.getFileSystemView();
        File defaut = vueSysteme.getDefaultDirectory();
        JFileChooser choixFichier = new JFileChooser(defaut);
        choixFichier.showOpenDialog(null);
        String openFilePath = choixFichier.getSelectedFile().getAbsoluteFile().getAbsolutePath();

        //Ouverture du fichier json
        try{
            FileInputStream f = new FileInputStream(openFilePath);
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
            ArrayList<ArrayList<Double>> matrice = creerMatrice(villes.size());
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
                    Double dist = jo2.getDouble(key2);
                    ajoutDistance(matrice, pos1, pos2, dist);
                }
            }
            Matrice distance = new Matrice(matrice, villes);
            return distance;
        }
        catch(IOException ex){
            JOptionPane MessageErreurFichier = new JOptionPane();
            MessageErreurFichier.showMessageDialog(null, "Message d'erreur", "Erreur à l'ouverture du fichier", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }

    /**
     *Méthode pour créer la matrice/le tableau des distances
     * @param taille Nombre de ville contenue dans le fichier.
     * @return Le tableau/matrice créé(e).
     */
    private static ArrayList<ArrayList<Double>> creerMatrice(int taille){
        ArrayList<ArrayList<Double>> matrice = new ArrayList<ArrayList<Double>>();
        ArrayList<Integer> listeCreation = new ArrayList<Integer>();
        for (int i = 0; i < taille; i++){
            listeCreation.add(-1);
        }
        for (int j = 0; j < taille; j++){
            matrice.add((ArrayList<Double>)listeCreation.clone());
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
    private static void ajoutDistance(ArrayList<ArrayList<Double>> mat, Integer i, Integer j, Double dis){
    //Ajout de la distance entre deux villes, de façon sysmétrique
        mat.get(i).set(j, dis);
        mat.get(j).set(i, dis);
    }
}
