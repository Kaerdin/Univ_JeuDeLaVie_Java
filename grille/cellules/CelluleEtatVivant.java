package Univ_JeuDeLaVie_Java.grille.cellules;

import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

public class CelluleEtatVivant implements CelluleEtat {
    // Instance unique
    private static final CelluleEtatVivant instance = new CelluleEtatVivant();

    private CelluleEtatVivant() {}

    public static CelluleEtatVivant getInstance() {
        return instance;
    }

    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    @Override
    public CelluleEtat vit() {
        return this;
    }

    @Override
    public boolean estVivante() {
        return true;
    }

    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiterCelluleVivante(cellule);
    }
}