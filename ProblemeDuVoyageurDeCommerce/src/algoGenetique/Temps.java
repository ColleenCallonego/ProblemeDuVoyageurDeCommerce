package algoGenetique;

import java.util.Timer;
import java.util.TimerTask;
import static main.Main.g;


public class Temps extends Thread{
    Timer minuteur;
    Tache t;
    long secondes;

    public Temps (int secondes){
        minuteur = new Timer();
        t = new Tache();
        this.secondes = secondes;
    }

    public void run(){
        minuteur.schedule(t,0, secondes*1000);
        minuteur.cancel();
    }
}
