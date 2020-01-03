package ville;
import java.util.ArrayList;

public class Chemin {
  private ArrayList<String> villes;
  private Double length;

  public Chemin(ArrayList<String> villes, Double length) {
    this.villes = villes;
    this.length = length;
  }

  public ArrayList<String> getVilles(){
    return villes;
  }

  public Double getLength() {
    return length;
  }

    public void setVilles(ArrayList<String> villes) {
        this.villes = villes;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Chemin{" + "villes=" + villes + '}';
    }
  
}
