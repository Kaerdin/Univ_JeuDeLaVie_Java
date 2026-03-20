package Univ_JeuDeLaVie_Java.affichage;
import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.grille.observeurs.Observateur;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

import javax.swing.JPanel;


public class JeuDeLaVieUI extends JPanel implements Observateur{
    private JeuDeLaVie jeu;
    private double scale = 1.0;
    private double offsetX = 0;
    private double offsetY = 0;

    private int colorR = 150;
    private int colorG = 0;
    private int colorB = 0;

    public void setColorR(int r) { this.colorR = r; repaint(); }
    public void setColorG(int g) { this.colorG = g; repaint(); }
    public void setColorB(int b) { this.colorB = b; repaint(); }

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        

        this.jeu = jeu;
        addMouseWheelListener(e -> {

            double zoomFactor = 1.5;

            double mouseX = e.getX();
            double mouseY = e.getY();

            double oldScale = scale;

            if (e.getWheelRotation() < 0) {
                scale *= zoomFactor;
            } else {
                scale /= zoomFactor;
            }

            offsetX = mouseX - (mouseX - offsetX) * (scale / oldScale);
            offsetY = mouseY - (mouseY - offsetY) * (scale / oldScale);

            System.out.println("Zoom : " + scale + " | Offset : (" + offsetX + ", " + offsetY + ") | Souris : (" + mouseX + ", " + mouseY + ")");


            if (scale > 2500) {
                scale = 1.0; 
                offsetX = 0;
                offsetY = 0;
            }else if (scale < 0.01) {
                scale = 1.0; 
                offsetX = 0;
                offsetY = 0;
            }

            repaint();
        });
    }

    public double getScale(){
        return scale;
    }

    public double getOffsetX(){
        return offsetX;
    }


    public double getOffsetY(){
        return offsetY;
    }

    public void actualise(){
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.BLACK);

        Random random = new Random();

        for(int i = 0; i < jeu.getXMax(); i++) {
            for(int j = 0; j < jeu.getYMax(); j++) {

                if (jeu.getGrilleXY(i, j).estVivante()) {

                    g.setColor(new Color(
                        Math.min(colorR + random.nextInt(55), 255),
                        Math.min(colorG + random.nextInt(55), 255),
                        Math.min(colorB + random.nextInt(55), 255)
                    ));

                    int x = (int)(i  * scale + offsetX);
                    int y = (int)(j * scale + offsetY);
                    int size = (int)Math.ceil( scale);

                    g.fillRect(x, y, size, size);
                }
            }
        }
    }
}