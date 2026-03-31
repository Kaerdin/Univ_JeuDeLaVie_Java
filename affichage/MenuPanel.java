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
        // MODE DE JEU (dropdown)

        add(createLabel("Mode de jeu", 40f, Color.WHITE));

        String[] modes = {"Classique", "Day & Night", "HighLife", "Victor Annihilation", "Pandémie du Covid-19"};

        JComboBox<String> modeDropdown = createDropdown(modes, selected -> {
            switch (selected) {
                case "Classique" -> mainUI.setRegle(MainUI.Regle.CLASSIQUE);
                case "Day & Night" -> mainUI.setRegle(MainUI.Regle.DAY_AND_NIGHT);
                case "HighLife" -> mainUI.setRegle(MainUI.Regle.HIGHLIFE);
                case "Victor Annihilation" -> mainUI.setRegle(MainUI.Regle.VICTOR);
                case "Pandémie du Covid-19" -> mainUI.setRegle(MainUI.Regle.COVID);
            }
        });

        add(modeDropdown);
        add(Box.createRigidArea(new Dimension(0, 20)));

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
        JButton pauseBtn = createModernButton("Stop");
        pauseBtn.addActionListener(e -> mainUI.pause());
        
        // Bouton NextStep
        JButton nextStep = createModernButton("Next Step");
        nextStep.addActionListener(e -> mainUI.nextStep());
        
        // #####################################################################
        // Bouton Reset
        JButton resetBtn = createModernButton("Reset Grid");
        resetBtn.addActionListener(e -> jeuUI.getJeu().viderGrille());

        // #####################################################################
        // Bouton Reset
        JButton fillBtn = createModernButton("Fill Grid");
        fillBtn.addActionListener(e -> jeuUI.getJeu().remplirGrille());

        // #####################################################################
        // Bouton Newgrid
        JButton newGrid = createModernButton("New Grid");
        newGrid.addActionListener(e -> jeuUI.getJeu().initialiserGrille());

        // #####################################################################
        // Ajout de skibidi
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Univ_JeuDeLaVie_Java/assets/img/skibidi.png"));Image img = icon.getImage();
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

        // Boutons Jouer / Pause / reset
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(playBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(pauseBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(nextStep);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resetBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(fillBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(newGrid);

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
        add(Box.createRigidArea(new Dimension(0, 200)));
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

    private JComboBox<String> createDropdown(String[] options, java.util.function.Consumer<String> onSelect) {

        JComboBox<String> comboBox = new JComboBox<>(options);

        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        comboBox.setBackground(new Color(40, 40, 40));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(comboBox.getFont().deriveFont(14f));
        comboBox.setFocusable(false);

        // Renderer pour style des items
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                c.setBackground(isSelected ? new Color(70, 70, 70) : new Color(40, 40, 40));
                c.setForeground(Color.WHITE);

                return c;
            }
        });

        // Action
        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            if (selected != null && onSelect != null) {
                onSelect.accept(selected);
            }
        });

        return comboBox;
    }
}