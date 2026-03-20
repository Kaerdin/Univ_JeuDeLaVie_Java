package Univ_JeuDeLaVie_Java.grille.commandes;
import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;

public class CommandeVit extends Commande{
    public CommandeVit(Cellule cellule) {
        super(cellule);
    }

    public void executer() {
        cellule.vit();
    }
}
