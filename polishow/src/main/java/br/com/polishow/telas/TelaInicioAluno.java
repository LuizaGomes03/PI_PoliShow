package br.com.polishow.telas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Shape;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import br.com.polishow.modelo.Materia;
import br.com.polishow.persistencia.MateriaDAO;


public class TelaInicioAluno extends JFrame {

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
    
    public TelaInicioAluno() {
        setTitle("Tela Inicial - Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640); 
        setLocationRelativeTo(null); 
        setLayout(null);
        setResizable(false);
        setUndecorated(false);


        // img
        JPanel painel = new JPanel() {
            ImageIcon imagemFundo = new ImageIcon("polishow/src/main/imagens/telaInicialAluno.png");
            Image img = imagemFundo.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);
        setContentPane(painel);

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
        new TelaLogin();
        });
        painel.add(voltarButton);

        // selecionar materia
        JComboBox<String> mtComboBox = new JComboBox<>();
        mtComboBox.addItem("Selecionar Matéria");
        try {
            MateriaDAO materiaDAO = new MateriaDAO();
            List<Materia> materias = materiaDAO.listarTodas();

            for (Materia m : materias) {
                mtComboBox.addItem(m.getNomeMateria());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar matérias: " + e.getMessage());
        }
        mtComboBox.setName("mtComboBox");
        mtComboBox.setBackground(new Color(18, 66, 177)); 
        mtComboBox.setForeground(Color.WHITE);
        mtComboBox.setFont(new Font("SansSerif", Font.BOLD, 24));
        mtComboBox.setBounds(355, 250, 280, 45); 
        mtComboBox.setBorder(null);
        mtComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mtComboBox.setUI(new BasicComboBoxUI() {
            
            @Override
            protected JButton createArrowButton() {
                
                BasicArrowButton arrow = new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    new Color(18, 66, 177),   
                    new Color(220, 150, 34),   
                    Color.WHITE,               
                    new Color(220, 150, 34)    
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
        painel.add(mtComboBox);

        // botão iniciar
        JButton botaoIniciar = new JButton("INICIAR");
        botaoIniciar.setBounds(385, 345, 180, 50); 
        botaoIniciar.setBackground(new Color(219, 151, 18)); 
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFont(new Font("SansSerif", Font.BOLD, 24));
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Iniciando..."));
        botaoIniciar.setBorderPainted(false);
        botaoIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painel.add(botaoIniciar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaInicioAluno();
    }
}

