import java.awt.*;
import javax.swing.*;

public class TelaInicio extends JFrame {
    public TelaInicio() {
        setTitle("Polishow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(960, 640);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null);

        int screenWidth = 960;
        int screenHeight = 640;

        // Painel com imagem de fundo
        JPanel fundo = new JPanel() {
            ImageIcon bg = new ImageIcon("C:\\Users\\Luiza Gomes\\OneDrive\\Documentos\\GitHub\\Projeto-Integrador-\\PI_codigo\\imagens\\TelaFundoHD.png");
            Image img = bg.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fundo.setBounds(0, 0, screenWidth, screenHeight);
        fundo.setLayout(null);
        add(fundo);

        // Título arredondado
        JLabel titulo = new JLabel("POLISHOW", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(20, 30, 80));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
            }
        };
        titulo.setFont(new Font("Serif", Font.BOLD, 48));
        titulo.setForeground(new Color(219, 151, 28));
        titulo.setOpaque(false);
        titulo.setBounds(screenWidth / 2 - 200, 100, 400, 100);
        fundo.add(titulo);

        // Logo
        ImageIcon logo = new ImageIcon("C:\\Users\\Luiza Gomes\\OneDrive\\Documentos\\GitHub\\Projeto-Integrador-\\PI_codigo\\imagens\\7 1 (1).png");
        Image imgLogo = logo.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(imgLogo);
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(screenWidth / 2 - 350, screenHeight / 2 - 50, 400, 155);
        fundo.add(logoLabel);

        // Botão Iniciar (arredondado)
        RoundedButton jogarBtn = new RoundedButton("INICIAR", new Color(219, 151, 28));
        jogarBtn.setBounds(screenWidth / 2 + 70, screenHeight / 2 - 50, 200, 90);
        fundo.add(jogarBtn);

        // Botão Sair (arredondado)
        RoundedButton sairBtn = new RoundedButton("SAIR", new Color(200, 50, 50));
        sairBtn.setBounds(screenWidth / 2 + 70, screenHeight / 2 + 50, 200, 90);
        fundo.add(sairBtn);

        // Ações dos botões
        jogarBtn.addActionListener(e -> {
            new TelaLogin(); // substitua por sua classe real
            setVisible(false);
        });

        sairBtn.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }

    // Classe interna para botão arredondado com fundo colorido
    class RoundedButton extends JButton {
        private final Color backgroundColor;

        public RoundedButton(String text, Color backgroundColor) {
            super(text);
            this.backgroundColor = backgroundColor;
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Serif", Font.BOLD, 30));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

            FontMetrics fm = g2.getFontMetrics();
            int stringWidth = fm.stringWidth(getText());
            int stringHeight = fm.getAscent();
            g2.setColor(getForeground());
            g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 5);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        new TelaInicio();
    }
}
