package br.com.polishow.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaPontuacaoFinal extends JFrame {

    private JLabel materiaValor;
    private JLabel totalQuestoesValor;
    private JLabel acertosValor;
    private JLabel dinheiroValor;

    public TelaPontuacaoFinal() {
        setTitle("Pontuação Final");
        setSize(960, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel com imagem de fundo
        JPanel painel = new JPanel() {
            ImageIcon background = new ImageIcon("polishow/src/main/imagens/Telapontuaçãojpg (1).png");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);

        // Fonte e cor dos textos
        Font fonte = new Font("SansSerif", Font.BOLD, 20);
        Color corTexto = Color.WHITE;

        // Labels dos valores
        materiaValor = criarLabel("Matemática", 575, 157, fonte, corTexto);
        totalQuestoesValor = criarLabel("12", 610, 247, fonte, corTexto);
        acertosValor = criarLabel("8", 616, 342, fonte, corTexto);
        dinheiroValor = criarLabel("R$ 800,00", 583, 428, fonte, corTexto);

        painel.add(materiaValor);
        painel.add(totalQuestoesValor);
        painel.add(acertosValor);
        painel.add(dinheiroValor);

        // Botão com efeitos de clique e hover
        JButton botao = new JButton("SAIR") {
            private boolean hover = false;
            private boolean pressed = false;

            {

                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // garante o cursor de mão
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        pressed = true;
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        pressed = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color corNormal = new Color(220, 20, 60);
                Color corHover = new Color(255, 60, 80);
                Color corPressed = new Color(180, 0, 40);

                if (pressed) {
                    g2.setColor(corPressed);
                } else if (hover) {
                    g2.setColor(corHover);
                } else {
                    g2.setColor(corNormal);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // sem borda
            }
        };

        botao.setBounds(750, 541, 166, 46);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 24));
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setOpaque(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botao.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Você clicou em Continuar!");
            // Aqui pode chamar outra tela ou lógica do jogo
        });

        painel.add(botao);

        setContentPane(painel);
    }

    // Método para criar labels com estilo
    private JLabel criarLabel(String texto, int x, int y, Font fonte, Color cor) {
        JLabel label = new JLabel(texto);
        label.setBounds(x, y, 300, 30);
        label.setFont(fonte);
        label.setForeground(cor);
        return label;
    }

    // Atualiza os dados dinamicamente
    public void atualizarDados(String materia, int total, int acertos, double dinheiro) {
        materiaValor.setText(materia);
        totalQuestoesValor.setText(String.valueOf(total));
        acertosValor.setText(String.valueOf(acertos));
        dinheiroValor.setText(String.format("R$ %.2f", dinheiro));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPontuacaoFinal tela = new TelaPontuacaoFinal();
            tela.setVisible(true);
        });
    }
}
