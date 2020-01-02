package population;

import java.util.ArrayList;
import java.util.Random;
import static main.Main.KMeilleursParents;
import static main.Main.distance;
import static main.Main.taillePopulation;
import static main.Main.tauxMutation;
import static main.Main.villeDepart;
import static main.Main.villeRetour;

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
	//voir avec GabyChou pour utiliser le .sort
    }
    
    /**
     *Méthode pour sélectionner les K meilleurs individus de la population.
     * @param k Nombre d'individu à selectionner.
     * @return ArrayList des K meilleurs individus.
     */
    public ArrayList selectionKMeilleur(int k){
	ArrayList KMeilleur = new ArrayList();
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
    public ArrayList creationEnfants(){
	ArrayList enfants = new ArrayList();
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
	int milieu = (int)indi1.getCities().size()/2; //endroit où il faut couper les individus pour les recombiner
	ArrayList indi1part1 = new ArrayList();
	indi1part1 = (ArrayList<String>)indi1.getCities().subList(0, milieu + 1);
	ArrayList indi1part2 = new ArrayList();
	indi1part2 = (ArrayList<String>) indi1.getCities().subList(milieu + 1, indi1.getCities().size());
	ArrayList indi2part1 = new ArrayList();
	indi2part1 = (ArrayList<String>) indi2.getCities().subList(0, milieu + 1);
	ArrayList indi2part2 = new ArrayList();
	indi2part2 = (ArrayList<String>) indi2.getCities().subList(milieu + 1, indi2.getCities().size());
	ArrayList villesEnfant1 = new ArrayList();
	villesEnfant1 = indi1part1;
	villesEnfant1.addAll(indi2part2);
        Individu enfant1 = new Individu(villesEnfant1, Individu.calculFitness(villesEnfant1));
	ArrayList villesEnfant2 = new ArrayList();
	villesEnfant2 = indi2part1;
	villesEnfant2.addAll(indi1part2);
        Individu enfant2 = new Individu(villesEnfant2, Individu.calculFitness(villesEnfant2));
	if (enfant1.verificationChemin()){
            if (false){//PAS BONNE CONDITION, AIDEZ MOI POUR FAIRE LA PROBA DE MUTATION
                enfant1.mutation();
            }
		enfants.add(enfant1);
	}
	if (enfant2.verificationChemin()){
            if (false){//PAS BONNE CONDITION, AIDEZ MOI POUR FAIRE LA PROBA DE MUTATION
                enfant2.mutation();
            }
		enfants.add(enfant2);
	}
    }
    
    /**
     *Méthode pour remplacer toute la population par des enfants des individus.
     * Et si il n'y a pas assez d'enfants, on complète par des nouveaux individus.
     * @param enfants ArrayList de tous les enfants de la population à remplacer.
     */
    public void remplacementTotal(ArrayList enfants){ 
	if (enfants.size() > population.size()){
		population = selectionKMeilleur(taillePopulation);
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
    public void remplacementPartiel(ArrayList enfants){
	population.clear();
	population.addAll(KMeilleursParents); //faire une variable globale KMeilleurParents
	int NbManquant = taillePopulation - KMeilleursParents.size();
	if (enfants.size() > NbManquant){
		population.addAll(selectionKMeilleur(NbManquant));
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
