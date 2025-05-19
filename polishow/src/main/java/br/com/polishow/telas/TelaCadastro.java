package br.com.polishow.telas;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import br.com.polishow.modelo.Usuario;
import br.com.polishow.persistencia.DAO;

public class TelaCadastro extends JFrame implements java.awt.event.ActionListener {

    private JTextField txtEmail;
    private JPasswordField senhaPasswordField;
    private JTextField txtNomeProfessor;

    public TelaCadastro() {
        setTitle("Cadastrar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel com imagem de fundo
        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/Tela de cadastro professor (1).png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        txtNomeProfessor = new JTextField();
        txtNomeProfessor.setBackground(new Color(18, 66, 177));
        txtNomeProfessor.setForeground(Color.WHITE);
        txtNomeProfessor.setFont(new Font("SansSerif", Font.BOLD, 17));
        txtNomeProfessor.setBounds(317, 215, 320, 40);
        txtNomeProfessor.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(txtNomeProfessor);

        txtEmail = new JTextField();
        txtEmail.setBackground(new Color(186, 49, 49));
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setFont(new Font("SansSerif", Font.BOLD, 17));
        txtEmail.setBounds(317, 308, 320, 40);
        txtEmail.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(txtEmail);

        senhaPasswordField = new JPasswordField();
        senhaPasswordField.setName("senhaPasswordField");
        senhaPasswordField.setBackground(new Color(219, 151, 28));
        senhaPasswordField.setForeground(Color.WHITE);
        senhaPasswordField.setFont(new Font("SansSerif", Font.BOLD, 17));
        senhaPasswordField.setBounds(317, 395, 263, 40);
        senhaPasswordField.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(senhaPasswordField);

        // Botão Voltar
        JButton voltarButton = new JButton();
        voltarButton.setBounds(15, 15, 35, 40);
        voltarButton.setBorder(BorderFactory.createEmptyBorder());
        voltarButton.setContentAreaFilled(false); // Torna o botão transparente
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse
        painelFundo.add(voltarButton);

        voltarButton.addActionListener(e -> {
            new TelaLogin();
            dispose();
        });

        // Mostrar/Ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(580, 395, 60, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(219, 151, 28));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
        toggleSenhaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse
        toggleSenhaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toggleSenhaBtn.setBackground(new Color(255, 180, 50)); // Cor de destaque
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toggleSenhaBtn.setBackground(new Color(219, 151, 28)); // Cor original
            }
        });
        painelFundo.add(toggleSenhaBtn);

        toggleSenhaBtn.addActionListener(e -> {
            if (senhaPasswordField.getEchoChar() != (char) 0) {
                senhaPasswordField.setEchoChar((char) 0);
                toggleSenhaBtn.setText("Ocultar");
            } else {
                senhaPasswordField.setEchoChar('•');
                toggleSenhaBtn.setText("Mostrar");
            }
        });

        // Botão Criar
        JButton btnCriar = new JButton("CRIAR");
        btnCriar.setBounds(397, 490, 150, 40);
        btnCriar.setFont(new Font("SansSerif", Font.BOLD, 23));
        btnCriar.setBackground(new Color(18, 66, 177));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.setBorder(BorderFactory.createEmptyBorder());
        btnCriar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse
        painelFundo.add(btnCriar);

        btnCriar.addActionListener(this);

        add(painelFundo);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = txtNomeProfessor.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(senhaPasswordField.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (email.endsWith("@p4ed.com")) {
            JOptionPane.showMessageDialog(null, "Somente professores podem realizar cadastro.", "Erro", JOptionPane.WARNING_MESSAGE);
        } else if (email.endsWith("@sistemapoliedro.com.br")) {
            try{
                var u = new Usuario(nome, email, senha, 1);
                var dao = new DAO();
                dao.criarCadastroAdm(u);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);}
            JOptionPane.showMessageDialog(null, "Bem-vindo, Professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro());
    }

}