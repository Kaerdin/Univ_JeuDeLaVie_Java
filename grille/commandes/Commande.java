package Univ_JeuDeLaVie_Java.grille.commandes;
import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;

public class Commande {
    protected Cellule cellule;

    public Commande(Cellule cellule) {
        this.cellule = cellule;
    }

    public void executer() {
        CommandeMeurt commandeMeurt = new CommandeMeurt(cellule);
        CommandeVit commandeVit = new CommandeVit(cellule);
        if (cellule.estVivante()) {
            commandeMeurt.executer();
        } else {
            commandeVit.executer();
        }
    }
}