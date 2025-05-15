package br.com.polishow;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaCadastro extends JFrame implements java.awt.event.ActionListener {

    private JTextField txtEmail;
    private JPasswordField senhaPasswordField;

    public TelaCadastro() {
        setTitle("Cadastrar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);

        // Painel com imagem de fundo
        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/Tela cadastrar professor.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        txtEmail = new JTextField();
        txtEmail.setBackground(new Color(186, 49, 49));
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setFont(new Font("SansSerif", Font.BOLD, 17));
        txtEmail.setBounds(320, 245, 320, 40);
        txtEmail.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(txtEmail);

        senhaPasswordField = new JPasswordField();
        senhaPasswordField.setName("senhaPasswordField");
        senhaPasswordField.setBackground(new Color(219, 151, 28));
        senhaPasswordField.setForeground(Color.WHITE);
        senhaPasswordField.setFont(new Font("SansSerif", Font.BOLD, 17));
        senhaPasswordField.setBounds(320, 354, 265, 40);
        senhaPasswordField.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(senhaPasswordField);

        // Mostrar/Ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(585, 350, 60, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(219, 151, 28));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
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
        btnCriar.setBounds(400, 455, 150, 40);
        btnCriar.setFont(new Font("SansSerif", Font.BOLD, 23));
        btnCriar.setBackground(new Color(18, 66, 177));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.setBorder(BorderFactory.createEmptyBorder());
        painelFundo.add(btnCriar);

        btnCriar.addActionListener(this);

        add(painelFundo);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = txtEmail.getText().trim();
        String senha = new String(senhaPasswordField.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (email.contains("@p4ed.com")) {
            new TelaInicioAluno(); // Abre a TelaInicioAluno
            dispose(); // Fecha a tela de login
        } else if (email.contains("@sistemapoliedro.com.br")) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, Professor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Aqui você pode redirecionar para a tela do professor, se necessário
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido. Use um email com @aluno ou @professor.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro());
    }
}