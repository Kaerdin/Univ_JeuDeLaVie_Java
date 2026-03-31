package Univ_JeuDeLaVie_Java.grille.visiteurs;

import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;
import Univ_JeuDeLaVie_Java.grille.commandes.CommandeMeurt;

/*  
Une cellule vivante doit mourrir si :
    - Elle a moins de 2 voisins vivants
    - Elle a plus de 3 voisins vivants

Une cellule morte doit revenir à la vie si :
    - Elle a exactement 3 voisins vivants
*/
public class VisiteurVictorDestruction extends Visiteur {

    public VisiteurVictorDestruction() {

    };

    public void visiterCelluleVivante(Cellule cellule) {
        jeu.ajouterCommande(new CommandeMeurt(cellule));
    }

    public void visiterCelluleMorte(Cellule cellule) {
        // Victor ne sème que la Mort, il n'a pas besoin de donner vie 
    }
}