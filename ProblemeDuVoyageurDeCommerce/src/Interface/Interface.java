/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
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
    //initialisation de la frame et des panels
    JFrame framePricipale = new JFrame("Problème du voyageur de commerce");
    JPanel panelParametrage = new JPanel();
    panelParametrage.setSize(400, 800);
    JLabel labelParametrage = new JLabel("Paramètres et Stratégies");
    JPanel panelParametres = new JPanel ();
    JLabel labelParametres = new JLabel ("Paramètres");
    JPanel panelStrategies = new JPanel ();
    JLabel labelStrategies = new JLabel ("Stratégies");
    JPanel panelLancement = new JPanel ();
    JPanel panelCourbeFitness = new JPanel();
    panelCourbeFitness.setSize(1200, 800);
    JLabel labelCourbeFitness = new JLabel("Courbe de Fitness");
    JButton boutonLancer = new JButton("Lancer");
    panelParametrage.setLayout(new BoxLayout(panelParametrage,BoxLayout.Y_AXIS));
    panelStrategies.setLayout(new BoxLayout(panelStrategies,BoxLayout.Y_AXIS));
    panelCourbeFitness.setLayout(new FlowLayout());
    //les différentes comboBox pour les choix de paramètres
    //String[] villes = recupeVilles();
    //JComboBox choixVilles = new JComboBox(villes);
    String[] stratSelection = new String[]{"Stratégie de Selection","Elitiste","Tournoi"};
    JComboBox choixStratSelection = new JComboBox(stratSelection);
    String[] stratRecombinaison = new String[]{"Stratégie de recombinaison","Simple","Enjambement"};
    JComboBox choixStratRecombinaison = new JComboBox(stratRecombinaison);
    String[] tempsCalcul = new String[]{"Temps de calcul","Par génération","Par seconde"};
    JComboBox choixTempsCalcul = new JComboBox(tempsCalcul);
    

    //colorisation des panel
    panelParametrage.setBackground(Color.BLUE);
    panelCourbeFitness.setBackground(Color.RED);
    panelParametres.setBackground(Color.CYAN);
    panelStrategies.setBackground(Color.GRAY);
    panelLancement.setBackground(Color.LIGHT_GRAY);
    
    
    
    panelParametres.add(labelParametres);
    
    panelStrategies.add(labelStrategies);
    panelStrategies.add(choixStratSelection);
    panelStrategies.add(choixStratRecombinaison);
    panelStrategies.add(choixTempsCalcul);
    
    panelLancement.add(boutonLancer);
    //panelParametrage.add(choixVilles);
    panelParametrage.add(labelParametrage);
    panelParametrage.add(panelParametres);
    panelParametrage.add(panelStrategies);
    panelParametrage.add(panelLancement);
    panelCourbeFitness.add(labelCourbeFitness);
    
    //taille des panels
    
    
    //encapsulation des des panels dans la frame principale
    framePricipale.setLayout(new BorderLayout());
    framePricipale.add(panelParametrage,BorderLayout.WEST);
    framePricipale.add(panelCourbeFitness,BorderLayout.CENTER);
    framePricipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePricipale.setSize(1600, 800);
    framePricipale.setVisible(true);
    }
}
