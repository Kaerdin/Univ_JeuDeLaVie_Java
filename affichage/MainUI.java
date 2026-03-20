package Univ_JeuDeLaVie_Java.affichage;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurClassique;

public class MainUI extends JFrame {
    public enum Mode {
        SOURIS, PINCEAU, GOMME
    }
    private JeuDeLaVie jeu;
    private JeuDeLaVieUI jeuUI;
    private Timer timer;

    public MainUI() {

        super("Jeu de la Vie");

        // Initialisation du JDLV
        jeu = new JeuDeLaVie(900, 900);

        // UI
        jeuUI = new JeuDeLaVieUI(jeu);

        // Boutons
        MenuPanel menu = new MenuPanel(this, jeuUI);


        PaintController paintController = new PaintController(jeuUI, jeu);
        menu.setPaintController(paintController);

        // Split fenêtres
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            jeuUI,
            menu
        );
        // Ratio 80 / 20
        splitPane.setResizeWeight(0.1);
        splitPane.setDividerLocation(0.1);
        splitPane.setOneTouchExpandable(true);
        jeuUI.setMinimumSize(new java.awt.Dimension(900, 0)); // largeur min
        menu.setMinimumSize(new java.awt.Dimension(300, 0)); // largeur min

        splitPane.setOneTouchExpandable(false); // pas de petites flèches

        // Observer
        jeu.attacheObservateur(jeuUI);

        // Visiteur (règles)
        VisiteurClassique visiteur = new VisiteurClassique();
        visiteur.setJeu(jeu);
        jeu.distribueVisiteur(visiteur);

        // Layout   
        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);

        // Fenêtre
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Simulation
        int delay = 50;
        timer = new Timer(delay, e -> jeu.calculerGenerationSuivante());
        timer.start();
    }

    // BONUS : contrôle du timer
    public void pause() {
        timer.stop();
    }

    public void play() {
        timer.start();
    }

    public void setSpeed(int delay) {
        timer.setDelay(delay);
    }

    public JeuDeLaVie getJeu() {
        return jeu;
    }
}