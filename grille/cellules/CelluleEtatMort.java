package Univ_JeuDeLaVie_Java.grille.cellules;

import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

public class CelluleEtatMort implements CelluleEtat{
    // Instance unique
    private static final CelluleEtatMort instance = new CelluleEtatMort();

    private CelluleEtatMort() {}

    public static CelluleEtatMort getInstance() {
        return instance;
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public boolean estVivante() {
        return false;
    }

    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiterCelluleMorte(cellule);
    }
}