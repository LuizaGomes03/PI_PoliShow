import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setUndecorated(true); // Remova a barra de título
        configurarJanela();
        JPanel fundo = criarPainelDeFundo();
        add(fundo);

        JPanel painelLogin = criarPainelLogin();
        fundo.add(painelLogin);

        JButton criarConta = criarBotaoCriarConta();
        fundo.add(criarConta);

        JLabel logoLabel = criarLogo();
        fundo.add(logoLabel);

        JButton voltar = criarBotaoVoltar();
        fundo.add(voltar);

        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Login Poliedro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366,768 );
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private JPanel criarPainelDeFundo() {
        int screenWidth = 1366;
        int screenHeight = 768;

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
        return fundo;
    }

    private JPanel criarPainelLogin() {
        int panelWidth = 340;
        int panelHeight = 270;
        int screenWidth = 1366;
        int screenHeight = 768;
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2 - 80;

        JPanel panel = new JPanel(null);
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);
        panel.setBackground(new Color(0, 0, 128, 220));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(120, 20, 120, 30);
        panel.add(title);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(40, 60, 100, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(40, 90, 260, 40);
        estilizarCampoTexto(emailField, new Color(189, 46, 46));
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setBounds(40, 140, 100, 30);
        panel.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(40, 170, 260, 40);
        estilizarCampoTexto(senhaField, new Color(237, 168, 33));
        panel.add(senhaField);

        JButton entrar = new JButton("Entrar");
        entrar.setBounds(110, 225, 120, 40);
        estilizarBotao(entrar, new Color(0, 76, 255));
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Lógica de autenticação pode ser adicionada aqui
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(entrar);

        return panel;
    }

    private JButton criarBotaoCriarConta() {
        JButton criarConta = new JButton("Criar conta");
        criarConta.setSize(140, 40);
        // Ajuste a posição vertical (reduza o valor de Y para mover o botão para cima)
        criarConta.setLocation(1300 - criarConta.getWidth() - 20, 768 - criarConta.getHeight() - 80); // Alterado de -20 para -80
        estilizarBotao(criarConta, new Color(0, 76, 255));
        criarConta.addActionListener(e -> JOptionPane.showMessageDialog(null, "Tela de criação de conta ainda não implementada."));
        return criarConta; //GUSTAVO DEVE IMPLEMENTAR A TELA DE CRIAÇÃO DE CONTA
    }

    private JLabel criarLogo() {
        ImageIcon logo = new ImageIcon("C:\\Users\\Luiza Gomes\\OneDrive\\Documentos\\GitHub\\Projeto-Integrador-\\PI_codigo\\imagens\\7 1 (1).png");
        // Aumentar o tamanho do logo
        Image imgLogo = logo.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH); // Alterado de 200x70 para 250x90
        logo = new ImageIcon(imgLogo);
        JLabel logoLabel = new JLabel(logo);
        // Subir o logo ajustando a coordenada Y
        int logoX = (1366 - 250) / 2; // Ajustado para o novo tamanho do logo
        int logoY = 768 - 150; // Subiu o logo (alterado de 768 - 100 para 768 - 150)
        logoLabel.setBounds(logoX, logoY, 250, 90); // Ajustado para o novo tamanho
        return logoLabel;
    }

    private JButton criarBotaoVoltar() {
    ImageIcon iconBack = new ImageIcon("C:\\Users\\Luiza Gomes\\OneDrive\\Documentos\\GitHub\\Projeto-Integrador-\\PI_codigo\\imagens\\arrow_back_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.png");
    JButton voltar = new JButton(iconBack);
    voltar.setBounds(20, 20, 30, 30);
    voltar.setBackground(null);
    voltar.setBorderPainted(false);
    voltar.setFocusPainted(false);
    voltar.setContentAreaFilled(false);
    voltar.addActionListener(e -> dispose()); // Fecha a janela atual
    return voltar;
}

    private void estilizarCampoTexto(JTextComponent campo, Color corFundo) {
        campo.setBackground(corFundo);
        campo.setForeground(Color.WHITE);
        campo.setFont(new Font("SansSerif", Font.BOLD, 16));
        campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    private void estilizarBotao(JButton botao, Color corFundo) {
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setFocusPainted(false);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}