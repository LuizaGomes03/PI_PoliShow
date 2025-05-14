package br.com.polishow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);
        setUndecorated(true); // Remove barra superior


        JPanel painel = new JPanel() {
            ImageIcon imagem = new ImageIcon("polishow\\\\src\\\\main\\\\imagens\\telalogin.png");
            Image img = imagem.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);

        // Campo de Email
        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(370, 218, 250, 50);
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        campoEmail.setBackground(new Color(187, 45, 57));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);
        painel.add(campoEmail);

        // Campo de Senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(370, 332, 250, 50);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setBackground(new Color(219, 151, 28));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);
        painel.add(campoSenha);

       
        // Botão Entrar
        // Botão Entrar
    JButton botaoEntrar = new JButton("ENTRAR");
    botaoEntrar.setBounds(80, 420, 790, 70);
    estilizarBotaoTransparente(botaoEntrar);
    botaoEntrar.setFont(new Font("Arial", Font.BOLD, 28));
    botaoEntrar.addActionListener((ActionListener) new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = campoEmail.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (email.contains("@aluno")) {
            
            new TelaInicioAluno(); // Abre a TelaInicioAluno
            dispose(); // Fecha a tela de login
        } else if (email.contains("@professor")) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, Professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Aqui você pode redirecionar para a tela do professor, se necessário
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido. Use um email com @aluno ou @professor.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

});
painel.add(botaoEntrar);

        // Botão Sair
       
        JButton botaoSair = new JButton("SAIR");
        // Ajustado para mover mais para cima (Y: 550) e para a esquerda (X: 20)
        botaoSair.setBounds(10, 556, 195, 50);
        estilizarBotaoTransparente(botaoSair);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 26));
        botaoSair.addActionListener(e -> System.exit(0));
        painel.add(botaoSair);

        // Botão Criar Conta no canto inferior direito
        JButton botaoCriarConta = new JButton("CRIAR CONTA");
        botaoCriarConta.setBounds(730, 545, 240, 70);
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
        botao.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
