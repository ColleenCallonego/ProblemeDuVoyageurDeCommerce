package population;

import static algoGenetique.Genetique.KMeilleursParents;
import static graphic.UI.k;
import static graphic.UI.nbITournoi;
import static graphic.UI.stratRecombinaison;
import static graphic.UI.taillePopulation;
import static graphic.UI.tauxMutation;
import static graphic.UI.villeDepart;
import static graphic.UI.villeRetour;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static main.Main.distance;
import ville.*;

/**
 *Classe pour la population d'individu.
 */
public class Population {
    private ArrayList<Individu> population;

    public Population(){
        population = new ArrayList<Individu>();
    }

    /**
     *Méthode pour créer une population.
     * @param matrice Matrice des distances entre villes.
     * @param begin Ville de départ.
     * @param end Ville de retour.
     * @param size Taille de la population.
     */
    public void creerPopulation(Matrice matrice, String begin, String end, int size) {
        for(int i=0; i<4; i++) {
            if (i != 3){
                Builder builder = new Builder(matrice,begin,end,size/4);
                builder.start();
            }
            else{
                Builder builder = new Builder(matrice,begin,end,((size/4) + (size%4)));
                builder.start();
            }
        }
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

    /**
     *Setteur de Population.
     * @param population
     */
    public void setPopulation(ArrayList<Individu> population) {
        this.population = population;
    }

    /**
     *Méthode pour vérifier qu'il y a le bon nombre d'Individu dans la population.
     */
    public void verifPopulation(){
        if (population.size() > taillePopulation){
            Integer trop = population.size() - taillePopulation;
            for(int i = 0; i < trop; i++){
                population.remove(0);
            }
        }
        this.triePopulation();
    }
    
    /**
     *Méthode pour trier une population en fonction par rapport à la fitness.
     * Par ordre croissant. A appeller chaque fois qu'on modifie la population.
     * 
     */
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

    /**
     *Méthode de sélection par tournoi.
     * @return L'ArrayList des Individus qui ont gagnés les tournois.
     */
    public ArrayList<Individu> selectionTournoi(){
        ArrayList<Individu> copiePop = (ArrayList<Individu>)population.clone();
        ArrayList<Individu> kMeilleur = new ArrayList<Individu>();
        ArrayList<Individu> tournoi = new ArrayList<Individu>();
        Population t = new Population();
        Random r = new Random();
        Integer pos;
        for (int i = 0; i < k; i++){
            tournoi.clear();
            t.getPopulation().clear();
            for (int j = 0; j < nbITournoi; j++){
                pos = r.nextInt(copiePop.size());
                tournoi.add(copiePop.get(pos));
                copiePop.remove(pos);
            }
            t = new Population(tournoi);
            t.triePopulation();
            kMeilleur.addAll(t.selectionKMeilleur(1));
        }
        return kMeilleur;
    }

    /**
     *Méthode pour créer tous les enfants possibles de la population.
     * @return ArrayList d'Individu qui correspond à tous les enfants créés.
     */
    public ArrayList<Individu> creationEnfants(){
	ArrayList<Individu> enfants = new ArrayList<Individu>();
	for (int i = 0; i < population.size(); i++){
            for (int j = i + 1; j < population.size(); j++){
                if (stratRecombinaison.equals("Recombinaison simple")){
                    recombinaisonSimple(population.get(i), population.get(j), enfants);
                }
                else { //pour recombinaison avec enjambement PAS FAIT
                    
                }
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
        int max = indi1.getPath().getVilles().size() - 2;
        int coupe = 1 + (int)(Math.random() * ((max - 1) + 1));//endroit où il faut couper les individus pour les recombiner
	ArrayList<String> indi1part1 = new ArrayList<String>();
        indi1part1 = partieArrayList(0, coupe, indi1.getPath().getVilles());
	ArrayList<String> indi1part2 = new ArrayList<String>();
        indi1part2 = partieArrayList(coupe, indi1.getPath().getVilles().size(), indi1.getPath().getVilles());
	ArrayList<String> indi2part1 = new ArrayList<String>();
	indi2part1 = partieArrayList(0, coupe, indi2.getPath().getVilles());
	ArrayList<String> indi2part2 = new ArrayList<String> ();
	indi2part2 = partieArrayList(coupe, indi2.getPath().getVilles().size(), indi2.getPath().getVilles());
	ArrayList<String> villesEnfant1 = new ArrayList<String>();
	villesEnfant1 = indi1part1;
	villesEnfant1.addAll(indi2part2);
        verifChemin(villesEnfant1);
        Individu enfant1 = new Individu(new Chemin(villesEnfant1, Individu.calculFitness(villesEnfant1)), Individu.calculFitness(villesEnfant1));
        ArrayList<String> villesEnfant2 = new ArrayList<String>();
	villesEnfant2 = indi2part1;
	villesEnfant2.addAll(indi1part2);
        verifChemin(villesEnfant2);
        Individu enfant2 = new Individu(new Chemin(villesEnfant2, Individu.calculFitness(villesEnfant2)), Individu.calculFitness(villesEnfant2));
        if (tauxMutation != 0.0){    
            if ((Double)Math.random()*100 <= tauxMutation){ 
                enfant1.mutation();
            }
            enfants.add(enfant1);
            if ((Double)Math.random()*100 <= tauxMutation){ 
               enfant2.mutation();
            }
            enfants.add(enfant2);
        }
    }

    /**
     *Méthode pour vérifier qu'un chemin est bien conforme.
     * i.e. qu'il n'y a pas de double et qu'il ne manque pas de ville.
     * @param villes Le chemin à vérifier.
     */
    public void verifChemin(ArrayList<String> villes){
        ArrayList<String> distanceVilleCopie = (ArrayList<String>)distance.getVilles().clone();
        distanceVilleCopie.remove(villeDepart);
        distanceVilleCopie.remove(villeRetour);
        for(int i = 0; i < distanceVilleCopie.size(); i++){
            if (occurence(villes, distanceVilleCopie.get(i)) == 2){
                villes.remove(villes.lastIndexOf(distanceVilleCopie.get(i)));
            }
            else if (occurence(villes, distanceVilleCopie.get(i)) == 0){
                villes.add(villes.size() - 2,distanceVilleCopie.get(i));
            }
        }
    }
    
    /**
     *Méthode pour connaitre le nombre d'occurence d'une ville dans une ArrayList.
     * @param villes L'ArrayList des villes.
     * @param ville La ville dont on veut savoir le nombre d'occurence.
     * @return Le nombre d'occurence.
     */
    public Integer occurence(ArrayList<String> villes, String ville){
        Integer occur = 0;
        for(int i = 1; i < villes.size() - 1; i++){
            if (villes.get(i) == ville){
                occur++;
            }
        }
        return occur;
    }
    
    /**
     *Méthode pour avoir une partie d'une ArrayList.
     * @param pos1 Postion de début.
     * @param pos2 Postion de fin + 1.
     * @param a ArrayList dont on veut avoir une partie.
     * @return La partie de l'ArrayList.
     */
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
	if (enfants.size() >= population.size()){
                Population e = new Population(enfants);
                e.triePopulation();
		population = e.selectionKMeilleur(taillePopulation);
	}
	else {
            population.clear();
            population.addAll(enfants);
            for(int i = enfants.size(); i < taillePopulation; i++){
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
	population.addAll(KMeilleursParents); 
	int NbManquant = taillePopulation - KMeilleursParents.size();
	if (enfants.size() >= NbManquant){
                Population e = new Population(enfants);
                e.triePopulation();
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