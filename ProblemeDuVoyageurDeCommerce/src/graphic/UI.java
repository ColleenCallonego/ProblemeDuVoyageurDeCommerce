package graphic;

import algoGenetique.Genetique;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
/**
 *
 */
public class UI extends JPanel{
    
    public static String villeDepart;
    public static String villeRetour;
    public static Integer taillePopulation;
    public static Integer k;
    public static Integer nbITournoi;
    public static Double tauxMutation;
    public static Integer nbGenerationSeconde;
    public static String stratSelection;
    public static String stratRecombinaison;
    public static String stratRemplissage;
    public static String tempsCalcul;
    public static Genetique g;
    public static XYPlot plot; 
    public static JTextField solutionChemin;
    public static JTextField solutionFitness;

    /**
     * Création de l'interface graphique de notre programme. Une fenêtre où se situent deux panel le premier pour rentrer les différents paramètres de notre problème, le deuxième pour afficher la courbe et le résultat de chaque génération en temps réel.
     * 
     * @param listVilles list des villes contenues dans le fichier. Permet de remplir les comboBox pour le choix de la ville de départ et de retour
     */
    public UI(ArrayList<String> listVilles) throws InterruptedException{
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
        //parametre 5
        JPanel panelPara5 = new JPanel();
        panelPara5.setLayout(new FlowLayout());
        JLabel labelPara5 = new JLabel("Nombre d'individu par tournoi. nbT :", SwingConstants.CENTER);
        JTextField textPara5 = new JTextField(4);
        textPara5.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() < KeyEvent.VK_NUMPAD0 || e.getKeyCode() > KeyEvent.VK_NUMPAD9){
                    if (!textPara5.getText().isEmpty()){
                        textPara5.setText(textPara5.getText().substring(0, textPara5.getText().length() - 1));
                    }
                }
            }
        });
    //initialisation du panelStrategies
    JPanel panelStrategies = new JPanel ();
        JLabel labelStrategies = new JLabel ("Stratégies", SwingConstants.CENTER);
        //comboBox des differentes strategies
        String[] listStratSelection = new String[]{"Stratégie de Selection","Elitiste","Tournoi"};
        JComboBox choixStratSelection = new JComboBox(listStratSelection);
        String[] listStratRecombinaison = new String[]{"Stratégie de recombinaison","Recombinaison simple"};
        JComboBox choixStratRecombinaison = new JComboBox(listStratRecombinaison);
        String[] listTempsCalcul = new String[]{"Temps de calcul","Par génération", "Par seconde", "Illimité"};
        JComboBox choixTempsCalcul = new JComboBox(listTempsCalcul);
        String[] listStratRemplissage = new String[]{"Stratégie de Remplissage", "Parents et enfants", "Enfants uniquement"};
        JComboBox choixRemplissage = new JComboBox(listStratRemplissage);
    JButton boutonLancer = new JButton("LANCER");
    JButton boutonStop = new JButton("STOP");
    //initialisation panelCourbeFitness
    JPanel panelCourbeFitness = new JPanel();
    //initialisation des éléments dans panelCourbeFitness
    JLabel labelCourbeFitness = new JLabel("Courbe de Fitness", SwingConstants.CENTER);
        labelCourbeFitness.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //preparation de la courbe
        NumberAxis domain = new NumberAxis("Générations");
     	NumberAxis range = new NumberAxis("Fitness en Km");
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot = new XYPlot(null, domain, range, renderer);
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(new TextTitle("Evolution de la Fitness"));
        ChartPanel panelCourbe = new ChartPanel(chart);
        //panelCourbe.setVisible(false);
        //plot.setDataset(dataset);
        
        solutionChemin = new JTextField();
        solutionChemin.setEditable(false);
        solutionChemin.setHorizontalAlignment(JTextField.CENTER);
        solutionFitness = new JTextField();
        solutionFitness.setEditable(false);
        solutionFitness.setHorizontalAlignment(JTextField.CENTER);
    


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
    panelPara5.setBackground(C1);
    panelStrategies.setBackground(C1);
    panelCourbeFitness.setBackground(C2);
    
    //encapsulation
    //Parametrage
    panelParametrage.setLayout(null);
    labelParametrage.setBounds(100, 30, 200, 25);
    panelParametrage.add(labelParametrage);
    panelParametres.setBounds(20, 70, 360, 340);
    panelParametrage.add(panelParametres);
    panelStrategies.setBounds(20, 440, 360, 300);
    panelParametrage.add(panelStrategies);
    boutonLancer.setBounds(160, 790, 80, 40);
    panelParametrage.add(boutonLancer);
    boutonStop.setBounds(160, 890, 80, 40);
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
    panelPara5.add(labelPara5);
        panelPara5.add(textPara5);
    panelPara5.setBounds(10, 300, 340, 30);
    panelParametres.add(panelPara5);
    //Strategies
    panelStrategies.setLayout(new GridLayout(5,1));
    panelStrategies.add(labelStrategies);
    panelStrategies.add(choixStratSelection);
    panelStrategies.add(choixStratRecombinaison);
    panelStrategies.add(choixRemplissage);
    panelStrategies.add(choixTempsCalcul);
    //CourbeFitness
    panelCourbeFitness.setLayout(null);
    labelCourbeFitness.setBounds(597, 30, 200, 25);
    panelCourbeFitness.add(labelCourbeFitness);
    panelCourbe.setBounds(97, 90, 1200, 600);
    panelCourbeFitness.add(panelCourbe);
    solutionChemin.setBounds(97, 730, 1200, 30);
    panelCourbeFitness.add(solutionChemin);
    solutionFitness.setBounds(547, 800, 300, 30);
    panelCourbeFitness.add(solutionFitness);
    
    //encapsulation des éléments de la frame principale
    framePricipale.setLayout(null);
    panelParametrage.setBounds(0, 0, 400, 971);
    panelCourbeFitness.setBounds(400, 0, 1394, 971);
    framePricipale.add(panelParametrage);
    framePricipale.add(panelCourbeFitness);
    
    //Action du bouton LANCER. Récupère les parametres et les stratégies puis lance l'algo génétique
    boutonLancer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //recuperation des parametres
            villeDepart = (String) choixVillesDepart.getSelectedItem();
            villeRetour = (String) choixVillesRetour.getSelectedItem();
            if (!textPara1.getText().isEmpty()){
                taillePopulation = Integer.parseInt(textPara1.getText());
            }
            if (!textPara2.getText().isEmpty()){
                k = Integer.parseInt(textPara2.getText());
            }
            if (!textPara3.getText().isEmpty()){
                tauxMutation = Double.parseDouble(textPara3.getText());
            }
            if (!textPara4.getText().isEmpty()){
                nbGenerationSeconde = Integer.parseInt(textPara4.getText());
            }
            if (!textPara5.getText().isEmpty()){
                nbITournoi = Integer.parseInt(textPara5.getText());
            }
            stratSelection = (String) choixStratSelection.getSelectedItem();
            stratRecombinaison = (String) choixStratRecombinaison.getSelectedItem();
            stratRemplissage = (String) choixRemplissage.getSelectedItem();
            tempsCalcul = (String) choixTempsCalcul.getSelectedItem();
            
            //Vérification avant lancement
            if (taillePopulation!=null && k!=null && tauxMutation!=null && nbGenerationSeconde!=null && stratSelection!="Stratégie de Selection" && stratRecombinaison!="Stratégie de recombinaison" && stratRemplissage!="Stratégie de Remplissage" && tempsCalcul!="Temps de calcul" && nbITournoi!=null && (nbITournoi*k <= taillePopulation)){
                try {
                    g = new Genetique();
                    g.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                //message d'erreur sinon
                JOptionPane messageErreur = new JOptionPane();
                messageErreur.showMessageDialog(null, "Une erreur est survenue. Toutes les paramètres et les stratégies n'ont pas été correctement entrés", "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    //Action du bouton STOP. Arrête l'algo si en mode "Illimité"
    boutonStop.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tempsCalcul=="Illimité"){
                g.setB(false);
            }
        }
    });

    framePricipale.setVisible(true);
    }
}