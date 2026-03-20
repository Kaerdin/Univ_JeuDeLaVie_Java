package Univ_JeuDeLaVie_Java.grille.visiteurs;

import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;
import Univ_JeuDeLaVie_Java.grille.commandes.CommandeMeurt;
import Univ_JeuDeLaVie_Java.grille.commandes.CommandeVit;

/*  
Une cellule vivante doit mourrir si :
    - Elle a moins de 2 voisins vivants
    - Elle a plus de 3 voisins vivants

Une cellule morte doit revenir à la vie si :
    - Elle a exactement 3 voisins vivants
*/
public class VisiteurDayAndNight extends Visiteur {

    public VisiteurDayAndNight() {

    };

    public void visiterCelluleVivante(Cellule cellule) {
        int voisinsVivants = cellule.nombreVoisinesVivantes(jeu);

        if (voisinsVivants != 3 && voisinsVivants != 4 &&
            voisinsVivants != 6 && voisinsVivants != 7 &&
            voisinsVivants != 8) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
        }
    }

    public void visiterCelluleMorte(Cellule cellule) {
        int voisinsVivants = cellule.nombreVoisinesVivantes(jeu);

        if (voisinsVivants == 3 || voisinsVivants == 6 ||
            voisinsVivants == 7 || voisinsVivants == 8) {
            jeu.ajouterCommande(new CommandeVit(cellule));
        }
    }
}