package Univ_JeuDeLaVie_Java.grille;

import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;
import Univ_JeuDeLaVie_Java.grille.cellules.CelluleEtatMort;
import Univ_JeuDeLaVie_Java.grille.cellules.CelluleEtatVivant;
import Univ_JeuDeLaVie_Java.grille.commandes.Commande;
import Univ_JeuDeLaVie_Java.grille.observeurs.Observable;
import Univ_JeuDeLaVie_Java.grille.observeurs.Observateur;
import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

import java.util.ArrayList;
import java.util.Random;

public class JeuDeLaVie implements Observable {
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private Observateur[] observateurs = new Observateur[0];
    private ArrayList<Commande> commandes = new ArrayList<Commande>();
    private Visiteur visiteur;
    private int generationCourrante = 0;

    public JeuDeLaVie(int largeur, int hauteur) {
        this.xMax = largeur;
        this.yMax = hauteur;
        this.grille = new Cellule[xMax][yMax];
        initialiserGrille();
    }

    public void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if ((i < 250 || i > grille.length-250 || j < 250 || j > grille[i].length-250) ||  random.nextBoolean()) { // Génération aléatoire 50%
                    grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
                } else {
                    grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
                }
            }
        }
        this.generationCourrante = 0;
        notifieObservateurs();
    }

    public void viderGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
            }
        }
        notifieObservateurs();
        this.generationCourrante = 0;
    }
    public void remplirGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
            }
        }
        notifieObservateurs();
        this.generationCourrante = 0;
    }

    public Cellule getGrilleXY(int x, int y) {
        if (x >= 0 && x < grille.length && y >= 0 && y < grille[0].length) {
            return grille[x][y];
        }
        return null;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    @Override
    public void attacheObservateur(Observateur o) {
        Observateur[] newObservateurs = new Observateur[observateurs.length + 1];
        System.arraycopy(observateurs, 0, newObservateurs, 0, observateurs.length);
        newObservateurs[observateurs.length] = o;
        observateurs = newObservateurs;
    }

    @Override
    public void detacheObservateur(Observateur o) {
        Observateur[] newObservateurs = new Observateur[observateurs.length - 1];
        int index = 0;
        for (Observateur obs : observateurs) {
            if (!obs.equals(o)) {
                newObservateurs[index++] = obs;
            }
        }
        observateurs = newObservateurs;
    }

    @Override
    public void notifieObservateurs() {
        for (Observateur obs : observateurs) {
            obs.actualise();
        }
    }

    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    public void executerCommandes() {
        for (Commande cmd : commandes) {
            cmd.executer();
        }
        commandes = new ArrayList<Commande>();
    }

    public void distribueVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
        for (Cellule[] ligne : grille) {
            for (Cellule cellule : ligne) {
                cellule.accepte(this.visiteur, cellule);
            }
        }
    }

    public void calculerGenerationSuivante() {
        // Distribuer un visiteur
        distribueVisiteur(this.visiteur);

        // Exécuter les commandes
        executerCommandes();
        
        // Actualisez les observateurs
        notifieObservateurs();

        this.generationCourrante += 1;
        System.out.println(generationCourrante);
    }
}
