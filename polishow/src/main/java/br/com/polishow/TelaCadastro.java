package br.com.polishow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame implements java.awt.event.ActionListener {

    private JTextField txtEmail;
    private JPasswordField senhaPasswordField;

    public TelaCadastro() {
        setTitle("Cadastrar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel com imagem de fundo
        JPanel painelFundo = new JPanel() {
            Image imagem = new ImageIcon("C:\\Users\\25.01250-1\\Documents\\GitHub\\Projeto-Integrador-\\polishow\\src\\main\\imagens\\Tela cadastrar professor.png").getImage();

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

        //Botão Voltar
        JButton voltarButton = new JButton();
        voltarButton.setBounds(20, 15, 40, 40);
        voltarButton.setBorder(BorderFactory.createEmptyBorder());
        voltarButton.setContentAreaFilled(false); // Torna o botão transparente
        painelFundo.add(voltarButton);

        voltarButton.addActionListener(e -> {
            new TelaLogin();
            dispose();
        });

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
        } else if (email.endsWith("@p4ed.com")) {
            JOptionPane.showMessageDialog(null, "Somente professores podem criar conta.", "Erro", JOptionPane.WARNING_MESSAGE);
        } else if (email.endsWith("@sistemapoliedro.com.br")) {
            new TelaInicialProfessor();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro());
    }
}