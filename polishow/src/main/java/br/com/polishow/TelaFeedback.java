package br.com.polishow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TelaFeedback extends JPanel {

    private Image backgroundImage;

    // Dados de exemplo
    private String materia = "Matemática";
    private int totalQuestoes = 10;
    private int acertos = 8;
    private String dinheiroGanho = "R$ 800,00";

    public TelaFeedback() {
        String caminhoImagem = "polishow\\\\src\\\\main\\\\imagens\\Telapontuaçãojpg (1).png";

        if (!new File(caminhoImagem).exists()) {
            JOptionPane.showMessageDialog(null, "Imagem de fundo não encontrada:\n" + caminhoImagem);
        } else {
            backgroundImage = new ImageIcon(caminhoImagem).getImage();
            setPreferredSize(new Dimension(960, 640)); // resolução fixa
        }

        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem redimensionada para 960x640
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 960, 640, this);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 22));

            // Coordenadas ajustadas para o tamanho menor da tela
            g.drawString(materia, 400, 140);
            g.drawString(String.valueOf(totalQuestoes), 440, 220);
            g.drawString(String.valueOf(acertos), 450, 300);
            g.drawString(dinheiroGanho, 400, 380);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pontuação Final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 640);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Painel de fundo
        TelaFeedback painel = new TelaFeedback();

        // Botão customizado
        JButton botao = new JButton("Voltar ao Menu");
        botao.setBounds(700, 540, 200, 45);
        botao.setBackground(new Color(0, 120, 215));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Voltando ao menu...");
            }
        });

        // Usando JLayeredPane para sobreposição
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(960, 640));

        painel.setBounds(0, 0, 960, 640);
        botao.setBounds(700, 540, 200, 45);

        layeredPane.add(painel, Integer.valueOf(0));
        layeredPane.add(botao, Integer.valueOf(1));

        frame.setContentPane(layeredPane);
        frame.setVisible(true);
    }
}
