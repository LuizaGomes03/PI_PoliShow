package br.com.polishow.telas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

    public TelaInicioAluno() {
        setTitle("Tela Inicial - Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640); // Tamanho da tela
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(null);
        setResizable(false);
        setUndecorated(true);


        // Painel com imagem de fundo
        JPanel painel = new JPanel() {
            ImageIcon imagemFundo = new ImageIcon("polishow\\\\src\\\\main\\\\imagens\\Tela bem-vindo (1) (1).png");
            Image img = imagemFundo.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);
        setContentPane(painel);

        // JComboBox para selecionar a série
        JComboBox<String> serieComboBox = new JComboBox<>();
        serieComboBox.addItem("Selecionar Matéria");
        try {
            MateriaDAO materiaDAO = new MateriaDAO();
            List<Materia> materias = materiaDAO.listarTodas();

            for (Materia m : materias) {
                serieComboBox.addItem(m.getNomeMateria());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar matérias: " + e.getMessage());
        }
        serieComboBox.setName("serieComboBox");
        serieComboBox.setBackground(new Color(18, 66, 177)); // Azul
        serieComboBox.setForeground(Color.WHITE);
        serieComboBox.setFont(new Font("SansSerif", Font.BOLD, 22));
        serieComboBox.setBounds(378, 262, 250, 45); // Posição e tamanho
        serieComboBox.setBorder(null);
        serieComboBox.setUI(new BasicComboBoxUI() {
            
            @Override
            protected JButton createArrowButton() {
                // Cria uma seta apontando para baixo, usando BasicArrowButton
                BasicArrowButton arrow = new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    new Color(18, 66, 177),   // Background do botão (mesma cor do combo)
                    new Color(220, 150, 34),   // Shadow
                    Color.WHITE,               // Cor da seta
                    new Color(220, 150, 34)    // Highlight
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
        painel.add(serieComboBox);

        // Botão Iniciar
        JButton botaoIniciar = new JButton("INICIAR");
        botaoIniciar.setBounds(408, 338, 145, 35); // Posição e tamanho
        botaoIniciar.setBackground(new Color(219, 151, 18)); // Verde
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFont(new Font("SansSerif", Font.BOLD, 20));
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Iniciando..."));
        botaoIniciar.setBorderPainted(false);
        painel.add(botaoIniciar);

        // Botão Sair
        JButton botaoSair = new JButton("SAIR");
        botaoSair.setBounds(408, 408, 145, 35); // Posição e tamanho
        botaoSair.setBackground(new Color(187, 45, 57)); // Vermelho
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setFont(new Font("SansSerif", Font.BOLD, 20));
        botaoSair.setFocusPainted(false);
        botaoSair.addActionListener(e -> System.exit(0));
        botaoSair.setBorderPainted(false);
        painel.add(botaoSair);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaInicioAluno();
    }
}
// Código para impedir que selecione o "placeholder" do combobox (é para colocar no listener quando ele for feito)
// if (serieComboBox.getSelectedIndex() == 0) {
//     JOptionPane.showMessageDialog(null, "Por favor, selecione uma matéria válida.");
//     return;
// } 
