package br.com.polishow.telas;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;

public class TelaJogo extends JFrame {

    public TelaJogo() {
        setTitle("Tela Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel com imagem de fundo
        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/TelaJogo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        // Botão de sair redondo, menor e transparente
        JButton btnSair = new JButton();
        btnSair.setBounds(48, 15, 40, 35);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setOpaque(false);
        btnSair.setBorderPainted(false);
        btnSair.setBorder(new RoundBorder(35));
        btnSair.addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair do jogo?",
                "Sair do Jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (resposta == JOptionPane.YES_OPTION) {
                dispose();
                new TelaFeedback(); // redireciona para a tela de feedback
            }
        });
        painelFundo.add(btnSair);

        // Botão de eliminar três questões (redondo, menor e transparente)
        JButton btnElimina = new JButton();
        btnElimina.setBounds(100, 15, 40, 35); // posição ao lado do botão sair
        btnElimina.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnElimina.setFocusPainted(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setOpaque(false);
        btnElimina.setBorderPainted(false);
        btnElimina.setBorder(new RoundBorder(35));
        btnElimina.setToolTipText("Eliminar três questões");
        btnElimina.addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Gostaria de eliminar 3 alternativas?\nLembre-se que você só poderá usar essa ajuda uma vez durante toda sua jogada.",
                "Eliminar Questões",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (resposta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    this,
                    "Três questões foram eliminadas!",
                    "Eliminação",
                    JOptionPane.INFORMATION_MESSAGE
                );
                // Aqui você pode adicionar a lógica para eliminar as questões
            }
        });
        painelFundo.add(btnElimina);

        // Botão para pular para a próxima questão (redondo, menor e transparente)
        JButton btnPular = new JButton();
        btnPular.setBounds(152, 15, 40, 35); // posição ao lado do botão eliminar
        btnPular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPular.setFocusPainted(false);
        btnPular.setContentAreaFilled(false);
        btnPular.setOpaque(false);
        btnPular.setBorderPainted(false);
        btnPular.setBorder(new RoundBorder(35));
        btnPular.setToolTipText("Pular para próxima questão");
        btnPular.addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Você tem certeza que gostaria de pular essa questão?",
                "Pular Questão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (resposta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    this,
                    "Questão pulada!",
                    "Pular",
                    JOptionPane.INFORMATION_MESSAGE
                );
                // Aqui você pode adicionar a lógica para pular a questão
            }
        });
        painelFundo.add(btnPular);

        // JLabel com uma pergunta de matemática
        JLabel lblPergunta = new JLabel("<html><b>Quanto é 7 x 8?</b></html>");
        lblPergunta.setBounds(300, 120, 400, 40);
        lblPergunta.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        lblPergunta.setForeground(java.awt.Color.WHITE);
        painelFundo.add(lblPergunta);

        // Botões de alternativas A-E
        String[] alternativas = {
            "A) 54",
            "B) 56",
            "C) 58",
            "D) 48",
            "E) 64"
        };
        int y = 180;
        for (int i = 0; i < alternativas.length; i++) {
            JButton btnAlternativa = new JButton(alternativas[i]);
            btnAlternativa.setBounds(300, y, 360, 40);
            btnAlternativa.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
            btnAlternativa.setFocusPainted(false);
            painelFundo.add(btnAlternativa);
            y += 50;
        }

        setContentPane(painelFundo);
        setVisible(true);
    }

    // Borda arredondada personalizada
    static class RoundBorder extends AbstractBorder {
        private int radius;
        public RoundBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getForeground());
            g2.draw(new RoundRectangle2D.Float(x, y, width-1, height-1, radius, radius));
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        new TelaJogo();
    }
}