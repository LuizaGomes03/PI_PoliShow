import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setTitle("Cadastro");
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

        // Painel de cadastro
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds((largura - 400) / 2, (altura - 400) / 2, 400, 400);
        panel.setBackground(new Color(10, 20, 80, 220)); // azul com transparência
        panel.setBorder(new RoundBorder(30));
        background.add(panel);

        JLabel title = new JLabel("Cadastro", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 20, 400, 40);
        panel.add(title);

        // Campo de nome
        JTextField nomeField = new JTextField("Nome");
        nomeField.setBounds(70, 80, 260, 40);
        nomeField.setFont(new Font("Arial", Font.BOLD, 16));
        nomeField.setBackground(new Color(180, 30, 30));
        nomeField.setForeground(new Color(180, 30, 30));  // Cor do texto "Nome"
        nomeField.setCaretColor(Color.WHITE);  // A cor do cursor será branca
        nomeField.setBorder(new RoundBorder(20));

        // Adicionando FocusListener para remover/colocar o texto placeholder
        nomeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeField.getText().equals("Nome")) {
                    nomeField.setText("");
                    nomeField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeField.getText().isEmpty()) {
                    nomeField.setText("Nome");
                    nomeField.setForeground(new Color(180, 30, 30));
                }
            }
        });

        panel.add(nomeField);

        // Campo de email com placeholder
        JTextField emailField = new JTextField("Email");
        emailField.setBounds(70, 140, 260, 40);
        emailField.setFont(new Font("Arial", Font.BOLD, 16));
        emailField.setBackground(new Color(180, 30, 30));
        emailField.setForeground(new Color(180, 30, 30));  // Cor do texto "Email"
        emailField.setCaretColor(Color.WHITE);  // A cor do cursor será branca
        emailField.setBorder(new RoundBorder(20));

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

        // Campo de senha com placeholder
        JPasswordField senhaField = new JPasswordField("Senha");
        senhaField.setBounds(70, 200, 260, 40);
        senhaField.setFont(new Font("Arial", Font.BOLD, 16));
        senhaField.setBackground(new Color(230, 170, 40));
        senhaField.setForeground(Color.BLACK);
        senhaField.setCaretColor(Color.BLACK);
        senhaField.setBorder(new RoundBorder(20));
        panel.add(senhaField);

        // Campo de confirmação de senha
        JPasswordField confirmSenhaField = new JPasswordField("Confirmar Senha");
        confirmSenhaField.setBounds(70, 260, 260, 40);
        confirmSenhaField.setFont(new Font("Arial", Font.BOLD, 16));
        confirmSenhaField.setBackground(new Color(230, 170, 40));
        confirmSenhaField.setForeground(Color.BLACK);
        confirmSenhaField.setCaretColor(Color.BLACK);
        confirmSenhaField.setBorder(new RoundBorder(20));
        panel.add(confirmSenhaField);

        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 320, 100, 40);
        btnCadastrar.setBackground(new Color(30, 100, 255));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorder(new RoundBorder(20));
        panel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());
            String confirmSenha = new String(confirmSenhaField.getPassword());

            if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !confirmSenha.isEmpty()) {
                if (senha.equals(confirmSenha)) {
                    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Aqui você pode adicionar a lógica para armazenar os dados no banco de dados ou seguir com o fluxo.
                    new TelaPrincipal(); // Exibe a tela principal
                    dispose(); // Fecha a tela de cadastro
                } else {
                    JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

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

            JLabel msg = new JLabel("Você se cadastrou com sucesso!", SwingConstants.CENTER);
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
