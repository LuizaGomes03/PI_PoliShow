package br.com.polishow.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

public class TelaJogo extends JFrame {

    private boolean ajudaUsada = false;
    private List<JButton> alternativasBtns = new ArrayList<>();
    private String respostaCorreta = "B) 56"; // aqui provavelmente entrará o banco de dados -- arthur

    @SuppressWarnings("deprecation")
    public TelaJogo() {
        setTitle("Tela Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // img
        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/TelaJogo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        // botão de sair 
        JButton btnSair = new JButton();
        btnSair.setBounds(48, 15, 40, 35);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setOpaque(false);
        btnSair.setBorderPainted(false);
        btnSair.setBorder(new RoundBorder(35));
        btnSair.setToolTipText("Sair do Jogo");
        
        btnSair.addActionListener((ActionEvent e) -> {
            Object[] opcoes = {"Sim", "Não"};
            int resposta = JOptionPane.showOptionDialog(
                this,
                "Deseja realmente sair do jogo?",
                "Sair do Jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[1]
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dispose(); 

                // link
                JFrame frameFeedback = new JFrame("Pontuação Final");
                frameFeedback.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameFeedback.setSize(960, 640);
                frameFeedback.setResizable(false);
                frameFeedback.setLocationRelativeTo(null);

                TelaFeedback painelFeedback = new TelaFeedback();

                // botão voltar ao menu
                JButton botao = new JButton("Voltar ao Menu");
                botao.setBounds(700, 540, 200, 45);
                botao.setBackground(new Color(0, 120, 215));
                botao.setForeground(Color.WHITE);
                botao.setFont(new Font("SansSerif", Font.BOLD, 16));
                botao.setFocusPainted(false);
                botao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

                botao.addActionListener(ev -> {
                    JOptionPane.showMessageDialog(frameFeedback, "Voltando ao menu...");
                    frameFeedback.dispose();
                    new TelaInicio();
                });

                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setPreferredSize(new Dimension(960, 640));
                painelFeedback.setBounds(0, 0, 960, 640);
                botao.setBounds(700, 540, 200, 45);
                layeredPane.add(painelFeedback, Integer.valueOf(0));
                layeredPane.add(botao, Integer.valueOf(1));

                frameFeedback.setContentPane(layeredPane);
                frameFeedback.setVisible(true);
            }
        });
        painelFundo.add(btnSair);

        // eliminar três questões
        JButton btnElimina = new JButton();
        btnElimina.setBounds(95, 15, 40, 35); 
        btnElimina.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnElimina.setFocusPainted(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setOpaque(false);
        btnElimina.setBorderPainted(false);
        btnElimina.setBorder(new RoundBorder(35));
        btnElimina.setToolTipText("Eliminar três questões");

        btnElimina.addActionListener((ActionEvent e) -> {
            if (ajudaUsada) {
                JOptionPane.showMessageDialog(this, "Você já usou essa ajuda.", "Ajuda já usada", JOptionPane.WARNING_MESSAGE);
                return;
            }

        Object[] opcoes = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(
            this,
            "Gostaria de eliminar 3 alternativas?\nLembre-se que você só poderá usar essa ajuda uma vez durante toda sua jogada.",
            "Eliminar Questões",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[1]
        );

        if (resposta == JOptionPane.YES_OPTION) {
            List<JButton> erradas = alternativasBtns.stream()
                .filter(btn -> !btn.getText().equals(respostaCorreta))
                .collect(Collectors.toList());

            Collections.shuffle(erradas);

            for (int i = 0; i < 3 && i < erradas.size(); i++) {
                JButton btn = erradas.get(i);
                btn.setEnabled(false);
                btn.setBackground(Color.GRAY);  // opcional
            }

            ajudaUsada = true;

            JOptionPane.showMessageDialog(
                this,
                "Três alternativas foram eliminadas!",
                "Ajuda utilizada",
                JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        painelFundo.add(btnElimina);

        //pular para a próxima questão 
        JButton btnPular = new JButton();
        btnPular.setBounds(145, 15, 35, 35); 
        btnPular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPular.setFocusPainted(false);
        btnPular.setContentAreaFilled(false);
        btnPular.setOpaque(false);
        btnPular.setBorderPainted(false);
        btnPular.setBorder(new RoundBorder(35));
        btnPular.setToolTipText("Pular para próxima questão");
        btnPular.addActionListener((ActionEvent e) -> {
        Object[] opcoes = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(
            this,
            "Você tem certeza que gostaria de pular essa questão?",
            "Pular Questão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[1]
        );
        if (escolha == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                this,
                "Questão pulada!",
                "Pular",
                JOptionPane.INFORMATION_MESSAGE
            );
            // cod lógica para pular a questão vai aqui
            }
        });
        painelFundo.add(btnPular);


        // JLabel com uma pergunta de matemática
        JLabel lblPergunta = new JLabel("<html><b>Quanto é 7 x 8?</b></html>");
        lblPergunta.setBounds(180, 120, 400, 40);
        lblPergunta.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        lblPergunta.setForeground(java.awt.Color.WHITE);
        painelFundo.add(lblPergunta);

        //alternativas
        String[] alternativas = {
            "A) 54",
            "B) 56",
            "C) 58",
            "D) 48",
            "E) 64"
        };
        int y = 190;
        for (int i = 0; i < alternativas.length; i++) {
            JButton btnAlternativa = new JButton(alternativas[i]);
            btnAlternativa.setBounds(180, y, 580, 40);
            btnAlternativa.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
            btnAlternativa.setFocusPainted(false);
            btnAlternativa.setHorizontalAlignment(SwingConstants.LEFT);
            btnAlternativa.setMargin(new Insets(0, 15, 0, 0));
            btnAlternativa.setBackground(new Color(18, 66, 177)); 
            btnAlternativa.setForeground(Color.WHITE);
            btnAlternativa.setOpaque(true);
            btnAlternativa.setBorderPainted(false);
            btnAlternativa.setCursor(new Cursor(HAND_CURSOR));
            painelFundo.add(btnAlternativa);
            y += 60;

            alternativasBtns.add(btnAlternativa);
        }

        setContentPane(painelFundo);
        setVisible(true);
    }

    
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