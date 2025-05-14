import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaCadastroAluno {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroAluno().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Tamanho desejado da imagem
        int imageWidth = 960;
        int imageHeight = 640;

        // Carrega e redimensiona a imagem de fundo
        ImageIcon originalIcon = new ImageIcon("PI_codigo/imagens/telaCadastro.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(scaledImage);

        // Cria o JLabel com imagem redimensionada
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(imageWidth, imageHeight));

        // Frame
        JFrame frame = new JFrame("Cadastrar aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Nome
        JTextField nomeTextField = new JTextField();
        nomeTextField.setName("nomeTextField");
        nomeTextField.setBackground(new Color(186, 49, 49));
        nomeTextField.setForeground(Color.WHITE);
        nomeTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        nomeTextField.setBounds(300, 195, 350, 45);
        nomeTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(nomeTextField);

        // ComboBox sem bordas e com seta colorida
        JComboBox<String> serieComboBox = new JComboBox<>(
                new String[]{"Selecione a série", "1ª Série", "2ª Série", "3ª Série"});
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(220, 150, 34));
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        serieComboBox.setBounds(300, 293, 360, 45);
        serieComboBox.setBorder(null);
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

        // Email
        JTextField emailTextField = new JTextField();
        emailTextField.setName("emailTextField");
        emailTextField.setBackground(new Color(21, 179, 192));
        emailTextField.setForeground(Color.WHITE);
        emailTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
        emailTextField.setBounds(300, 385, 350, 45);
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        background.add(emailTextField);

        // Senha
        JPasswordField senhaPasswordField = new JPasswordField();
        senhaPasswordField.setName("senhaPasswordField");
        senhaPasswordField.setBackground(new Color(11, 54, 165));
        senhaPasswordField.setForeground(Color.WHITE);
        senhaPasswordField.setFont(new Font("SansSerif", Font.BOLD, 20));
        senhaPasswordField.setBounds(300, 475, 300, 45);
        senhaPasswordField.setBorder(BorderFactory.createEmptyBorder());
        background.add(senhaPasswordField);

        // Mostrar/Ocultar senha
        JButton toggleSenhaBtn = new JButton("Mostrar");
        toggleSenhaBtn.setBounds(600, 475, 60, 45);
        toggleSenhaBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggleSenhaBtn.setFocusPainted(false);
        toggleSenhaBtn.setBackground(new Color(11, 54, 165));
        toggleSenhaBtn.setForeground(Color.WHITE);
        toggleSenhaBtn.setBorder(BorderFactory.createEmptyBorder());
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

        // Botão Cadastrar
        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setName("cadastrarButton");
        cadastrarButton.setBackground(new Color(11, 65, 175));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBounds(380, 570, 200, 45);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(cadastrarButton);

        // Exibe a janela
        frame.setVisible(true);
    }
}
