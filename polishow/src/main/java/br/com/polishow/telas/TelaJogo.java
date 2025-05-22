package br.com.polishow.telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class TelaJogo extends JFrame {

    private boolean ajudaUsada = false;
    private List<JButton> alternativasBtns = new ArrayList<>();
    private String respostaCorreta = "B) 56"; // exemplo

    @SuppressWarnings("deprecation")
    public TelaJogo() {
        setTitle("Tela Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/TelaJogo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        // Botão de Sair
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
                dispose(); // fecha a tela atual

                TelaPontuacaoFinal telaFinal = new TelaPontuacaoFinal();
                telaFinal.setVisible(true);

                // Exemplo: atualizando os dados do placar
                telaFinal.atualizarDados("Matemática", 12, 8, 800.00);
            }
        });
        painelFundo.add(btnSair);

        // Botão Eliminar
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
                    btn.setBackground(Color.GRAY);
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

        // Botão Pular
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
                // lógica para pular a questão aqui
            }
        });
        painelFundo.add(btnPular);

        // Pergunta
        JLabel lblPergunta = new JLabel("<html><b>Quanto é 7 x 8?</b></html>");
        lblPergunta.setBounds(180, 120, 400, 40);
        lblPergunta.setFont(new Font("Arial", Font.BOLD, 24));
        lblPergunta.setForeground(Color.WHITE);
        painelFundo.add(lblPergunta);

        // Alternativas
        String[] alternativas = {
            "A) 54", "B) 56", "C) 58", "D) 48", "E) 64"
        };
        int y = 190;
        for (String alternativa : alternativas) {
            JButton btnAlternativa = new JButton(alternativa);
            btnAlternativa.setBounds(180, y, 580, 40);
            btnAlternativa.setFont(new Font("Arial", Font.PLAIN, 18));
            btnAlternativa.setFocusPainted(false);
            btnAlternativa.setHorizontalAlignment(SwingConstants.LEFT);
            btnAlternativa.setMargin(new Insets(0, 15, 0, 0));
            btnAlternativa.setBackground(new Color(18, 66, 177));
            btnAlternativa.setForeground(Color.WHITE);
            btnAlternativa.setOpaque(true);
            btnAlternativa.setBorderPainted(false);
            btnAlternativa.setCursor(new Cursor(HAND_CURSOR));
            painelFundo.add(btnAlternativa);
            alternativasBtns.add(btnAlternativa);
            y += 60;
        }

        setContentPane(painelFundo);
        setVisible(true);
    }

    // Borda arredondada
    static class RoundBorder extends AbstractBorder {
        private int radius;
        public RoundBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
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
