package br.com.polishow.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import br.com.polishow.modelo.Alternativas;
import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Questao;
import br.com.polishow.persistencia.AlternativasDAO;
import br.com.polishow.persistencia.MateriaDAO;
import br.com.polishow.persistencia.QuestaoDAO;

public class TelaEditarPergunta extends JFrame {

    private JComboBox<String> materiaComboBox;

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

        ImageIcon setaIcon = new ImageIcon("polishow/src/main/imagens/arrow-small-left.png");
        Image setaImage = setaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setaIcon = new ImageIcon(setaImage);

        RoundedInvisibleButton voltarButton = new RoundedInvisibleButton(30);
        voltarButton.setBounds(20, 20, 40, 40);
        voltarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voltarButton.setIcon(setaIcon);
        voltarButton.addActionListener(e -> {
            this.dispose();
            new TelaInicialProfessor().TelaInicialProfessor();
        });
        background.add(voltarButton);

        materiaComboBox = new JComboBox<>();
        materiaComboBox.addItem("Selecionar Matéria");
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

        RoundedInvisibleButton selecionarPerguntaButton = new RoundedInvisibleButton(30);
        selecionarPerguntaButton.setText("Selecionar Pergunta");
        selecionarPerguntaButton.setBounds(350, 365, 280, 45);
        selecionarPerguntaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selecionarPerguntaButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        selecionarPerguntaButton.setForeground(Color.WHITE);
        selecionarPerguntaButton.setBackground(new Color(220, 150, 34));
        selecionarPerguntaButton.addActionListener(e -> carregarPergunta());
        background.add(selecionarPerguntaButton);

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
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            int selectedIndex = materiaComboBox.getSelectedIndex();
            if (selectedIndex <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma matéria.");
                return;
            }

            String nomeMateriaSelecionada = (String) materiaComboBox.getSelectedItem();
            MateriaDAO materiaDAO = new MateriaDAO();
            List<Materia> materias = materiaDAO.listarTodas();

            int idMateriaSelecionada = -1;
            for (Materia m : materias) {
                if (m.getNomeMateria().equalsIgnoreCase(nomeMateriaSelecionada)) {
                    idMateriaSelecionada = m.getIdMateria();
                    break;
                }
            }

            if (idMateriaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Matéria não encontrada.");
                return;
            }

            QuestaoDAO dao = new QuestaoDAO();
            List<Questao> questoes = dao.buscarPorMateria(idMateriaSelecionada);

            if (questoes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma pergunta cadastrada para esta matéria.");
                return;
            }

            DefaultListModel<Questao> model = new DefaultListModel<>();
            for (Questao q : questoes) {
                model.addElement(q);
            }

            JList<Questao> listaPerguntas = new JList<>(model);
            listaPerguntas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(listaPerguntas);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            int opcao = JOptionPane.showConfirmDialog(
                    this,
                    scrollPane,
                    "Selecione para editar ou excluir",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (opcao == JOptionPane.OK_OPTION) {
                Questao perguntaSelecionada = listaPerguntas.getSelectedValue();
                if (perguntaSelecionada != null) {
                    mostrarPopup(perguntaSelecionada);
                } else {
                    JOptionPane.showMessageDialog(this, "Nenhuma pergunta selecionada.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar perguntas: " + e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void mostrarPopup(Questao pergunta) {
    AlternativasDAO altDao = new AlternativasDAO();
    List<Alternativas> alternativas;
    try {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        alternativas = altDao.listar(pergunta);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao carregar alternativas: " + e.getMessage());
        return;
    } finally {
        setCursor(Cursor.getDefaultCursor());
    }

    JTextField campoPerg = new JTextField(pergunta.getPergunta());

    JTextField[] camposAlternativas = new JTextField[alternativas.size()];
    JRadioButton[] radios = new JRadioButton[alternativas.size()];
    ButtonGroup grupoRadios = new ButtonGroup();

    JPanel painel = new JPanel(new GridLayout(0, 1));
    painel.add(new JLabel("Editar Pergunta:"));
    painel.add(campoPerg);

    for (int i = 0; i < alternativas.size(); i++) {
        JPanel linhaAlt = new JPanel(new GridLayout(1, 2));
        camposAlternativas[i] = new JTextField(alternativas.get(i).getAlternativa());
        radios[i] = new JRadioButton("Alternativa " + (char) ('A' + i));

        
        if (alternativas.get(i).isCorreta()) {
            radios[i].setSelected(true);
        }

        grupoRadios.add(radios[i]);

        linhaAlt.add(radios[i]);
        linhaAlt.add(camposAlternativas[i]);

        painel.add(linhaAlt);
    }

    Object[] opcoes = {"Salvar", "Excluir", "Cancelar"};

    int opcao = JOptionPane.showOptionDialog(
            this,
            painel,
            "Editar Pergunta",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]);

    QuestaoDAO dao = new QuestaoDAO();

    if (opcao == JOptionPane.YES_OPTION) {
        String novaPergunta = campoPerg.getText();
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            pergunta.setPergunta(novaPergunta);
            dao.atualizar(pergunta);

            for (int i = 0; i < alternativas.size(); i++) {
                Alternativas alt = alternativas.get(i);
                alt.setAlternativa(camposAlternativas[i].getText());
                alt.setCorreta(radios[i].isSelected());
                altDao.atualizar(alt);
            }

            JOptionPane.showMessageDialog(this, "Pergunta e alternativas atualizadas.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }

    } else if (opcao == JOptionPane.NO_OPTION) {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            altDao.removerPorQuestao(pergunta);
            dao.remover(pergunta);
            JOptionPane.showMessageDialog(this, "Pergunta e alternativas excluídas.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

}
