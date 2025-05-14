import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;

// Botão arredondado bonito e funcional
class RoundedButton extends JButton {
    private Color normalColor;
    private Color hoverColor;
    private boolean hovered = false;
    private int shadowSize = 5;

    public RoundedButton(String text, Color normalColor, Color hoverColor, Color fgColor) {
        super(text);
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(fgColor);
        setFont(new Font("Arial", Font.BOLD, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Desenha sombra
        if (hovered) {
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, 30, 30);
        }

        // Desenha o botão
        g2.setColor(hovered ? hoverColor : normalColor);
        g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, 30, 30);

        // Adiciona gradiente
        GradientPaint gp = new GradientPaint(0, 0, 
            new Color(255, 255, 255, 30), 
            0, getHeight(), 
            new Color(0, 0, 0, 30));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, 30, 30);

        g2.dispose();
        super.paintComponent(g);
    }
    @Override
    public void paintBorder(Graphics g) {
        // Sem borda
    }
    @Override
    public boolean isOpaque() {
        return false;
    }
}

// Borda arredondada para ComboBox
class RoundedBorder extends javax.swing.border.AbstractBorder {
    private int radius;
    private Color color;
    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
        g2.dispose();
    }
}

public class EditarPergunta extends JFrame {

    public EditarPergunta() {
        try {
            setTitle("Editar Pergunta");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Painel personalizado para o fundo
            JPanel painelFundo = new JPanel() {
                Image bg;
                {
                    try {
                        // Tenta carregar a imagem da pasta imagens
                        String imagePath = "C:\\Users\\Luiza Gomes\\OneDrive\\Documentos\\GitHub\\Projeto-Integrador-\\PI_codigo\\imagens\\fundo.png";
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            bg = new ImageIcon(imagePath).getImage();
                        } else {
                            System.out.println("Imagem não encontrada em: " + imageFile.getAbsolutePath());
                            bg = null;
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao carregar imagem: " + e.getMessage());
                        bg = null;
                    }
                }
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (bg != null) {
                        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                    } else {
                        // Se não houver imagem, pinta um fundo preto
                        setBackground(Color.BLACK);
                    }
                }
            };
            painelFundo.setLayout(new GridBagLayout());

            // Painel de conteúdo transparente
            JPanel painelConteudo = new JPanel();
            painelConteudo.setOpaque(false);
            painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
            painelConteudo.setPreferredSize(new Dimension(420, 250));

            // Label principal
            JLabel titulo = new JLabel("Edite uma pergunta");
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            titulo.setForeground(new Color(255, 193, 7));
            titulo.setOpaque(false);
            painelConteudo.add(titulo);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

            JLabel subTitulo = new JLabel("Escolha a materia e selecione a pergunta que deseja editar");
            subTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            subTitulo.setFont(new Font("Arial", Font.PLAIN, 15));
            subTitulo.setForeground(Color.WHITE);
            subTitulo.setOpaque(false);
            painelConteudo.add(subTitulo);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 25)));

            // ComboBox para Matéria
            JComboBox<String> comboMateria = new JComboBox<>(new String[]{"Materia", "Matematica", "Portugues", "Historia"});
            comboMateria.setMaximumSize(new Dimension(220, 40));
            comboMateria.setBackground(new Color(230, 57, 70));
            comboMateria.setForeground(Color.WHITE);
            comboMateria.setFont(new Font("Arial", Font.BOLD, 16));
            comboMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
            comboMateria.setBorder(new RoundedBorder(20, new Color(230, 57, 70)));
            comboMateria.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    return label;
                }
            });
            painelConteudo.add(comboMateria);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

            // ComboBox para Perguntas
            JComboBox<String> comboPerguntas = new JComboBox<>(new String[]{"Carregar Perguntas", "Pergunta 1", "Pergunta 2"});
            comboPerguntas.setMaximumSize(new Dimension(220, 40));
            comboPerguntas.setBackground(new Color(255, 196, 0));
            comboPerguntas.setForeground(Color.BLACK);
            comboPerguntas.setFont(new Font("Arial", Font.BOLD, 16));
            comboPerguntas.setAlignmentX(Component.CENTER_ALIGNMENT);
            comboPerguntas.setBorder(new RoundedBorder(20, new Color(255, 196, 0)));
            comboPerguntas.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    return label;
                }
            });
            painelConteudo.add(comboPerguntas);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));

            // Botão Salvar com cores mais vibrantes
            RoundedButton btnSalvar = new RoundedButton("Salvar", 
                new Color(41, 128, 185), // Azul vibrante
                new Color(52, 152, 219),  // Azul mais claro para hover
                Color.WHITE);
            btnSalvar.setMaximumSize(new Dimension(120, 40));
            btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelConteudo.add(btnSalvar);

            // Adiciona o painel de conteúdo centralizado no painel de fundo
            painelFundo.add(painelConteudo, new GridBagConstraints());
            setContentPane(painelFundo);
        } catch (Exception e) {
            System.out.println("Erro ao criar janela: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
        materiaComboBox.setBounds(355, 205, 245, 45);
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
        JComboBox<String> serieComboBox = new JComboBox<>(new String[]{"Carregar Pergunta"});
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(220, 150, 34));
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 20));
        serieComboBox.setBounds(300, 365, 360, 50);
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
        cadastrarButton.setBounds(380, 535, 200, 45);
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
                            new String[]{"Perguntas Cadastradas "}
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