package Univ_JeuDeLaVie_Java.grille.commandes;
import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;

public class CommandeMeurt extends Commande{
    public CommandeMeurt(Cellule cellule) {
        super(cellule);
    }

    public void executer() {
        cellule.meurt();
    }
}
