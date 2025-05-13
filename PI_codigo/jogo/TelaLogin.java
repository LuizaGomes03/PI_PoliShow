import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        JPanel painel = new JPanel() {
            ImageIcon imagem = new ImageIcon("imagens\\telalogin (1).png");
            Image img = imagem.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null); // Layout absoluto

        // Campo de Email
        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(580, 300, 300, 50);//setBounds(x, y, largura, altura)
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        campoEmail.setBackground(new Color(187, 45, 57));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);
        painel.add(campoEmail);

        // Campo de Senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(590, 470, 305, 50);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setBackground(new Color(219, 151, 28));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);
        painel.add(campoSenha);

            // Botão Entrar
        JButton botaoEntrar = new JButton("ENTRAR");
        botaoEntrar.setBounds(620, 570, 300, 90); // Movido mais para baixo
        botaoEntrar.setContentAreaFilled(false); // Torna o botão transparente
        botaoEntrar.setBorderPainted(false); // Remove a borda
        botaoEntrar.setFocusPainted(false); // Remove o foco visual
        botaoEntrar.setForeground(Color.WHITE); // Define a cor do texto
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 30));
        botaoEntrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = campoEmail.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (email.contains("@aluno")) {
                JOptionPane.showMessageDialog(null, "Bem-vindo, Aluno!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Aqui você pode redirecionar para a tela inicial do aluno
            } else if (email.contains("@professor")) {
                JOptionPane.showMessageDialog(null, "Bem-vindo, Professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Aqui você pode redirecionar para a tela inicial do professor
            } else {
                JOptionPane.showMessageDialog(null, "Email inválido. Use um email com @aluno ou @professor.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    painel.add(botaoEntrar);
    

        // Botão Sair
        JButton botaoSair = new JButton("SAIR");
        botaoSair.setBounds(100, 760, 130, 50);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 70));
        estilizarBotaoTransparente(botaoSair);
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        painel.add(botaoSair);

        // Botão Criar Conta
        JButton botaoCriarConta = new JButton("CRIAR CONTA");
        botaoCriarConta.setBounds(1150, 690, 420, 190);
        estilizarBotaoTransparente(botaoCriarConta);
        painel.add(botaoCriarConta);

        setContentPane(painel);
        setVisible(true);
    }

    private void estilizarBotaoTransparente(JButton botao) {
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 30));
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
