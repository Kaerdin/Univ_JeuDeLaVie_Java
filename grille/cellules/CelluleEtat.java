package Univ_JeuDeLaVie_Java.grille.cellules;

import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

public interface CelluleEtat {
    public CelluleEtat meurt();
    public CelluleEtat vit();
    public boolean estVivante();
    public void accepte(Visiteur visiteur, Cellule cellule);
}