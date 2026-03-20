package Univ_JeuDeLaVie_Java.grille.cellules;
import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.grille.visiteurs.Visiteur;

public class Cellule{
    private int x;
    private int y;
    private CelluleEtat etat;

    public Cellule(int x, int y, CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    public void meurt() {
        this.etat = CelluleEtatMort.getInstance();
    }

    public void vit() {
        this.etat = CelluleEtatVivant.getInstance();
    }

    public boolean estVivante() {
        return this.etat == CelluleEtatVivant.getInstance();
    }

    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                Cellule voisine = jeu.getGrilleXY(x + i, y + j);
                if (voisine != null && voisine.estVivante()) {
                    count++;
                }
            }
        }
        return count;
    }

    public CelluleEtat getEtat() {
        return etat;
    }

    public void accepte(Visiteur visiteur, Cellule cellule) {
        if (this.estVivante()) {
            visiteur.visiterCelluleVivante(cellule);
        } else {
            visiteur.visiterCelluleMorte(cellule);
        }
    }

}
