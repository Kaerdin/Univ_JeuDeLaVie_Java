package Univ_JeuDeLaVie_Java.affichage;

import javax.swing.*;

import java.awt.*;


public class MenuPanel extends JPanel {
    private JPanel modePanel; // contiendra Souris / Pinceau / Gomme


    private PaintController paintController;

    public void setPaintController(PaintController pc) {
        this.paintController = pc;
    }

    public MenuPanel(MainUI mainUI, JeuDeLaVieUI jeuUI) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(25, 25, 25));

        // #####################################################################
        // BOUTONS
        JButton sourisBtn = createModernButton("Souris");
        JButton pinceauBtn = createModernButton("Pinceau");
        JButton gommeBtn = createModernButton("Gomme");
        sourisBtn.addActionListener(e -> paintController.setMode(MainUI.Mode.SOURIS));
        pinceauBtn.addActionListener(e -> paintController.setMode(MainUI.Mode.PINCEAU));
        gommeBtn.addActionListener(e -> paintController.setMode(MainUI.Mode.GOMME));
        
        // #####################################################################
        // Slider taille pinceau
        JSlider brushSlider = createModernSlider(1, 100, 1); // min=1, max=20, défaut=1
        brushSlider.setMajorTickSpacing(5);
        brushSlider.setPaintTicks(true);
        brushSlider.setPaintLabels(true);
        brushSlider.addChangeListener(e -> {
            if (paintController != null) {
                paintController.setBrushSize(brushSlider.getValue());
            }
        });  
        
        // Dessin boxes
        modePanel = new JPanel();
        modePanel.setLayout(new GridLayout(3, 1, 5, 5)); // 3 boutons empilés avec un petit espace
        modePanel.setBackground(new Color(25, 25, 25)); // même fond sombre

        // #####################################################################
        // Bouton Play
        JButton playBtn = createModernButton("Play");
        playBtn.addActionListener(e -> mainUI.play());

        // Bouton Pause
        JButton pauseBtn = createModernButton("Pause");
        pauseBtn.addActionListener(e -> mainUI.pause());

        // #####################################################################
        // Ajout de skibidi
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("../assets/img/skibidi.png"));
        imageLabel.setIcon(icon);
        imageLabel.setAlignmentX(CENTER_ALIGNMENT); // centre dans BoxLayout vertical
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // taille voulue
        imageLabel.setIcon(new ImageIcon(scaledImg));

        // #####################################################################
        // Slider vitesse
        JSlider speedSlider = createModernSlider(0, 1000, 50);
        speedSlider.addChangeListener(e -> {
            mainUI.setSpeed(speedSlider.getValue());
        });

        // #####################################################################
        // Slider Rouge
        JSlider rSlider = createModernSlider(0, 150, 150);
        rSlider.setMajorTickSpacing(50);
        rSlider.setPaintTicks(true);
        rSlider.setPaintLabels(true);
        rSlider.addChangeListener(e -> jeuUI.setColorR(rSlider.getValue()));
        

        // Slider Vert
        JSlider gSlider = createModernSlider(0, 150, 0);
        gSlider.setMajorTickSpacing(50);
        gSlider.setPaintTicks(true);
        gSlider.setPaintLabels(true);
        gSlider.addChangeListener(e -> jeuUI.setColorG(gSlider.getValue()));
        
        // Slider Bleu
        JSlider bSlider = createModernSlider(0, 150, 0);
        bSlider.setMajorTickSpacing(50);
        bSlider.setPaintTicks(true);
        bSlider.setPaintLabels(true);
        bSlider.addChangeListener(e -> jeuUI.setColorB(bSlider.getValue()));


    
        // #####################################################################
        // Skibidi
        add(imageLabel);

        // Dessin
        modePanel.add(sourisBtn);
        modePanel.add(pinceauBtn);
        modePanel.add(gommeBtn);

        // Ajout dans le panel
        add(createLabel("Taille du pinceau", 30f, Color.WHITE));
        add(brushSlider);
        add(Box.createRigidArea(new Dimension(0, 20))); // petit espace après
                
        // Puis ajoute le modePanel dans ton MenuPanel
        add(modePanel);
        add(Box.createRigidArea(new Dimension(0, 20))); // espace après

        // Ajout des composants
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(playBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(pauseBtn);

        // Label vitesse
        add(createLabel("Vitesse", 50f, Color.WHITE));
        add(speedSlider);

        add(Box.createRigidArea(new Dimension(0, 100)));

        // Label couleurs
        add(createLabel("Couleur des cellules", 50f, Color.WHITE));
        add(createLabel("Rouge", 30f, Color.RED));
        add(rSlider);
        add(createLabel("Vert", 30f, Color.GREEN));
        add(gSlider);
        add(createLabel("Bleu", 30f, Color.BLUE));
        add(bSlider);
        // #####################################################################
    }

    private JLabel createLabel(String text, float fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(fontSize));
        label.setForeground(color);
        return label;
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground().darker());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(button.getFont().deriveFont(16f));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    private JSlider createModernSlider(int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing((max - min) / 5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(0, 0, 0)); // fond sombre
        slider.setForeground(Color.WHITE); // labels blancs
        slider.setFont(slider.getFont().deriveFont(12f));
        return slider;
    }
}