package br.com.polishow.telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.ArrayList;
import java.util.List;
import br.com.polishow.modelo.Alternativas;
import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Questao;
import br.com.polishow.persistencia.MateriaDAO;
import br.com.polishow.persistencia.QuestaoDAO;
import java.awt.geom.RoundRectangle2D;

public class TelaAdicionarPergunta {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarPergunta().createAndShowGUI());
    }

    private String textoPergunta = null;
    private List<Alternativas> listaAlternativas = new ArrayList<>();
    private Materia materiaSelecionada = null;
    private String dificuldadeSelecionada = null;
    private String letraCorreta = null;
    private JComboBox<String> materiaComboBox;

    void createAndShowGUI() {
        int imageWidth = 960;
        int imageHeight = 640;

        ImageIcon originalIcon = new ImageIcon("polishow/src/main/imagens/TelaDeAdicionarPergunta1.png");
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

        materiaComboBox = new JComboBox<>();
        materiaComboBox.addItem("Selecione a matéria");

        try {
            MateriaDAO materiaDAO = new MateriaDAO();
            List<Materia> materias = materiaDAO.listarTodas();
            for (Materia m : materias) {
                materiaComboBox.addItem(m.getNomeMateria());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar matérias: " + e.getMessage());
        }

        materiaComboBox.setBackground(new Color(3, 13, 93));
        materiaComboBox.setForeground(Color.WHITE);
        materiaComboBox.setFont(new Font("SansSerif", Font.BOLD, 18));
        materiaComboBox.setBounds(411, 215, 430, 45);
        materiaComboBox.setBorder(null);
        materiaComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        JTextField campoBuscaMateria = new JTextField();
        campoBuscaMateria.setBounds(411, 280, 310, 45);
        campoBuscaMateria.setForeground(Color.WHITE);
        campoBuscaMateria.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campoBuscaMateria.setToolTipText("Digite para filtrar matérias...");
        campoBuscaMateria.setBorder(null);
        campoBuscaMateria.setBackground(new Color(3, 13, 93));
        background.add(campoBuscaMateria);

        JButton adicionarMateriaButton = new JButton("Adicionar");
        adicionarMateriaButton.setBounds(720, 280, 140, 45);
        adicionarMateriaButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        adicionarMateriaButton.setForeground(Color.WHITE);
        adicionarMateriaButton.setBackground(new Color(3, 13, 93));
        adicionarMateriaButton.setBorder(BorderFactory.createEmptyBorder());
        adicionarMateriaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adicionarMateriaButton.addActionListener(e -> {
            String nomeMateria = campoBuscaMateria.getText().trim();
            if (!nomeMateria.isEmpty()) {
                try {
                    MateriaDAO dao = new MateriaDAO();
                    List<Materia> existentes = dao.buscarPorTexto(nomeMateria);
                    boolean jaExiste = existentes.stream()
                            .anyMatch(m -> m.getNomeMateria().equalsIgnoreCase(nomeMateria));
                    if (jaExiste) {
                        JOptionPane.showMessageDialog(null, "A matéria já existe.");
                    } else {
                        Materia nova = new Materia();
                        nova.setNomeMateria(nomeMateria);
                        dao.cadastrar(nova);
                        JOptionPane.showMessageDialog(null, "Matéria adicionada com sucesso!");

                        // Atualiza o combo com todas as matérias sem filtro
                        materiaComboBox.removeAllItems();
                        materiaComboBox.addItem("Selecionar Matéria");
                        List<Materia> todas = dao.listarTodas();
                        for (Materia m : todas) {
                            materiaComboBox.addItem(m.getNomeMateria());
                        }

                        campoBuscaMateria.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar matéria: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite o nome da matéria.");
            }
        });
        background.add(adicionarMateriaButton);

        materiaComboBox.addActionListener(e -> {
            int idx = materiaComboBox.getSelectedIndex();
            if (idx > 0) {
                try {
                    MateriaDAO materiaDAO = new MateriaDAO();
                    List<Materia> materias = materiaDAO.listarTodas();
                    materiaSelecionada = materias.get(idx - 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                materiaSelecionada = null;
            }
        });

        JComboBox<String> dificuldadeComboBox = new JComboBox<>(
                new String[] { "Selecione a dificuldade", "Fácil", "Médio", "Difícil" });
        dificuldadeComboBox.setBackground(new Color(3, 13, 93));
        dificuldadeComboBox.setForeground(Color.WHITE);
        dificuldadeComboBox.setFont(new Font("SansSerif", Font.BOLD, 18));
        dificuldadeComboBox.setBounds(410, 485, 430, 45);
        dificuldadeComboBox.setBorder(null);
        dificuldadeComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        dificuldadeComboBox.addActionListener(e -> {
            String diff = (String) dificuldadeComboBox.getSelectedItem();
            if (!diff.equals("Selecione a dificuldade")) {
                dificuldadeSelecionada = diff;
            } else {
                dificuldadeSelecionada = null;
            }
        });

        JButton perguntaButton = new JButton("Clique aqui para adicionar pergunta");
        perguntaButton.setForeground(Color.WHITE);
        perguntaButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        perguntaButton.setBounds(398, 345, 350, 45);
        perguntaButton.setFocusPainted(false);
        perguntaButton.setBorder(BorderFactory.createEmptyBorder());
        perguntaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        perguntaButton.setContentAreaFilled(false);
        perguntaButton.setOpaque(false);
        perguntaButton.addActionListener(e -> abrirJanelaPergunta());
        background.add(perguntaButton);

        JButton opcoesButton = new JButton("Clique aqui para adicionar opções");
        opcoesButton.setForeground(Color.WHITE);
        opcoesButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        opcoesButton.setBounds(392, 415, 350, 45);
        opcoesButton.setFocusPainted(false);
        opcoesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        opcoesButton.setBorder(BorderFactory.createEmptyBorder());
        opcoesButton.setContentAreaFilled(false);
        opcoesButton.setOpaque(false);
        opcoesButton.addActionListener(e -> abrirJanelaOpcoes());
        background.add(opcoesButton);

        RoundedInvisibleButton salvarButton = new RoundedInvisibleButton(50);
        salvarButton.setBounds(382, 568, 195, 54);
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        salvarButton.addActionListener(e -> {
            try {
                if (textoPergunta == null || textoPergunta.isEmpty() ||
                        listaAlternativas.isEmpty() ||
                        materiaSelecionada == null ||
                        dificuldadeSelecionada == null ||
                        letraCorreta == null) {
                    JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.");
                    return;
                }

                Questao questao = new Questao();
                questao.setMateria(materiaSelecionada);
                questao.setPergunta(textoPergunta);
                questao.setDificuldade(dificuldadeSelecionada);

                char letra = letraCorreta.charAt(0);
                int indexCorreta = letra - 'A';
                for (int i = 0; i < listaAlternativas.size(); i++) {
                    listaAlternativas.get(i).setCorreta(i == indexCorreta);
                }

                new QuestaoDAO().cadastrar(questao, listaAlternativas, letraCorreta);
                JOptionPane.showMessageDialog(frame, "Pergunta adicionada com sucesso!");

                textoPergunta = null;
                listaAlternativas.clear();
                materiaSelecionada = null;
                dificuldadeSelecionada = null;
                letraCorreta = null;
                materiaComboBox.setSelectedIndex(0);
                dificuldadeComboBox.setSelectedIndex(0);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao adicionar pergunta: " + ex.getMessage());
            }
        });
        background.add(salvarButton);

        ImageIcon setaIcon = new ImageIcon("polishow/src/main/imagens/arrow-small-left.png");
        Image setaImage = setaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setaIcon = new ImageIcon(setaImage);
        JButton voltarButton = new JButton(setaIcon);
        voltarButton.setBounds(15, 15, 40, 40);
        voltarButton.setFocusPainted(false);
        voltarButton.setContentAreaFilled(false);
        voltarButton.setBorderPainted(false);
        voltarButton.setOpaque(false);
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voltarButton.addActionListener(e -> {
            new TelaInicialProfessor();
            frame.dispose();
        });
        background.add(voltarButton);

        frame.setVisible(true);
    }

    private JButton createCustomArrowButton() {
        BasicArrowButton arrow = new BasicArrowButton(
                BasicArrowButton.SOUTH,
                new Color(3, 13, 93),
                new Color(3, 13, 93),
                Color.WHITE,
                new Color(3, 13, 93));
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
        confirmar.addActionListener(e -> {
            String pergunta = areaPergunta.getText().trim();
            if (!pergunta.isEmpty()) {
                textoPergunta = pergunta;
                JOptionPane.showMessageDialog(popup, "Pergunta adicionada:\n" + pergunta);
            }
            popup.dispose();
        });
        popup.add(confirmar);
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
        confirmar.addActionListener(e -> {
            listaAlternativas.clear();
            for (int i = 0; i < 5; i++) {
                String textoAlt = campos[i].getText().trim();
                if (!textoAlt.isEmpty()) {
                    Alternativas alt = new Alternativas();
                    alt.setAlternativa(textoAlt);
                    listaAlternativas.add(alt);
                }
            }
            letraCorreta = (String) comboCorreta.getSelectedItem();
            JOptionPane.showMessageDialog(popup, "Opções adicionadas. Alternativa correta: " + letraCorreta);
            popup.dispose();
        });
        popup.add(confirmar);
        popup.setVisible(true);
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

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}
