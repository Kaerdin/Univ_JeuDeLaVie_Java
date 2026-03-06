package Univ_JeuDeLaVie_Java.grille.visiteurs;

import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;

public abstract class Visiteur {
    JeuDeLaVie jeu;

    public abstract void visiterCelluleVivante(Cellule cellule);

    public abstract void visiterCelluleMorte(Cellule cellule);

}
