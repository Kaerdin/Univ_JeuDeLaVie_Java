package Univ_JeuDeLaVie_Java.affichage;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import Univ_JeuDeLaVie_Java.grille.JeuDeLaVie;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurClassique;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurDayAndNight;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurHighLife;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurVictorDestruction;
import Univ_JeuDeLaVie_Java.grille.visiteurs.VisiteurCovid;

public class MainUI extends JFrame {
    public enum Mode {
        SOURIS, PINCEAU, GOMME
    }
    private JeuDeLaVie jeu;
    private JeuDeLaVieUI jeuUI;
    private Timer timer;


    private VisiteurClassique visiteurC = new VisiteurClassique();
    private VisiteurDayAndNight visiteurDaN = new VisiteurDayAndNight();
    private VisiteurHighLife visiteurHL = new VisiteurHighLife();
    private VisiteurVictorDestruction visiteurVD = new VisiteurVictorDestruction();
    private VisiteurCovid visiteurCD = new VisiteurCovid();

    public enum Regle {
        CLASSIQUE,
        DAY_AND_NIGHT,
        HIGHLIFE,
        VICTOR,
        COVID
    }

    public MainUI() {

        super("Jeu de la Vie");

        // Initialisation du JDLV
        jeu = new JeuDeLaVie(900, 900);

        // UI
        jeuUI = new JeuDeLaVieUI(jeu);

        // Panel
        MenuPanel menu = new MenuPanel(this, jeuUI);

        // Brush
        PaintController paintController = new PaintController(jeuUI, jeu);
        menu.setPaintController(paintController);

        // J'active la possibilité de scroll le menu
        JScrollPane scrollMenu = new JScrollPane(menu);
        scrollMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



        // Split fenêtres
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            jeuUI,
            scrollMenu
        );
        // Ratio 80 / 20
        splitPane.setOneTouchExpandable(false); // pas de petites flèches pour extend ou fermer
        
        jeuUI.setMinimumSize(new java.awt.Dimension(900, 0)); // largeur min
        scrollMenu.setMinimumSize(new java.awt.Dimension(300, 0)); // largeur min

        // Observer
        jeu.attacheObservateur(jeuUI);

        // Visiteur (règles)
        visiteurC.setJeu(jeu);
        visiteurDaN.setJeu(jeu);
        visiteurHL.setJeu(jeu);
        visiteurVD.setJeu(jeu);
        visiteurCD.setJeu(jeu);

        // Visiteur par défaut
        jeu.distribueVisiteur(visiteurC);

        // Layout   
        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);

        // Fenêtre
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Simulation
        int delay = 50;
        timer = new Timer(delay, e -> jeu.calculerGenerationSuivante());
        timer.start();
    }

    public void pause() {
        timer.stop();
    }


    public void nextStep() {
        this.pause(); // Met en pause auto
        jeu.calculerGenerationSuivante();
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


    public void setRegle(Regle regle) {
        switch (regle) {
            case CLASSIQUE -> this.jeu.distribueVisiteur(this.visiteurC);
            case DAY_AND_NIGHT -> this.jeu.distribueVisiteur(this.visiteurDaN);
            case HIGHLIFE -> this.jeu.distribueVisiteur(this.visiteurHL);
            case VICTOR -> this.jeu.distribueVisiteur(this.visiteurVD);
            case COVID -> this.jeu.distribueVisiteur(this.visiteurCD);
        }
    }

}