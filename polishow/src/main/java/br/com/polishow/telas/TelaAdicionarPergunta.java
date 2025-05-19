package br.com.polishow.telas;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaAdicionarPergunta {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarPergunta().createAndShowGUI());
    }

    void createAndShowGUI() {
        int imageWidth = 960;
        int imageHeight = 640;

        ImageIcon originalIcon = new ImageIcon("polishow\\\\src\\\\main\\\\imagens/telaAddPergunta.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(imageWidth, imageHeight));

        JFrame frame = new JFrame("Adicionar Pergunta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // ComboBox de Matéria
        JComboBox<String> materiaComboBox = new JComboBox<>(
            new String[] { "Selecione a matéria", "Português", "Matemática", "História" }
        );
        materiaComboBox.setBackground(new Color(3, 13, 93));
        materiaComboBox.setForeground(Color.WHITE);
        materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 18));
        materiaComboBox.setBounds(400, 215, 450, 45);
        materiaComboBox.setBorder(null);
        materiaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return createCustomArrowButton();
            }

            @Override
            public void installListeners() {
                super.installListeners();
                comboBox.setFocusable(false);
            }
        });
        background.add(materiaComboBox);

        // ComboBox de Dificuldade
        JComboBox<String> dificuldadeComboBox = new JComboBox<>(
            new String[] { "Selecione", "Fácil", "Médio", "Difícil" }
        );
        dificuldadeComboBox.setBackground(new Color(3, 13, 93));
        dificuldadeComboBox.setForeground(Color.WHITE);
        dificuldadeComboBox.setFont(new Font("SansSerif", Font.BOLD, 18));
        dificuldadeComboBox.setBounds(400, 425, 450, 45);
        dificuldadeComboBox.setBorder(null);
        dificuldadeComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return createCustomArrowButton();
            }

            @Override
            public void installListeners() {
                super.installListeners();
                comboBox.setFocusable(false);
            }
        });
        background.add(dificuldadeComboBox);

        // Botão "Adicionar Pergunta"
        JButton perguntaButton = new JButton("Clique aqui para adicionar pergunta");
        perguntaButton.setBackground(new Color(3, 13, 93));
        perguntaButton.setForeground(Color.WHITE);
        perguntaButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        perguntaButton.setBounds(395, 285, 350, 45);
        perguntaButton.setFocusPainted(false);
        perguntaButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(perguntaButton);

        perguntaButton.addActionListener(e -> abrirJanelaPergunta());

        // Botão "Adicionar Opções"
        JButton opcoesButton = new JButton("Clique aqui para adicionar opções");
        opcoesButton.setBackground(new Color(3, 13, 93));
        opcoesButton.setForeground(Color.WHITE);
        opcoesButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        opcoesButton.setBounds(390, 355, 350, 45);
        opcoesButton.setFocusPainted(false);
        opcoesButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(opcoesButton);

        opcoesButton.addActionListener(e -> abrirJanelaOpcoes());

        // Botão "Salvar"
        JButton salvarButton = new JButton("SALVAR");
        salvarButton.setBackground(new Color(11, 65, 175));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 25));
        salvarButton.setBounds(420, 534, 120, 40); // Ajuste a posição se necessário
        salvarButton.setFocusPainted(false);
        salvarButton.setBorder(BorderFactory.createEmptyBorder());

        salvarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Pergunta adicionada com sucesso!");
        });

        background.add(salvarButton);

        // Exibe a janela principal
        frame.setVisible(true);
    }

    private JButton createCustomArrowButton() {
        BasicArrowButton arrow = new BasicArrowButton(
            BasicArrowButton.SOUTH,
            new Color(3, 13, 93),
            new Color(3, 13, 93),
            Color.WHITE,
            new Color(3, 13, 93)
        );
        arrow.setBorder(BorderFactory.createEmptyBorder());
        arrow.setContentAreaFilled(false);
        return arrow;
    }

    private void abrirJanelaPergunta() {
        JFrame popup = new JFrame("Digite sua Pergunta");
        popup.setSize(500, 300);
        popup.setLayout(null);
        popup.setLocationRelativeTo(null);

        JTextArea areaPergunta = new JTextArea();
        areaPergunta.setFont(new Font("SansSerif", Font.PLAIN, 16));
        areaPergunta.setLineWrap(true);
        areaPergunta.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaPergunta);
        scroll.setBounds(30, 30, 430, 150);
        popup.add(scroll);

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(190, 200, 120, 40);
        confirmar.setBackground(new Color(0, 102, 204));
        confirmar.setForeground(Color.WHITE);
        confirmar.setFocusPainted(false);
        popup.add(confirmar);

        confirmar.addActionListener(e -> {
            String pergunta = areaPergunta.getText().trim();
            if (!pergunta.isEmpty()) {
                JOptionPane.showMessageDialog(popup, "Pergunta adicionada:\n" + pergunta);
            }
            popup.dispose();
        });

        popup.setVisible(true);
    }

    private void abrirJanelaOpcoes() {
        JFrame popup = new JFrame("Adicionar Opções");
        popup.setSize(500, 450);
        popup.setLayout(null);
        popup.setLocationRelativeTo(null);

        JTextField[] campos = new JTextField[5];
        String[] letras = { "A", "B", "C", "D", "E" };

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Opção " + letras[i] + ":");
            label.setBounds(30, 30 + i * 50, 100, 30);
            popup.add(label);

            campos[i] = new JTextField();
            campos[i].setBounds(120, 30 + i * 50, 330, 30);
            popup.add(campos[i]);
        }

        JLabel labelCorreta = new JLabel("Resposta correta:");
        labelCorreta.setBounds(30, 290, 150, 30);
        popup.add(labelCorreta);

        JComboBox<String> comboCorreta = new JComboBox<>(letras);
        comboCorreta.setBounds(180, 290, 80, 30);
        popup.add(comboCorreta);

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(180, 340, 120, 40);
        confirmar.setBackground(new Color(0, 102, 204));
        confirmar.setForeground(Color.WHITE);
        confirmar.setFocusPainted(false);
        popup.add(confirmar);

        confirmar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                sb.append(letras[i]).append(": ").append(campos[i].getText().trim()).append("\n");
            }
            sb.append("Correta: ").append(comboCorreta.getSelectedItem());
            JOptionPane.showMessageDialog(popup, sb.toString(), "Opções Adicionadas", JOptionPane.INFORMATION_MESSAGE);
            popup.dispose();
        });

        popup.setVisible(true);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}