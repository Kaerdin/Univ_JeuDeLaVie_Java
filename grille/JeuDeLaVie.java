package Univ_JeuDeLaVie_Java.grille;

import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;
import Univ_JeuDeLaVie_Java.grille.cellules.CelluleEtatMort;
import Univ_JeuDeLaVie_Java.grille.cellules.CelluleEtatVivant;
import Univ_JeuDeLaVie_Java.grille.commandes.Commande;
import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

import java.util.Random;

public class JeuDeLaVie implements Observable {
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private Observateur[] observateurs;
    private Commande[] commandes;
    private Visiteur visiteur;

    public JeuDeLaVie(int largeur, int hauteur) {
        this.xMax = largeur;
        this.yMax = hauteur;
        this.grille = new Cellule[xMax][yMax];
        initialiserGrille();
    }

    private void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (random.nextBoolean()) { // Génération aléatoire 50%
                    grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
                } else {
                    grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
                }
            }
        }
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

    public void attacheObservateur(Observateur o) {
        Observateur[] newObservateurs = new Observateur[observateurs.length + 1];
        System.arraycopy(observateurs, 0, newObservateurs, 0, observateurs.length);
        newObservateurs[observateurs.length] = o;
        observateurs = newObservateurs;
    }

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

    public void notifieObservateurs() {
        for (Observateur obs : observateurs) {
            obs.actualise();
        }
    }

    public void ajouterCommande(Commande commande) {
        Commande[] newCommandes = new Commande[commandes.length + 1];
        System.arraycopy(commandes, 0, newCommandes, 0, commandes.length);
        newCommandes[commandes.length] = commande;
        commandes = newCommandes;
    }

    public void executerCommandes() {
        for (Commande cmd : commandes) {
            cmd.executer();
        }
        notifieObservateurs();
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
        distribueVisiteur(visiteur);

        // Exécuter les commandes
        executerCommandes();

        // Actualisez les observateurs
        notifieObservateurs();
    }
}
