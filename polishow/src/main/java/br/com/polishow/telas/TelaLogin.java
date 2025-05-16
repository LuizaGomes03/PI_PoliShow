package br.com.polishow.telas;
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
        campoEmail.setBounds(360, 204, 250, 50);
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        campoEmail.setBackground(new Color(187, 45, 57));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);
        painel.add(campoEmail);

        // Campo de Senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(360, 310, 200, 50);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setBackground(new Color(219, 151, 28));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);
        painel.add(campoSenha);

       // Mostrar/Ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(565, 312, 65, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(219, 151, 28));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
        painel.add(toggleSenhaBtn);

        toggleSenhaBtn.addActionListener(e -> {
            if (campoSenha.getEchoChar() != (char) 0) {
                campoSenha.setEchoChar((char) 0);
                toggleSenhaBtn.setText("Ocultar");
            } else {
                campoSenha.setEchoChar('•');
                toggleSenhaBtn.setText("Mostrar");
            }
        });

        // Botão Entrar
    JButton botaoEntrar = new JButton("ENTRAR");
    botaoEntrar.setBounds(393, 407, 150, 45);
    estilizarBotaoTransparente(botaoEntrar);
    botaoEntrar.setFont(new Font("Arial", Font.BOLD, 25));
    botaoEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    botaoEntrar.addActionListener((ActionListener) new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = campoEmail.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (email.endsWith("@p4ed.com")) {
            
            new TelaInicioAluno(); // Abre a TelaInicioAluno
            dispose(); // Fecha a tela de login
        } else if (email.endsWith("@sistemapoliedro.com.br")) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, Professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Aqui você pode redirecionar para a tela do professor, se necessário
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

});
painel.add(botaoEntrar);

        // Botão Sair
       
        JButton botaoSair = new JButton("SAIR");
        botaoSair.setBounds(30, 524, 150, 50);
        estilizarBotaoTransparente(botaoSair);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 26));
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addActionListener(e -> System.exit(0));
        painel.add(botaoSair);

        // Botão Criar Conta no canto inferior direito
        JButton botaoCriarConta = new JButton("CRIAR CONTA");
        botaoCriarConta.setBounds(741, 513, 190, 70);
        estilizarBotaoTransparente(botaoCriarConta);
        botaoCriarConta.setFont(new Font("Arial", Font.BOLD, 19));
        botaoCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCriarConta.addActionListener(e -> acaoBotaoCriarConta());
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

    private void acaoBotaoCriarConta() {
        new TelaCadastro();
        dispose(); // Fecha a tela de login
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
