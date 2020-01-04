package algoGenetique;

import java.util.TimerTask;
import static main.Main.g;


class Tache extends TimerTask{
    public void run(){
        System.out.println("Coucou2");
        g.setB(false);
    }
}
