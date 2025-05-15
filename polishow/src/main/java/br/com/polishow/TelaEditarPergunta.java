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
        setUndecorated(true);

        JPanel painel = new JPanel() {
            ImageIcon imagem = new ImageIcon("polishow\\\\src\\\\main\\\\imagens\\\\editar perguntas (1).png");
            Image img = imagem.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);

        // ComboBox "Matéria"
        JComboBox<String> serieComboBox = new JComboBox<>(
                new String[]{"Matéria", "Português", "Matemática", "História"});
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(220, 150, 34));
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        serieComboBox.setBounds(300, 293, 360, 45);
        serieComboBox.setBorder(null);
        serieComboBox.setUI(new BasicComboBoxUI() {
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
        serieComboBox.setFocusable(false);
        serieComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        painel.add(serieComboBox);

        // ComboBox "Carregar Pergunta" 100% transparente, sem borda, sem escrita
        JComboBox<String> perguntaComboBox = new JComboBox<>(
                new String[]{"Carregar Pergunta"});
        perguntaComboBox.setBounds(320, 370, 340, 75);
        perguntaComboBox.setOpaque(false);
        perguntaComboBox.setBackground(new Color(0,0,0,0));
        perguntaComboBox.setForeground(new Color(0,0,0,0));
        perguntaComboBox.setBorder(null);
        perguntaComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        perguntaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        perguntaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            public void paint(Graphics g, javax.swing.JComponent c) {
                // Não desenha nada, garantindo transparência total
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setVisible(false);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setBorder(null);
                return button;
            }
        });
        perguntaComboBox.setEditable(false);
        painel.add(perguntaComboBox);

        // Botão "Salvar" - cor (18, 66, 177)
        JButton btnSalvar = new JButton();
        btnSalvar.setBounds(390, 550, 190, 75);
        btnSalvar.setOpaque(false);
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Botão 'Salvar' clicado.");
        });
        painel.add(btnSalvar);

        // Botão "Sair"
        JButton btnSair = new JButton();
        btnSair.setBounds(10, 10, 40, 40);
        btnSair.setOpaque(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.setForeground(new Color(220, 53, 69));
        btnSair.setFont(new Font("Arial", Font.BOLD, 18));
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSair.addActionListener((ActionEvent e) -> {
            dispose();
        });
        painel.add(btnSair);

        setContentPane(painel);
        setVisible(true);
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