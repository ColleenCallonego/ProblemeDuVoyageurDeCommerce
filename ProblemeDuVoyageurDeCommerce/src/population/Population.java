package population;

import java.util.ArrayList;
import java.util.Collections;
import static main.Main.KMeilleursParents;
import static main.Main.distance;
import static main.Main.taillePopulation;
import static main.Main.tauxMutation;
import static main.Main.villeDepart;
import static main.Main.villeRetour;
import ville.Chemin;

/**
 *Classe pour la population d'individu.
 */
public class Population {
    private ArrayList<Individu> population;

    public Population(){
        //NE SERT A RIEN JUSTE POUR QUE JE PUISSE METTRE EN PLACE LES AUTRES METHODES
        //A ENLEVER UNE FOIS QU'ON SAIT COMMENT CREER LA POPULATION.
    }

    /**
     *Constructeur d'une population.
     * @param population ArrayList d'Individu.
     */
    public Population(ArrayList<Individu> population) {
        this.population = population;
    }

    /**
     *Getteur de l'ArrayList d'Individu de la population.
     * @return
     */
    public ArrayList<Individu> getPopulation() {
        return population;
    }

    public void triePopulation(){//Méthode à appeler à la fin de la création d'une nouvelle population
        Collections.sort(population);
    }

    /**
     *Méthode pour sélectionner les K meilleurs individus de la population.
     * @param k Nombre d'individu à selectionner.
     * @return ArrayList des K meilleurs individus.
     */
    public ArrayList<Individu> selectionKMeilleur(int k){
	ArrayList<Individu> KMeilleur = new ArrayList<Individu>();
	for(int i = 0; i < k; i++){
		KMeilleur.add(population.get(i));
	}
	return KMeilleur;
    }

    public void selectionTournoi(){
        //A FAIRE, PAS ENCORE PENSER
    }

    /**
     *Méthode pour créer tous les enfants possibles de la population.
     * @return ArrayList d'Individu qui correspond à tous les enfants créés.
     */
    public ArrayList<Individu> creationEnfants(){
	ArrayList<Individu> enfants = new ArrayList<Individu>();
	for (int i = 0; i < taillePopulation; i++){
            for (int j = i + 1; j < taillePopulation; j++){
                recombinaisonSimple(population.get(i), population.get(j), enfants);
            }
	}
        return enfants;
    }

    /**
     *Méthode pour recombiner deux individus entre eux.
     * On les recombine en les coupant en deux.
     * @param indi1 Premier "parent".
     * @param indi2 Deuxième "parent"
     * @param enfants ArrayList d'individu qui va contenir les enfants.
     */
    public void recombinaisonSimple(Individu indi1, Individu indi2, ArrayList<Individu> enfants){
	int milieu = indi1.getPath().getVilles().size()/2; //endroit où il faut couper les individus pour les recombiner
	ArrayList<String> indi1part1 = new ArrayList<String>();
        indi1part1 = partieArrayList(0, milieu, indi1.getPath().getVilles());
	ArrayList<String> indi1part2 = new ArrayList<String>();
        indi1part2 = partieArrayList(milieu, indi1.getPath().getVilles().size(), indi1.getPath().getVilles());
	ArrayList<String> indi2part1 = new ArrayList<String>();
	indi2part1 = partieArrayList(0, milieu, indi2.getPath().getVilles());
	ArrayList<String> indi2part2 = new ArrayList<String> ();
	indi2part2 = partieArrayList(milieu, indi2.getPath().getVilles().size(), indi2.getPath().getVilles());
	ArrayList<String> villesEnfant1 = new ArrayList<String>();
	villesEnfant1 = indi1part1;
	villesEnfant1.addAll(indi2part2);
        Individu enfant1 = new Individu(new Chemin(villesEnfant1, Individu.calculFitness(villesEnfant1)), Individu.calculFitness(villesEnfant1));
	ArrayList<String> villesEnfant2 = new ArrayList<String>();
	villesEnfant2 = indi2part1;
	villesEnfant2.addAll(indi1part2);
        Individu enfant2 = new Individu(new Chemin(villesEnfant2, Individu.calculFitness(villesEnfant2)), Individu.calculFitness(villesEnfant2));
            if (Math.random() <= tauxMutation){ //PAS BONNE CONDITON, AIDEZ MOI
                enfant1.mutation();
            }
            enfants.add(enfant1);
            if (Math.random() <= tauxMutation){ //PAS BONNE CONDITON, AIDEZ MOI
               enfant2.mutation();
            }
            enfants.add(enfant2);
    }

    public ArrayList<String> partieArrayList(Integer pos1, Integer pos2, ArrayList<String> a){
        ArrayList<String> partie = new ArrayList<String>();
        for (int i = pos1; i < pos2; i++){
            partie.add(a.get(i));
        }
        return partie;
    }

    /**
     *Méthode pour remplacer toute la population par des enfants des individus.
     * Et si il n'y a pas assez d'enfants, on complète par des nouveaux individus.
     * @param enfants ArrayList de tous les enfants de la population à remplacer.
     */
    public void remplacementTotal(ArrayList<Individu> enfants){
	if (enfants.size() > population.size()){
                Population e = new Population(enfants);
		population = e.selectionKMeilleur(taillePopulation);
	}
	else {
            population.clear();
            population.addAll(enfants);
            for(int i = enfants.size(); i < taillePopulation; i++){
                String VilleRetour;
                population.add(new Individu(villeDepart, villeRetour, distance));
            }
	}
        this.triePopulation();
    }

    /**
     *Méthode pour remplacer partiellement la population.
     * Et si il n'y a pas assez d'enfants, on complète par des nouveaux individus.
     * @param enfants ArrayList de tous les enfants de la population à remplacer.
     */
    public void remplacementPartiel(ArrayList<Individu> enfants){
	population.clear();
	population.addAll(KMeilleursParents); //faire une variable globale KMeilleurParents
	int NbManquant = taillePopulation - KMeilleursParents.size();
	if (enfants.size() > NbManquant){
                Population e = new Population(enfants);
		population.addAll(e.selectionKMeilleur(NbManquant));
	}
	else {
		population.addAll(enfants);
		NbManquant = taillePopulation - population.size();
		for(int i = 0; i < NbManquant; i++){
			population.add(new Individu(villeDepart, villeRetour, distance));
		}
	}
        this.triePopulation();
    }

    /**
     *Méthode pour afficher le meilleur individu.
     * @return Le meilleur individu de la population.
     */
    public Individu meilleur(){
	return population.get(0);
    }
}
