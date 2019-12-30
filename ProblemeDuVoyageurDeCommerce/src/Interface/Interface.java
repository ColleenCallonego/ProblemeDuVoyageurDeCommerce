/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author gabri
 */
public class Interface extends JPanel{
    
    public static void main(String[] args) {
        
    //initialisation de la framePrincipale
    JFrame framePricipale = new JFrame("Problème du voyageur de commerce");
    framePricipale.setResizable(false);
    framePricipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePricipale.setSize(1800, 1000);
    framePricipale.setLocationRelativeTo(null);
    
    //initialisation du panelParametrage
    JPanel panelParametrage = new JPanel();
    panelParametrage.setLayout(new GridLayout(4,1));
    
    //initialisation des éléments dans panelParametrage
    JLabel labelParametrage = new JLabel("Paramètres et Stratégies");
    JPanel panelParametres = new JPanel ();
        //initialisation du panelParametres
        JLabel labelParametres = new JLabel ("Paramètres");
        //String[] villes = recupeVilles();
        //JComboBox choixVillesDepart = new JComboBox(villes);
        //JComboBox choixVillesRetour = new JComboBox(villes);
        
    JPanel panelStrategies = new JPanel ();
        //initialisation du panelStrategies
        JLabel labelStrategies = new JLabel ("Stratégies");
        panelStrategies.setLayout(new GridLayout(4,1));
        //comboBox des differentes strategies
        String[] stratSelection = new String[]{"Stratégie de Selection","Elitiste","Tournoi"};
        JComboBox choixStratSelection = new JComboBox(stratSelection);
        String[] stratRecombinaison = new String[]{"Stratégie de recombinaison","Simple","Enjambement"};
        JComboBox choixStratRecombinaison = new JComboBox(stratRecombinaison);
        String[] tempsCalcul = new String[]{"Temps de calcul","Par génération","Par seconde"};
        JComboBox choixTempsCalcul = new JComboBox(tempsCalcul);
        
    JButton boutonLancer = new JButton("Lancer");
    
    
    
    //initialisation panelCourbeFitness
    JPanel panelCourbeFitness = new JPanel();
    //panelCourbeFitness.setLayout(new FlowLayout());
    
    //initialisation des éléments dans panelCourbeFitness
    JLabel labelCourbeFitness = new JLabel("Courbe de Fitness");
    
    

    //colorisation des panel
    panelParametrage.setBackground(Color.BLUE);
    panelCourbeFitness.setBackground(Color.RED);
    panelParametres.setBackground(Color.CYAN);
    panelStrategies.setBackground(Color.GRAY);
    
    //encapsulation
    panelParametrage.add(labelParametrage);
    panelParametrage.add(panelParametres);
    panelParametrage.add(panelStrategies);
    panelParametrage.add(boutonLancer);
    
    panelCourbeFitness.add(labelCourbeFitness);
    
    panelParametres.add(labelParametres);
    //panelParametres.add(choixVillesDepart);
    //panelParametres.add(choixVillesRetour);
    
    panelStrategies.add(labelStrategies);
    panelStrategies.add(choixStratSelection);
    panelStrategies.add(choixStratRecombinaison);
    panelStrategies.add(choixTempsCalcul);
    
    //encapsulation des éléments de la frame principale
    framePricipale.setLayout(null);
    panelParametrage.setBounds(0, 0, 400, 961);
    panelCourbeFitness.setBounds(400, 0, 1394, 961);
    framePricipale.add(panelParametrage);
    framePricipale.add(panelCourbeFitness);
    
    framePricipale.setVisible(true);
    }
}
