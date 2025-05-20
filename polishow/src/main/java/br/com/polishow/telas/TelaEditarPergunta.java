package br.com.polishow.telas;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaEditarPergunta extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEditarPergunta().createAndShowGUI());
    }

    void createAndShowGUI() {
        
        int imageWidth = 960;
        int imageHeight = 640;

        
        ImageIcon originalIcon = new ImageIcon("polishow/src/main/imagens/telaEditarPergunta.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(scaledImage);

        
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(imageWidth, imageHeight));

        
        this.setTitle("EditarPergunta");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(background);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // seta
        ImageIcon setaIcon = new ImageIcon("polishow/src/main/imagens/arrow-small-left.png");
        Image setaImage = setaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setaIcon = new ImageIcon(setaImage);

        // botão com imagem de seta
        RoundedInvisibleButton voltarButton = new RoundedInvisibleButton(30);
        voltarButton.setBounds(20, 20, 40, 40);
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voltarButton.setIcon(setaIcon);
        voltarButton.addActionListener(e -> {
        this.dispose();
        new TelaInicialProfessor().TelaInicialProfessor();
        });
        background.add(voltarButton);

        // selecionar matéria
        JComboBox<String> materiaComboBox = new JComboBox<>(
            new String[]{"Selecionar Matéria"});
            materiaComboBox.setName("materiaComboBox");
            materiaComboBox.setBackground(new Color(186, 49, 49));
            materiaComboBox.setForeground(Color.WHITE);
            materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 24));
            materiaComboBox.setBounds(370, 230, 280, 45);
            materiaComboBox.setBorder(null);
            materiaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton arrow = new BasicArrowButton(
                        BasicArrowButton.SOUTH,
                        new Color(186, 49, 49),
                        new Color(186, 49, 49),
                        Color.WHITE,
                        new Color(186, 49, 49));
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

        background.add(voltarButton);

        // selecionar pergunta
        RoundedInvisibleButton selecionarPerguntaButton = new RoundedInvisibleButton(30);
        selecionarPerguntaButton.setText("Selecionar Pergunta");
        selecionarPerguntaButton.setBounds(350, 365, 280, 45);
        selecionarPerguntaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selecionarPerguntaButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        selecionarPerguntaButton.setForeground(Color.WHITE);
        selecionarPerguntaButton.setBackground(new Color(220, 150, 34));
        selecionarPerguntaButton.addActionListener(e -> carregarPergunta());
        background.add(selecionarPerguntaButton);

        
        // botão salvar
        RoundedInvisibleButton salvarButton = new RoundedInvisibleButton(30);
        salvarButton.setText("SALVAR");
        salvarButton.setBounds(395, 535, 170, 45);
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 32));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setBackground(new Color(11, 65, 175));
        background.add(salvarButton);

        this.setVisible(true);
    
    }

    class RoundedInvisibleButton extends JButton {
    private final int arc;
    private boolean isPressed = false;

    public RoundedInvisibleButton(int arc) {
        this.arc = arc;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                isPressed = true;
                repaint();
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                isPressed = false;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isPressed) {
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        }

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        return shape.contains(x, y);
        }
    }


    private void carregarPergunta() {
        
            String[] perguntas = {
                "Qual a fórmula da água?",
                "Qual foi a primeira capital do Brasil?"
            };

            JList <String> listaPerguntas = new JList<>(perguntas);
            listaPerguntas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(listaPerguntas);
            scrollPane.setPreferredSize(new Dimension(400,200));

            int opcao = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Selecione para editar ou excluir",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

             if (opcao == JOptionPane.OK_OPTION) {
            String perguntaSelecionada = listaPerguntas.getSelectedValue();
            if (perguntaSelecionada != null) {
                mostrarPopup(perguntaSelecionada);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma pergunta selecionada.");
            }
        }
    }
           

    private void mostrarPopup (String pergunta){
        JTextField campoPerg = new JTextField(pergunta);

        Object[] campos = {
            "Editar Pergunta: ", campoPerg
        };

        Object[] opcoes = { 
            "Salvar", "Excluir", "Cancelar"
        };

        int opcao = JOptionPane.showOptionDialog(
            this,
            campos,
            "Editar Pergunta",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (opcao == JOptionPane.YES_OPTION){
            String newPergunta = campoPerg.getText();
            JOptionPane.showMessageDialog(
                this,
                "Pergunta Atualizada: \n" + newPergunta
            );
        }

        else if(opcao == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(this, "Pergunta excluída");
        }
    }
    
}