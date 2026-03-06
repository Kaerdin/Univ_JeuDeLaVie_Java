package Univ_JeuDeLaVie_Java.affichage;
import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import java.awt.Graphics;
import javax.swing.JPanel;

public class JeuDeLaVieUI extends JPanel {
    private JeuDeLaVie jeu;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    public void actualise(){
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < jeu.getXMax(); i++) {
            for(int j = 0; j < jeu.getYMax(); j++) {
                if (jeu.getGrilleXY(i, j).estVivante()) {
                    g.fillOval(i*3,j*3,3,3);
                }
            }
        }
    }
}