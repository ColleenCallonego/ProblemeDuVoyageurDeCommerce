package Ville;
import java.util.ArrayList;

public class Matrice {
    public ArrayList<String> villes;
    public Integer[][] matrice;

    public Matrice(Integer[][] matrice, ArrayList<String> villes) {
        this.villes = villes;
        this.matrice = matrice;
    }
}
