import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarPergunta {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarPergunta().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Tamanho desejado da imagem
        int imageWidth = 960;
        int imageHeight = 640;

        // Carrega e redimensiona a imagem de fundo
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\25.00995-2\\Downloads\\Projeto-Integrador-\\PI_codigo\\imagens\\Tela de editar pergunta (1).png");
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

        // ComboBox para Matéria
        JComboBox<String> materiaComboBox = new JComboBox<>(
                new String[]{"Matéria", "Matematica", "Portugues", "Historia"});
        materiaComboBox.setName("materiaComboBox");
        materiaComboBox.setBackground(new Color(230, 57, 70));
        materiaComboBox.setForeground(Color.WHITE);
        materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        materiaComboBox.setBounds(300, 190, 290, 65);
        materiaComboBox.setBorder(null);
        materiaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(230, 57, 70),
                        new Color(230, 57, 70),
                        Color.WHITE,
                        new Color(230, 57, 70));
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
        background.add(materiaComboBox);

        // ComboBox para Série (inicialmente dependente da matéria)
        JComboBox<String> serieComboBox = new JComboBox<>(new String[]{"Selecionar Matéria"});
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

        // Botão Salvar
        JButton cadastrarButton = new JButton("Salvar");
        cadastrarButton.setName("cadastrarButton");
        cadastrarButton.setBackground(new Color(11, 65, 175));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBounds(380, 520, 200, 45);
        cadastrarButton.setBorder(BorderFactory.createEmptyBorder());
        background.add(cadastrarButton);

        // Remove o botão "Carregar Perguntas"
        // background.remove(carregarPerguntasButton); // Não foi adicionado neste código

        // Adiciona um ActionListener para o ComboBox de Matéria
        materiaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMateria = (String) materiaComboBox.getSelectedItem();
                if (!selectedMateria.equals("Matéria")) {
                    // Aqui você pode adicionar a lógica para carregar as séries correspondentes
                    // Por enquanto, vamos apenas preencher com algumas opções fixas
                    serieComboBox.setModel(new DefaultComboBoxModel<>(
                            new String[]{"Série", "1ª Série", "2ª Série", "3ª Série"}
                    ));
                    // Se uma matéria válida for selecionada, tentamos carregar as perguntas
                    tentarCarregarPerguntas(materiaComboBox, serieComboBox, frame);
                } else {
                    serieComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Selecionar Matéria"}));
                }
            }
        });

        // Adiciona um ActionListener para o ComboBox de Série
        serieComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tentamos carregar as perguntas sempre que a série é alterada
                tentarCarregarPerguntas(materiaComboBox, serieComboBox, frame);
            }
        });

        // Exibe a janela
        frame.setVisible(true);
    }

    private void tentarCarregarPerguntas(JComboBox<String> materiaCombo, JComboBox<String> serieCombo, JFrame frame) {
        String selectedMateria = (String) materiaCombo.getSelectedItem();
        String selectedSerie = (String) serieCombo.getSelectedItem();

        if (!selectedMateria.equals("Matéria") && !selectedSerie.equals("Selecionar Matéria") && !selectedSerie.equals("Série")) {
            // Aqui você adicionaria a lógica para carregar as perguntas
            // com base na matéria e série selecionadas
            JOptionPane.showMessageDialog(frame,
                    "Perguntas carregadas para " + selectedMateria + " - " + selectedSerie,
                    "Carregar Perguntas", JOptionPane.INFORMATION_MESSAGE);
        }
        // Não precisamos de um 'else' aqui, pois a mensagem de aviso seria repetitiva
        // a cada seleção nos ComboBoxes.
    }
}