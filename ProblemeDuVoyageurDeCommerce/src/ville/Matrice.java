package ville;
import java.util.ArrayList;

/**
 *Classe qui correspond à toutes les informations nécéssaires à propos
 * des distances et des villes.
 * On peut y trouver une ArrayList de String et un tableau/matrice en deux
 * dimensions.
 * On s'aide de l'ArrayList pour savoir à quelle ville correpond un indice dans
 * le tableau.
 * L'indice d'une ville dans l'ArrayList correspond donc à l'indice de cette
 * même ville dans le tableau/matrice.
 */
public class Matrice {
    private ArrayList<String> villes; //ArrayList des villes à visiter
    private ArrayList<ArrayList<Integer>> matrice; //Tableau/matrice des distances entre toutes les villes

    /**
     *Constructeur de la classe Matrice.
     * @param matrice Tableau/matrice créé(e) où l'on retrouve toutes les distances.
     * @param villes ArrayList de toutes les villes.
     */
    public Matrice(ArrayList<ArrayList<Integer>> matrice, ArrayList<String> villes) {
        this.villes = villes;
        this.matrice = matrice;
    }

    public ArrayList<ArrayList<Integer>> getMatrice() {
        return matrice;
    }

    public ArrayList<String> getVilles() {
        return villes;
    }
}
