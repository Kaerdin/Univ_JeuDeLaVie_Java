package Univ_JeuDeLaVie_Java.affichage;

import javax.swing.JPanel;
import javax.swing.JButton;

public class Boutons extends JPanel {

    public Boutons() {

        // Par défaut
        JButton button = new JButton("Clique moi");

        // Quand cliqué
        button.addActionListener(e -> {
            System.out.println("bonjour");
        });

        add(button);
    }
}