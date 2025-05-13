import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;

public class TelaCadastroAluno {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroAluno().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Imagem de fundo
        ImageIcon backgroundIcon = new ImageIcon("PI_codigo/imagens/telaCadastro.png");
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(
            backgroundIcon.getIconWidth(),
            backgroundIcon.getIconHeight()
        ));

        // Frame
        JFrame frame = new JFrame("Cadastrar aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Campos e Botões

        // Nome
        JTextField nomeTextField = new JTextField();
        nomeTextField.setName("nomeTextField");
        nomeTextField.setBackground(new Color(186,49,49)); 
        nomeTextField.setForeground(Color.WHITE);
        nomeTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        nomeTextField.setBounds(400, 265, 470, 50);
        nomeTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(nomeTextField);

        // ComboBox sem bordas e com seta colorida
        JComboBox<String> serieComboBox = new JComboBox<>(
            new String[] { "Selecione a série", "1ª Série", "2ª Série", "3ª Série" }
        );
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(220,150,34)); // laranja
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        serieComboBox.setBounds(400, 395, 470, 50);
        serieComboBox.setBorder(null);
        serieComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                // cria uma seta apontando para baixo, usando BasicArrowButton
                BasicArrowButton arrow = new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    new Color(220,150,34),   // background do botão (mesma cor do combo)
                    new Color(220,150,34),   // shadow
                    Color.WHITE,             // cor da seta
                    new Color(220,150,34)    // highlight
                );
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

        // Email
        JTextField emailTextField = new JTextField();
        emailTextField.setName("emailTextField");
        emailTextField.setBackground(new Color(21,179,192));
        emailTextField.setForeground(Color.WHITE);
        emailTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        emailTextField.setBounds(400, 515, 470, 50);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(emailTextField);

        // Senha
        JPasswordField senhaPasswordField = new JPasswordField();
        senhaPasswordField.setName("senhaPasswordField");
        senhaPasswordField.setBackground(new Color(11,54,175)); 
        senhaPasswordField.setForeground(Color.WHITE);
        senhaPasswordField.setFont(new Font("SansSerif", Font.BOLD, 20));
        senhaPasswordField.setBounds(400, 635, 420, 50);
        senhaPasswordField.setBorder(BorderFactory.createEmptyBorder());
        background.add(senhaPasswordField);

        //Mostrar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(820, 635, 60, 50);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(11,54,175));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
        background.add(toggleSenhaBtn);

        toggleSenhaBtn.addActionListener(_ -> {
            if (senhaPasswordField.getEchoChar() != (char)0) {
                senhaPasswordField.setEchoChar((char)0);
                toggleSenhaBtn.setText("Ocultar");
            } else {
                senhaPasswordField.setEchoChar('•');
                toggleSenhaBtn.setText("Mostrar");
            }
        });

        // Botão Cadastrar
        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setName("cadastrarButton");
        cadastrarButton.setBackground(new Color(13,66,149));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBounds(520, 765, 250, 50);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(cadastrarButton);

        // Exibe a janela
        frame.setVisible(true);
    }
}
