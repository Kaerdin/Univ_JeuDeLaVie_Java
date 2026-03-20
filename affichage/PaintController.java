package Univ_JeuDeLaVie_Java.affichage;

import java.awt.event.*;

import Univ_JeuDeLaVie_Java.affichage.MainUI.Mode;
import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;

public class PaintController {

    private JeuDeLaVieUI jeuUI;
    private JeuDeLaVie jeu;
    private Mode mode = Mode.SOURIS;

    private int brushSize = 1;

    public void setBrushSize(int size) {
        this.brushSize = Math.max(1, size); // minimum 1
        System.out.println("Brush size : " + brushSize);
    }

    public PaintController(JeuDeLaVieUI jeuUI, JeuDeLaVie jeu) {
        this.jeuUI = jeuUI;
        this.jeu = jeu;
        attachMouseListeners();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    private void attachMouseListeners() {
        jeuUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouse(e);
            }
        });

        jeuUI.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouse(e);
            }
        });
    }
    
    private void handleMouse(MouseEvent e) {
        int cellX = (int) ((e.getX() - jeuUI.getOffsetX()) / jeuUI.getScale());
        int cellY = (int) ((e.getY() - jeuUI.getOffsetY()) / jeuUI.getScale());

        for (int dx = -brushSize/2; dx <= brushSize/2; dx++) {
            for (int dy = -brushSize/2; dy <= brushSize/2; dy++) {
                int x = cellX + dx;
                int y = cellY + dy;
                if (x < 0 || y < 0 || x >= jeu.getXMax() || y >= jeu.getYMax()) continue;

                switch (mode) {
                    case PINCEAU -> jeu.getGrilleXY(x, y).vit();
                    case GOMME -> jeu.getGrilleXY(x, y).meurt();
                    case SOURIS -> {} // optionnel
                }
            }
        }

        jeuUI.repaint();
    }
}