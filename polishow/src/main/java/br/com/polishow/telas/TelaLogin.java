package br.com.polishow.telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import br.com.polishow.modelo.Usuario;
import br.com.polishow.persistencia.DAO;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);
        

    JPanel painel = new JPanel() {
    private final Image img = new ImageIcon("polishow/src/main/imagens/telalogin.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };
        painel.setLayout(null);


        ImageIcon setaIcon = new ImageIcon("polishow/src/main/imagens/arrow-small-left.png");
        Image setaImage = setaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setaIcon = new ImageIcon(setaImage);

        // botão com imagem de seta
        JButton voltarButton = new JButton(setaIcon);
        voltarButton.setBounds(20, 20, 40, 40);
        voltarButton.setFocusPainted(false);
        voltarButton.setContentAreaFilled(false); 
        voltarButton.setBorderPainted(false); 
        voltarButton.setOpaque(false); 
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ação do botão
        voltarButton.addActionListener(e -> {
            dispose();
            new br.com.polishow.telas.TelaInicio();
        });

        painel.add(voltarButton);
 
        // Campo de Email
        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(360, 204, 250, 50);
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        campoEmail.setBackground(new Color(187, 45, 57));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);
        painel.add(campoEmail);

        // senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(360, 310, 200, 50);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setBackground(new Color(219, 151, 28));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);
        painel.add(campoSenha);

        // mostrar/ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(565, 312, 65, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(219, 151, 28));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
        toggleSenhaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        // botão entrar
        JButton botaoEntrar = new JButton("ENTRAR");
        botaoEntrar.setBounds(394, 408, 150, 45);
        estilizarBotaoTransparente(botaoEntrar);
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 25));
        botaoEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEntrar.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText().trim();
                String senha = new String(campoSenha.getPassword()).trim();

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (email.endsWith("@p4ed.com")) {
                    try {
                        var u = new Usuario(email, senha);
                        var dao = new DAO();
                        if (dao.existeLogin(u)) {
                            var dt = new TelaInicioAluno();
                            dt.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário/senha inválidos");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (email.endsWith("@sistemapoliedro.com.br")) {
                    try {
                        var u = new Usuario(email, senha);
                        var dao = new DAO();
                        if (dao.existeLogin(u)) {
                            var dt = new TelaInicialProfessor();
                            dt.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário/senha inválidos");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        painel.add(botaoEntrar);

        // botão sair

        JButton botaoSair = new JButton("SAIR");
        botaoSair.setBounds(30, 524, 150, 50);
        estilizarBotaoTransparente(botaoSair);
        botaoSair.setFont(new Font("Arial", Font.BOLD, 26));
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSair.addActionListener(e -> System.exit(0));
        painel.add(botaoSair);

        // botão criar 
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
        dispose(); 
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
