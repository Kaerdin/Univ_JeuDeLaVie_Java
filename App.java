package Univ_JeuDeLaVie_Java;
import javax.swing.JFrame;
import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.affichage.JeuDeLaVieUI;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu de la Vie");
        JeuDeLaVie jeu = new JeuDeLaVie(400, 300);
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu);

        frame.add(jeuUI);
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Boucle de jeu
        while (true) {
            jeuUI.actualise();
            jeu.calculerGenerationSuivante();
            try {
                Thread.sleep(100); // Pause pour ralentir la boucle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
