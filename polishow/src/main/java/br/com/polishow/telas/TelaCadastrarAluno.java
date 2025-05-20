package br.com.polishow.telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.persistencia.DAO;

public class TelaCadastrarAluno {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastrarAluno().createAndShowGUI());
    }

    
    void createAndShowGUI() {
        
        int imageWidth = 960;
        int imageHeight = 640;

        
        ImageIcon originalIcon = new ImageIcon("polishow/src/main/imagens/telaCadastro.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(scaledImage);

        
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(imageWidth, imageHeight));

        
        JFrame frame = new JFrame("Cadastrar aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        
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
            frame.dispose();
            new br.com.polishow.telas.TelaInicialProfessor().TelaInicialProfessor();
        });

        background.add(voltarButton);

        // nome
        JTextField nomeTextField = new JTextField();
        nomeTextField.setName("nomeTextField");
        nomeTextField.setBackground(new Color(186, 49, 49));
        nomeTextField.setForeground(Color.WHITE);
        nomeTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        nomeTextField.setBounds(300, 195, 350, 45);
        nomeTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(nomeTextField);

        // série
        JComboBox<String> serieComboBox = new JComboBox<>(
                new String[] {"1ª Série", "2ª Série", "3ª Série" });
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(220, 150, 34));
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        serieComboBox.setBounds(300, 293, 360, 45);
        serieComboBox.setBorder(null);
        serieComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        serieComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(220, 150, 34),
                        new Color(220, 150, 34),
                        Color.WHITE,
                        new Color(220, 150, 34));
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                return arrow;
            }

            @Override
            public void installListeners() {
                super.installListeners();
                comboBox.setFocusable(false);
            }
        });
        background.add(serieComboBox);

        // email
        JTextField emailTextField = new JTextField();
        emailTextField.setName("emailTextField");
        emailTextField.setBackground(new Color(21, 179, 192));
        emailTextField.setForeground(Color.WHITE);
        emailTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        emailTextField.setBounds(300, 385, 350, 45);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(emailTextField);

        // senha
        JPasswordField senhaPasswordField = new JPasswordField();
        senhaPasswordField.setName("senhaPasswordField");
        senhaPasswordField.setBackground(new Color(11, 54, 165));
        senhaPasswordField.setForeground(Color.WHITE);
        senhaPasswordField.setFont(new Font("SansSerif", Font.BOLD, 20));
        senhaPasswordField.setBounds(300, 475, 300, 45);
        senhaPasswordField.setBorder(BorderFactory.createEmptyBorder());
        background.add(senhaPasswordField);

        // mostrar/ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(600, 475, 60, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(11, 54, 165));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
        toggleSenhaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        background.add(toggleSenhaBtn);

        toggleSenhaBtn.addActionListener(e -> {
            if (senhaPasswordField.getEchoChar() != (char) 0) {
                senhaPasswordField.setEchoChar((char) 0);
                toggleSenhaBtn.setText("Ocultar");
            } else {
                senhaPasswordField.setEchoChar('•');
                toggleSenhaBtn.setText("Mostrar");
            }
        });

        // botão cadastrar
        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setName("cadastrarButton");
        cadastrarButton.setBackground(new Color(11, 65, 175));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBounds(378, 572, 200, 45);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder());
        cadastrarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        background.add(cadastrarButton);

        cadastrarButton.addActionListener(e -> {
            String email = emailTextField.getText().trim();
            String nome = nomeTextField.getText().trim();
            String senha = new String(senhaPasswordField.getPassword()).trim();
            String serie = (String) serieComboBox.getSelectedItem();

            if (email.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Há campos vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // verificação de formato de email
            if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
                JOptionPane.showMessageDialog(frame, "Formato de email inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // domínio permitido
            if (!email.endsWith("@p4ed.com")) {
                JOptionPane.showMessageDialog(frame, "Email inválido. Utilize um email com @p4ed.com", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                var a = new Aluno(nome, email, senha, 0, serie);
                var dao = new DAO();
                dao.criarCadastroAluno(a);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

            
            JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso!");
        });

        // exibe a janela
        frame.setVisible(true);
    }
}
