package br.com.polishow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaEditarPergunta extends JFrame {

    public TelaEditarPergunta() {
        setTitle("Editar Pergunta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);

        JPanel painel = new JPanel() {
            ImageIcon imagem = new ImageIcon("polishow/src/main/imagens/telaEditarPerguntas.png");
            Image img = imagem.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);

        // ComboBox "Matéria"
        JComboBox<String> materiaComboBox = new JComboBox<>(
                new String[]{"Selecione a Matéria", "Português", "Matemática", "História"});
        materiaComboBox.setName("materiaComboBox");
        materiaComboBox.setBackground(new Color(187,49,49));
        materiaComboBox.setForeground(Color.WHITE);
        materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        materiaComboBox.setBounds(350, 210, 310, 45);
        materiaComboBox.setBorder(null);
        materiaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(187,49,49),
                        new Color(187,49,49),
                        Color.WHITE,
                        new Color(187,49,49));
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                return arrow;
            }
        });


        materiaComboBox.setFocusable(false);
        materiaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        painel.add(materiaComboBox);

        JComboBox<String> perguntaComboBox = new JComboBox<>(
            new String[]{"Selecione a Pergunta"});
    perguntaComboBox.setName("perguntaComboBox");
    perguntaComboBox.setBackground(new Color(220, 150, 34));
    perguntaComboBox.setForeground(Color.WHITE);
    perguntaComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
    perguntaComboBox.setBounds(340, 345, 310, 45);
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
    });
    perguntaComboBox.setFocusable(false);
    perguntaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    painel.add(perguntaComboBox);

        JButton salvarButton = new JButton("CADASTRAR");
        salvarButton.setName("cadastrarButton");
        salvarButton.setBackground(new Color(11, 65, 175));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        salvarButton.setFocusPainted(false);
        salvarButton.setBounds(380, 570, 200, 45);
        salvarButton.setBorder(BorderFactory.createEmptyBorder());
        painel.add(salvarButton);
    }

    private void estilizarBotaoColorido(JButton botao, java.awt.Color corFundo) {
        botao.setBackground(corFundo);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setForeground(Color.WHITE);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static class RoundBorder extends javax.swing.border.AbstractBorder {
        private int radius;
        public RoundBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getForeground());
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
        @Override
        public java.awt.Insets getBorderInsets(java.awt.Component c) {
            return new java.awt.Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        @Override
        public java.awt.Insets getBorderInsets(java.awt.Component c, java.awt.Insets insets) {
            insets.left = insets.top = this.radius+1;
            insets.right = insets.bottom = this.radius;
            return insets;
        }
    }

    public static void main(String[] args) {
        new TelaEditarPergunta();
    }
}