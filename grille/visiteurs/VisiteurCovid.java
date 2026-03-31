package Univ_JeuDeLaVie_Java.grille.visiteurs;

import Univ_JeuDeLaVie_Java.grille.cellules.Cellule;
import Univ_JeuDeLaVie_Java.grille.commandes.CommandeMeurt;
import Univ_JeuDeLaVie_Java.grille.commandes.CommandeVit;

import java.util.Random;

/*  
Une cellule vivante doit mourrir si :
    - Elle a moins de 2 voisins vivants
    - Elle a plus de 3 voisins vivants

Une cellule morte doit revenir à la vie si :
    - Elle a exactement 3 voisins vivants
*/
public class VisiteurCovid extends Visiteur {

    public VisiteurCovid() {

    };


    Random random = new Random();


    public void visiterCelluleVivante(Cellule cellule) {
        boolean chance = random.nextInt(100) < 1; // 1% chance de mourrir

        if (chance) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
        }
    }

    public void visiterCelluleMorte(Cellule cellule) {
        int voisinsVivants = cellule.nombreVoisinesVivantes(jeu);
        boolean chance = random.nextInt(100) < 1; // 1% chance de tomber malade si voisin

        if (voisinsVivants > 0 && chance) {
            jeu.ajouterCommande(new CommandeVit(cellule));
        }
    }
}