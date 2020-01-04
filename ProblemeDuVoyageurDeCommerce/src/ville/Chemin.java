package ville;
import java.util.ArrayList;

public class Chemin {
  private ArrayList<String> villes;
  private Double length;

  /**
   * Constructor par défaut d'un chemin
   * @param villes Les villes passées dans le chemin
   * @param length La taille du chemin.
   */
  public Chemin(ArrayList<String> villes, Double length) {
    this.villes = villes;
    this.length = length;
  }

  /**
   * Getter de villes
   * @return Les villes
   */
  public ArrayList<String> getVilles(){
    return villes;
  }

  /**
   * Getter de length
   * @return la length
   */
  public Double getLength() {
    return length;
  }

    /**
     * setter de villes
     * @param villes des villes
     */
    public void setVilles(ArrayList<String> villes) {
        this.villes = villes;
    }

    /**
     * Setter de length
     * @param length Une length
     */
    public void setLength(Double length) {
        this.length = length;
    }

    /**
     * Description de la class Chemin
     * @return une description du chemin
     */
    @Override
    public String toString() {
        return "Chemin{" + "villes=" + villes + '}';
    }
}
