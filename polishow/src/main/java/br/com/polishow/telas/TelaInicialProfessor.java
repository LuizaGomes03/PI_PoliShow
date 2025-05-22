package br.com.polishow.telas;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TelaInicialProfessor extends JFrame {
    /**
     * 
     */
    public TelaInicialProfessor() {
        setTitle("Tela do Professor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel com imagem de fundo
        JPanel painelFundo = new JPanel() {
            ImageIcon imagemFundo = new ImageIcon("polishow/src/main/imagens/Tela Inicial Professor.png");

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        // Botões invisíveis e arredondados com efeito de clique
        RoundedInvisibleButton btnAdicionar = new RoundedInvisibleButton(30);
        btnAdicionar.setBounds(296, 179, 352, 68);
        btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        RoundedInvisibleButton btnEditar = new RoundedInvisibleButton(30);
        btnEditar.setBounds(296, 266, 352, 68);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        RoundedInvisibleButton btnCadastrar = new RoundedInvisibleButton(30);
        btnCadastrar.setBounds(296, 352, 352, 68);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Ações
        btnAdicionar.addActionListener(e -> {
            new TelaAdicionarPergunta().createAndShowGUI(); 
            dispose(); 
        });
        btnEditar.addActionListener(e -> {
            new TelaEditarPergunta().createAndShowGUI(); 
            dispose(); 
        });
        btnCadastrar.addActionListener(e -> {
            new TelaCadastrarAluno().createAndShowGUI(); 
            dispose(); 
        });

        painelFundo.add(btnAdicionar);
        painelFundo.add(btnEditar);
        painelFundo.add(btnCadastrar);

        setContentPane(painelFundo);
        setVisible(true);

        // Botão Voltar
        JButton voltarButton = new JButton();
        voltarButton.setBounds(10, 12, 35, 40);
        voltarButton.setBorder(BorderFactory.createEmptyBorder());
        voltarButton.setContentAreaFilled(false); // Torna o botão transparente
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelFundo.add(voltarButton);

        voltarButton.addActionListener(e -> {
            new TelaLogin();
            dispose();
        });
    }

     // Classe de botão invisível com efeito de clique e contorno arredondado
     class RoundedInvisibleButton extends JButton {
        private final int arc;
        private boolean isPressed = false;

        public RoundedInvisibleButton(int arc) {
            this.arc = arc;
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);

            // Detecta clique para efeito visual
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent e) {
                    isPressed = true;
                    repaint();
                }

                public void mouseReleased(java.awt.event.MouseEvent e) {
                    isPressed = false;
                    repaint();
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    isPressed = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Efeito de clique: sombra ao pressionar
            if (isPressed) {
                g2.setColor(new Color(0, 0, 0, 50)); // sombra preta semitransparente
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            }

            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            return shape.contains(x, y);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaInicialProfessor::new);
    }
    public void TelaInicialProfessor() {
        throw new UnsupportedOperationException("Unimplemented method 'TelaInicialProfessor'");
    }
}