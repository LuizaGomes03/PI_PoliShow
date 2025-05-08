import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Polishow extends JFrame {

    public Polishow() {
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximiza a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Caminho da imagem de fundo
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\25.00405-2\\Downloads\\PI_codigo\\PI_codigo\\imagens\\image 16.png");
        JLabel background = new JLabel(backgroundIcon) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = backgroundIcon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  // Ajusta a imagem para cobrir toda a tela
            }
        };
        background.setLayout(null); // Layout nulo para permitir o posicionamento livre de componentes
        setContentPane(background);

        int largura = Toolkit.getDefaultToolkit().getScreenSize().width;
        int altura = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Painel de login
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds((largura - 400) / 2, (altura - 350) / 2, 400, 350);
        panel.setBackground(new Color(10, 20, 80, 220)); // azul com transparência
        panel.setBorder(new RoundBorder(30));
        background.add(panel);

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 20, 400, 40);
        panel.add(title);

        // Campo de email com placeholder
        JTextField emailField = new JTextField("Email");
        emailField.setBounds(70, 80, 260, 40);
        emailField.setFont(new Font("Arial", Font.BOLD, 16));
        emailField.setBackground(new Color(180, 30, 30));
        emailField.setForeground(new Color(180, 30, 30));  // A cor do texto "Email"
        emailField.setCaretColor(Color.WHITE);  // A cor do cursor será branca
        emailField.setBorder(new RoundBorder(20));

        // Adicionando FocusListener para remover/colocar o texto placeholder
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.WHITE);  // Garante que a cor do texto seja branca ao digitar
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Email");
                    emailField.setForeground(new Color(180, 30, 30));  // Cor original para o texto placeholder
                }
            }
        });

        panel.add(emailField);

        JPasswordField senhaField = new JPasswordField("Senha");
        senhaField.setBounds(70, 140, 260, 40);
        senhaField.setFont(new Font("Arial", Font.BOLD, 16));
        senhaField.setBackground(new Color(230, 170, 40));
        senhaField.setForeground(Color.BLACK);
        senhaField.setCaretColor(Color.BLACK);
        senhaField.setBorder(new RoundBorder(20));
        panel.add(senhaField);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(150, 210, 100, 40);
        btnEntrar.setBackground(new Color(30, 100, 255));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(new RoundBorder(20));
        panel.add(btnEntrar);

        btnEntrar.addActionListener(e -> {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            if (!email.isEmpty() && !senha.isEmpty()) {
                // Verifica se o e-mail contém "@professor" ou "@aluno.com"
                if (email.endsWith("@professor.com")) {
                    JOptionPane.showMessageDialog(this, "Bem-vindo professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new TelaPrincipal();
                    dispose();
                } else if (email.endsWith("@aluno.com")) {
                    JOptionPane.showMessageDialog(this, "Bem-vindo aluno!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new TelaPrincipal();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "E-mail não reconhecido. Use um e-mail válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha e-mail e senha.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botão Criar Conta no canto direito
        JButton btnCriar = new JButton("Criar conta");
        btnCriar.setBounds(largura - 150, altura - 70, 120, 30); // Posiciona no canto direito inferior
        btnCriar.setBackground(new Color(30, 100, 255));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCriar.setFocusPainted(false);
        btnCriar.setBorder(new RoundBorder(20));
        background.add(btnCriar);

        // Novo botão "Esqueci a senha"
        JButton btnEsqueciSenha = new JButton("Criar Conta");
        btnEsqueciSenha.setBounds(largura - 170, altura - 120, 160, 30);  // Posiciona no canto inferior direito, acima do botão "Criar conta"
        btnEsqueciSenha.setBackground(new Color(30, 100, 255));
        btnEsqueciSenha.setForeground(Color.WHITE);
        btnEsqueciSenha.setFont(new Font("Arial", Font.BOLD, 14));
        btnEsqueciSenha.setFocusPainted(false);
        btnEsqueciSenha.setBorder(new RoundBorder(20));
        background.add(btnEsqueciSenha);

        btnEsqueciSenha.addActionListener(e -> {
            // Ação do botão "Esqueci a senha"
            JOptionPane.showMessageDialog(this, "Criar Conta", "Criar Conta", JOptionPane.INFORMATION_MESSAGE);
            // Aqui você pode colocar a lógica de recuperação de senha ou redirecionar para outra tela
            
        });

        JLabel logo = new JLabel("📚 Poliedro Educação", SwingConstants.CENTER);
        logo.setBounds((largura - 200) / 2, altura - 70, 200, 30);
        logo.setFont(new Font("Arial", Font.BOLD, 16));
        logo.setForeground(Color.WHITE);
        background.add(logo);

        setVisible(true);
    }

    static class RoundBorder extends AbstractBorder {
        private final int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(255, 255, 255, 80));
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(10, 10, 10, 10);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(10, 10, 10, 10);
            return insets;
        }
    }

    public static class TelaPrincipal extends JFrame {
        public TelaPrincipal() {
            setTitle("Bem-vindo!");
            setSize(600, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setBackground(new Color(20, 100, 60));

            JLabel msg = new JLabel("Você entrou com sucesso!", SwingConstants.CENTER);
            msg.setFont(new Font("Arial", Font.BOLD, 24));
            msg.setForeground(Color.WHITE);
            add(msg);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Polishow();
    }
}
