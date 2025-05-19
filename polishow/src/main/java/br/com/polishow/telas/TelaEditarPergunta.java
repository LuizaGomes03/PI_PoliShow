package br.com.polishow.telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaEditarPergunta extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEditarPergunta().createAndShowGUI());
    }

    void createAndShowGUI() {
        // Tamanho desejado da imagem
        int imageWidth = 960;
        int imageHeight = 640;

        // Carrega e redimensiona a imagem de fundo
        ImageIcon originalIcon = new ImageIcon("polishow/src/main/imagens/telaEditarPergunta.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(scaledImage);

        // Cria o JLabel com imagem redimensionada
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(imageWidth, imageHeight));

        // Configura o JFrame
        this.setTitle("EditarPergunta");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(background);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Ícone da seta
        ImageIcon setaIcon = new ImageIcon("polishow/src/main/imagens/arrow-small-left.png");
        Image setaImage = setaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setaIcon = new ImageIcon(setaImage);

        // Botão com imagem de seta
        JButton voltarButton = new JButton(setaIcon);
        voltarButton.setBounds(20, 20, 40, 40);
        voltarButton.setFocusPainted(false);
        voltarButton.setContentAreaFilled(false);
        voltarButton.setBorderPainted(false);
        voltarButton.setOpaque(false);

        voltarButton.addActionListener(e -> {
            this.dispose();
            new TelaInicialProfessor().TelaInicialProfessor(); 
        });

        // ComboBox Selecionar Matéria
        JComboBox<String> materiaComboBox = new JComboBox<>(
            new String[]{"Selecionar Matéria"});
            materiaComboBox.setName("materiaComboBox");
            materiaComboBox.setBackground(new Color(186, 49, 49));
            materiaComboBox.setForeground(Color.WHITE);
            materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
            materiaComboBox.setBounds(340, 230, 330, 45);
            materiaComboBox.setBorder(null);
            materiaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(186, 49, 49),
                        new Color(186, 49, 49),
                        Color.WHITE,
                        new Color(186, 49, 49));
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                return arrow;
            }

            @Override
            public void installListeners() {
                super.installListeners();
                comboBox.setFocusable(false);
            }
        });
        background.add(materiaComboBox);

        background.add(voltarButton);

        // ComboBox Selecionar Pergunta
        JComboBox<String> perguntaComboBox = new JComboBox<>(
                new String[]{"Selecionar Pergunta"});
        perguntaComboBox.setName("perguntaComboBox");
        perguntaComboBox.setBackground(new Color(220, 150, 34));
        perguntaComboBox.setForeground(Color.WHITE);
        perguntaComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        perguntaComboBox.setBounds(340, 365, 330, 45);
        perguntaComboBox.setBorder(null);
        perguntaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(220, 150, 34),
                        new Color(220, 150, 34),
                        Color.WHITE,
                        new Color(220, 150, 34));
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                return arrow;
            }

            @Override
            public void installListeners() {
                super.installListeners();
                comboBox.setFocusable(false);
            }
        });

        // Botão Salvar
        JButton salvarButton = new JButton("SALVAR");
        salvarButton.setName("salvarButton");
        salvarButton.setBackground(new Color(11, 65, 175));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        salvarButton.setFocusPainted(false);
        salvarButton.setBounds(405, 535, 150, 45);
        salvarButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(salvarButton);

        background.add(perguntaComboBox);

        background.add(voltarButton);

        // Torna visível no final
        this.setVisible(true);
    }
}