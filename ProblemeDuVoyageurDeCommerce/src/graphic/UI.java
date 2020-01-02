package graphic;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author gabri
 */
public class UI extends JPanel{

    public UI(){

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

        //choix du fichier
        FileSystemView vueSysteme = FileSystemView.getFileSystemView();
        File defaut = vueSysteme.getDefaultDirectory();
        JFileChooser choixFichier = new JFileChooser(defaut);
        //initialisation panel choix de fichier
        JPanel panelChoixFichier = new JPanel();
        JButton boutonFichier = new JButton("Choix du fichier");
        JTextField textChoixFichier = new JTextField(20);
        textChoixFichier.setEditable(false);
        boutonFichier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choixFichier.showOpenDialog(null);
                String openFilePath = choixFichier.getSelectedFile().getAbsoluteFile().getAbsolutePath();
                textChoixFichier.setText(openFilePath);
            }
        });
        
        ArrayList<String> listVilles = new ArrayList<String>();
        Collections.sort(listVilles);
        String[] villes = new String[listVilles.size()];
        for(int i = 0; i < villes.length; i++){
            villes[i] = listVilles.get(i);
        }

        JPanel panelVillesDepart = new JPanel();
        JLabel labelVillesDepart = new JLabel("Ville de départ ");
        JComboBox choixVillesDepart = new JComboBox(villes);
        JPanel panelVillesRetour = new JPanel();
        JLabel labelVillesRetour = new JLabel("Ville de retour ");
        JComboBox choixVillesRetour = new JComboBox(villes);

        //parametre 1
        JPanel panelPara1 = new JPanel();
        panelPara1.setLayout(new FlowLayout());
        JLabel labelPara1 = new JLabel("Taille de la population. N : ");
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
    JLabel labelPara2 = new JLabel("Meilleurs individus. K : ");
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
    JLabel labelPara3 = new JLabel("Taux de mutation(%). M : ");
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
    JLabel labelPara4 = new JLabel("Temps de calcul(génération/seconde). T : ");
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


    JPanel panelStrategies = new JPanel ();
        //initialisation du panelStrategies
        JLabel labelStrategies = new JLabel ("Stratégies");
        panelStrategies.setLayout(new GridLayout(5,1));
        //comboBox des differentes strategies
        String[] stratSelection = new String[]{"Stratégie de Selection","Elitiste","Tournoi"};
        JComboBox choixStratSelection = new JComboBox(stratSelection);
        String[] stratRecombinaison = new String[]{"Stratégie de recombinaison","Simple","Enjambement"};
        JComboBox choixStratRecombinaison = new JComboBox(stratRecombinaison);
        String[] tempsCalcul = new String[]{"Temps de calcul","Par génération","Par seconde", "Illimité"};
        JComboBox choixTempsCalcul = new JComboBox(tempsCalcul);
        String[] stratRemplissage = new String[]{"Stratégie de Remplissage", "Nouveaux individus", "Enfants uniquement"};
        JComboBox choixRemplissage = new JComboBox(stratRemplissage);
    JButton boutonLancer = new JButton("Lancer");



    //initialisation panelCourbeFitness
    JPanel panelCourbeFitness = new JPanel();
    //panelCourbeFitness.setLayout(new FlowLayout());

    //initialisation des éléments dans panelCourbeFitness
    JLabel labelCourbeFitness = new JLabel("Courbe de Fitness");
            //courbe
    JButton boutonStop = new JButton("STOP");


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



    panelParametres.add(labelParametres);
        panelChoixFichier.add(boutonFichier);
        panelChoixFichier.add(textChoixFichier);
    panelParametres.add(panelChoixFichier);
        panelVillesDepart.add(labelVillesDepart);
        panelVillesDepart.add(choixVillesDepart);
        panelVillesRetour.add(labelVillesRetour);
        panelVillesRetour.add(choixVillesRetour);
    panelParametres.add(panelVillesDepart);
    panelParametres.add(panelVillesRetour);
        panelPara1.add(labelPara1);
        panelPara1.add(textPara1);
    panelParametres.add(panelPara1);
        panelPara2.add(labelPara2);
        panelPara2.add(textPara2);
    panelParametres.add(panelPara2);
        panelPara3.add(labelPara3);
        panelPara3.add(textPara3);
    panelParametres.add(panelPara3);
        panelPara4.add(labelPara4);
        panelPara4.add(textPara4);
    panelParametres.add(panelPara4);



    panelStrategies.add(labelStrategies);
    panelStrategies.add(choixStratSelection);
    panelStrategies.add(choixStratRecombinaison);
    panelStrategies.add(choixRemplissage);
    panelStrategies.add(choixTempsCalcul);


    panelCourbeFitness.add(labelCourbeFitness);
    panelCourbeFitness.add(boutonStop);


    //encapsulation des éléments de la frame principale
    framePricipale.setLayout(null);
    panelParametrage.setBounds(0, 0, 400, 961);
    panelCourbeFitness.setBounds(400, 0, 1394, 961);
    framePricipale.add(panelParametrage);
    framePricipale.add(panelCourbeFitness);

    framePricipale.setVisible(true);
    }
}