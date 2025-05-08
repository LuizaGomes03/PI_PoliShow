import java.awt.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela

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
        JLabel titulo = new JLabel("LOGIN", SwingConstants.CENTER) {
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
        logoLabel.setBounds(screenWidth / 2 - 350, screenHeight / 2 - 200, 300, 100);
        fundo.add(logoLabel);

        // Campo de usuário
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setBounds(screenWidth / 2 - 180, screenHeight / 2 - 50, 100, 30);
        fundo.add(usuarioLabel);

        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(screenWidth / 2 - 80, screenHeight / 2 - 50, 200, 30);
        fundo.add(usuarioField);

        // Campo de senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setBounds(screenWidth / 2 - 180, screenHeight / 2, 100, 30);
        fundo.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(screenWidth / 2 - 80, screenHeight / 2, 200, 30);
        fundo.add(senhaField);

        // Botão de login
        JButton loginBtn = new JButton("Entrar");
        loginBtn.setBounds(screenWidth / 2 - 80, screenHeight / 2 + 50, 200, 40);
        loginBtn.setBackground(new Color(40, 80, 200));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        loginBtn.setBorder(new RoundedBorder(30)); // Aplicando bordas arredondadas
        fundo.add(loginBtn);

        // Botão de criar conta
        JButton criarContaBtn = new JButton("Criar Conta");
        criarContaBtn.setBounds(screenWidth - 250, screenHeight - 100, 180, 40);
        criarContaBtn.setBackground(new Color(232, 176, 23)); // Cor do botão
        criarContaBtn.setForeground(Color.WHITE);
        criarContaBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        criarContaBtn.setBorder(new RoundedBorder(30)); // Bordas arredondadas
        fundo.add(criarContaBtn);

        // Botão de sair com imagem
        ImageIcon sairIcon = new ImageIcon("C:/Users/Luiza Gomes/OneDrive/Documentos/GitHub/Projeto-Integrador-/PI_codigo/imagens/arrow_back_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png");
        JButton sairBtn = new JButton(sairIcon);
        sairBtn.setBounds(20, screenHeight - 100, 50, 50); // Ajuste o tamanho da imagem se necessário
        sairBtn.setBackground(new Color(255, 255, 255, 0)); // Fundo transparente
        sairBtn.setBorder(null); // Remove a borda padrão
        fundo.add(sairBtn);

        // Ação do botão de login
        loginBtn.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            // Aqui você pode adicionar a lógica de autenticação
            if (usuario.equals("admin") && senha.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                // Abrir a próxima tela ou ação após o login
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos");
            }
        });

        // Ação do botão de criar conta
        criarContaBtn.addActionListener(e -> {
            // Lógica para abrir a tela de criação de conta
            JOptionPane.showMessageDialog(this, "Tela de criação de conta");
            // Aqui você pode adicionar a lógica para abrir uma nova tela ou formulário
        });

        // Ação do botão de sair
        sairBtn.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0); // Encerra a aplicação
            }
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
            return new Insets(radius + 1, radius + 1, radius + 2, radius); // Definindo margens
        }
    }

    public static void main(String[] args) {
        new TelaLogin(); // Exibe a tela de login em tela cheia
    }
}
