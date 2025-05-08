import java.awt.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class TelaInicio extends JFrame {
    public TelaInicio() {
        setTitle("Polishow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLayout(null);

        // Painel com imagem de fundo
        JPanel fundo = new JPanel() {
            ImageIcon bg = new ImageIcon("C:/Users/Luiza Gomes/OneDrive/Documentos/GitHub/Projeto-Integrador-/PI_codigo/imagens/telaFundo.jpg");
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
        titulo.setFont(new Font("SansSerif", Font.BOLD, 48));
        titulo.setForeground(Color.YELLOW);
        titulo.setOpaque(false); // Usamos paintComponent
        titulo.setBounds(screenWidth / 2 - 200, 100, 400, 80);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        fundo.add(titulo);

        // Logo
        ImageIcon logo = new ImageIcon("C:/Users/Luiza Gomes/OneDrive/Documentos/GitHub/Projeto-Integrador-/PI_codigo/imagens/Logo_poliedro.jpg");
        Image imgLogo = logo.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(imgLogo);
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(screenWidth / 2 - 350, screenHeight / 2 - 50, 300, 100);
        fundo.add(logoLabel);

        // Botão Jogar
        JButton jogarBtn = new JButton("JOGAR");
        jogarBtn.setBounds(screenWidth / 2 + 70, screenHeight / 2 - 50, 200, 60);
        jogarBtn.setBackground(new Color(232, 176, 23));
        jogarBtn.setForeground(Color.WHITE);
        jogarBtn.setFont(new Font("SansSerif", Font.BOLD, 22));
        jogarBtn.setBorder(new RoundedBorder(30)); // Aplicando bordas arredondadas
        fundo.add(jogarBtn);

        // Botão Sair
        JButton sairBtn = new JButton("SAIR");
        sairBtn.setBounds(screenWidth / 2 + 70, screenHeight / 2 + 20, 200, 60);
        sairBtn.setBackground(new Color(200, 50, 50));
        sairBtn.setForeground(Color.WHITE);
        sairBtn.setFont(new Font("SansSerif", Font.BOLD, 22));
        sairBtn.setBorder(new RoundedBorder(30)); // Aplicando bordas arredondadas
        fundo.add(sairBtn);

        // Ações dos botões
        jogarBtn.addActionListener(e -> {
            // Criar uma instância de TelaLogin ao clicar em "Jogar"
            new TelaLogin(); // Chama a tela de login
            setVisible(false); // Ocultar a tela inicial
        });

        sairBtn.addActionListener(e -> {
            System.exit(0); // Fecha o programa
        });

        setVisible(true);
    }

    // Classe para bordas arredondadas
    class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(c.getBackground());
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Bordas arredondadas
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius;
            return insets;
        }
    }

    public static void main(String[] args) {
        new TelaInicio();
    }
}
