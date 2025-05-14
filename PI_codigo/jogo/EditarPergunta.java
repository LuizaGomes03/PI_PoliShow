import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
                        String imagePath = "../imagens/fundo.png";
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
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    EditarPergunta frame = new EditarPergunta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Erro ao criar janela: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println("Erro ao iniciar aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
