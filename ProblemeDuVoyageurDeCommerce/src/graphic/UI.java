package graphic;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import ville.Matrice;

/**
 *
 * @author gabri
 */
public class UI extends JPanel{
    
    public static Matrice distance;

    public UI(ArrayList<String> listVilles){
    //initialisation de la framePrincipale
    JFrame framePricipale = new JFrame("Problème du voyageur de commerce");
    framePricipale.setResizable(false);
    framePricipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePricipale.setSize(1800, 1000);
    framePricipale.setLocationRelativeTo(null);
    //initialisation du panelParametrage
    JPanel panelParametrage = new JPanel();
    //initialisation des éléments dans panelParametrage
    JLabel labelParametrage = new JLabel("Paramètres et Stratégies", SwingConstants.CENTER);
    labelParametrage.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    JPanel panelParametres = new JPanel ();
        //initialisation du panelParametres
        JLabel labelParametres = new JLabel ("Paramètres", SwingConstants.CENTER);
        //initialisation des choix de ville de départ et de retour
        Collections.sort(listVilles);
        String[] villes = new String[listVilles.size()];
        for(int i = 0; i < villes.length; i++){
            villes[i] = listVilles.get(i);
        }
        JPanel panelVillesDepart = new JPanel();
        JLabel labelVillesDepart = new JLabel("Ville de départ", SwingConstants.CENTER);
        JComboBox choixVillesDepart = new JComboBox(villes);
        JPanel panelVillesRetour = new JPanel();
        JLabel labelVillesRetour = new JLabel("Ville de retour", SwingConstants.CENTER);
        JComboBox choixVillesRetour = new JComboBox(villes);
        //parametre 1
        JPanel panelPara1 = new JPanel();
        panelPara1.setLayout(new FlowLayout());
        JLabel labelPara1 = new JLabel("Taille de la population. N :", SwingConstants.CENTER);
        JTextField textPara1 = new JTextField(4);
        textPara1.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() < KeyEvent.VK_NUMPAD0 || e.getKeyCode() > KeyEvent.VK_NUMPAD9){
                    if (!textPara1.getText().isEmpty()){
                        textPara1.setText(textPara1.getText().substring(0, textPara1.getText().length() - 1));
                    }
                }
            }
        });
        //parametre 2
        JPanel panelPara2 = new JPanel();
        panelPara2.setLayout(new FlowLayout());
        JLabel labelPara2 = new JLabel("Meilleurs individus. K :", SwingConstants.CENTER);
        JTextField textPara2 = new JTextField(4);
        textPara2.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() < KeyEvent.VK_NUMPAD0 || e.getKeyCode() > KeyEvent.VK_NUMPAD9){
                    if (!textPara2.getText().isEmpty()){
                        textPara2.setText(textPara2.getText().substring(0, textPara2.getText().length() - 1));
                    }
                }
            }
        });
        //parametre 3
        JPanel panelPara3 = new JPanel();
        panelPara3.setLayout(new FlowLayout());
        JLabel labelPara3 = new JLabel("Taux de mutation(%). M :", SwingConstants.CENTER);
        JTextField textPara3 = new JTextField(4);
        textPara3.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
            if ((e.getKeyCode() < KeyEvent.VK_NUMPAD0 || e.getKeyCode() > KeyEvent.VK_NUMPAD9) && e.getKeyCode() != KeyEvent.VK_DECIMAL){
                    if (!textPara3.getText().isEmpty()){
                        textPara3.setText(textPara3.getText().substring(0, textPara3.getText().length() - 1));
                    }
                }
            }
        });
        //parametre 4
        JPanel panelPara4 = new JPanel();
        panelPara4.setLayout(new FlowLayout());
        JLabel labelPara4 = new JLabel("Temps de calcul(génération/seconde). T :", SwingConstants.CENTER);
        JTextField textPara4 = new JTextField(4);
        textPara4.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() < KeyEvent.VK_NUMPAD0 || e.getKeyCode() > KeyEvent.VK_NUMPAD9){
                    if (!textPara4.getText().isEmpty()){
                        textPara4.setText(textPara4.getText().substring(0, textPara4.getText().length() - 1));
                    }
                }
            }
        });
    //initialisation du panelStrategies
    JPanel panelStrategies = new JPanel ();
        JLabel labelStrategies = new JLabel ("Stratégies", SwingConstants.CENTER);
        //comboBox des differentes strategies
        String[] stratSelection = new String[]{"Stratégie de Selection","Elitiste","Tournoi"};
        JComboBox choixStratSelection = new JComboBox(stratSelection);
        String[] stratRecombinaison = new String[]{"Stratégie de recombinaison","Simple","Enjambement"};
        JComboBox choixStratRecombinaison = new JComboBox(stratRecombinaison);
        String[] tempsCalcul = new String[]{"Temps de calcul","Par génération","Par seconde", "Illimité"};
        JComboBox choixTempsCalcul = new JComboBox(tempsCalcul);
        String[] stratRemplissage = new String[]{"Stratégie de Remplissage", "Nouveaux individus", "Enfants uniquement"};
        JComboBox choixRemplissage = new JComboBox(stratRemplissage);
    JButton boutonLancer = new JButton("LANCER");
    JButton boutonStop = new JButton("STOP");
    //initialisation panelCourbeFitness
    JPanel panelCourbeFitness = new JPanel();
    //panelCourbeFitness.setLayout(new FlowLayout());
    //initialisation des éléments dans panelCourbeFitness
    JLabel labelCourbeFitness = new JLabel("Courbe de Fitness", SwingConstants.CENTER);
            //courbe
    


    //colorisation des panel
    Color C1 = new Color(187, 188, 191);
    Color C2 = new Color(225, 226, 230);
    panelParametrage.setBackground(C1);
    panelParametres.setBackground(C1);
    panelVillesDepart.setBackground(C1);
    panelVillesRetour.setBackground(C1);
    panelPara1.setBackground(C1);
    panelPara2.setBackground(C1);
    panelPara3.setBackground(C1);
    panelPara4.setBackground(C1);
    panelStrategies.setBackground(C1);
    panelCourbeFitness.setBackground(C2);
    //encapsulation
    //Parametrage
    panelParametrage.setLayout(null);
    labelParametrage.setBounds(100, 30, 200, 25);
    panelParametrage.add(labelParametrage);
    panelParametres.setBounds(20, 70, 360, 300);
    panelParametrage.add(panelParametres);
    panelStrategies.setBounds(20, 400, 360, 300);
    panelParametrage.add(panelStrategies);
    boutonLancer.setBounds(160, 750, 80, 40);
    panelParametrage.add(boutonLancer);
    boutonStop.setBounds(160, 850, 80, 40);
    panelParametrage.add(boutonStop);
    //Parametres
    panelParametres.setLayout(null);
    labelParametres.setBounds(10, 15, 340, 30);
    panelParametres.add(labelParametres);
        panelVillesDepart.add(labelVillesDepart);
        panelVillesDepart.add(choixVillesDepart);
        panelVillesRetour.add(labelVillesRetour);
        panelVillesRetour.add(choixVillesRetour);
    panelVillesDepart.setBounds(10, 60, 340, 30);
    panelParametres.add(panelVillesDepart);
    panelVillesRetour.setBounds(10, 100, 340, 30);
    panelParametres.add(panelVillesRetour);
        panelPara1.add(labelPara1);
        panelPara1.add(textPara1);
    panelPara1.setBounds(10, 140, 340, 30);
    panelParametres.add(panelPara1);
        panelPara2.add(labelPara2);
        panelPara2.add(textPara2);
    panelPara2.setBounds(10, 180, 340, 30);
    panelParametres.add(panelPara2);
        panelPara3.add(labelPara3);
        panelPara3.add(textPara3);
    panelPara3.setBounds(10, 220, 340, 30);
    panelParametres.add(panelPara3);
        panelPara4.add(labelPara4);
        panelPara4.add(textPara4);
    panelPara4.setBounds(10, 260, 340, 30);
    panelParametres.add(panelPara4);
    //Strategies
    panelStrategies.setLayout(new GridLayout(5,1));
    panelStrategies.add(labelStrategies);
    panelStrategies.add(choixStratSelection);
    panelStrategies.add(choixStratRecombinaison);
    panelStrategies.add(choixRemplissage);
    panelStrategies.add(choixTempsCalcul);
    //CourbeFitness
    panelCourbeFitness.add(labelCourbeFitness);
    //encapsulation des éléments de la frame principale
    framePricipale.setLayout(null);
    panelParametrage.setBounds(0, 0, 400, 971);
    panelCourbeFitness.setBounds(400, 0, 1394, 971);
    framePricipale.add(panelParametrage);
    framePricipale.add(panelCourbeFitness);
    framePricipale.setVisible(true);
    }
}