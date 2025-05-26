package br.com.polishow.telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Pontuacao;
import br.com.polishow.modelo.Questao;
import br.com.polishow.modelo.Usuario;
import br.com.polishow.modelo.Alternativas;
import br.com.polishow.persistencia.QuestaoDAO;
import br.com.polishow.persistencia.AlternativasDAO;
import br.com.polishow.persistencia.PontuacaoDAO;

public class TelaJogo extends JFrame {

    private Usuario usuarioLogado;
    private Materia materiaSelecionada;
    private List<Questao> questoes;
    private int indiceAtual = 0;
    private boolean ajudaUsada = false;
    private boolean puloUsado = false;
    private double pontuacaoAtual = 0;
    private final int[] valores = { 1000, 2000, 3000, 5000, 10000, 20000, 40000, 80000, 160000, 320000, 640000,
            1000000 };
    private int numeroDeAcertos = 0;
    private final int totalQuestoes = 12;

    private JPanel painelFundo;
    private JLabel lblPergunta;
    private JButton btnSair;
    private JButton btnElimina;
    private JButton btnPular;

    private List<JButton> alternativasBtns = new ArrayList<>();

    public TelaJogo(Materia materia, Usuario usuario) {
        this.materiaSelecionada = materia;
        this.usuarioLogado = usuario;
        setTitle("Tela Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponentes();
        carregarQuestoes();

        setVisible(true);
    }

    private void initComponentes() {
        painelFundo = new JPanel() {
            Image imagem = new ImageIcon("polishow/src/main/imagens/TelaJogo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(null);

        // Botão Sair
        btnSair = new JButton();
        btnSair.setBounds(48, 15, 40, 35);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setOpaque(false);
        btnSair.setBorderPainted(false);
        btnSair.setBorder(new RoundBorder(35));
        btnSair.setToolTipText("Sair do Jogo");
        btnSair.addActionListener((ActionEvent e) -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do jogo?", "Sair do Jogo",
                    JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    Pontuacao pontuacao = new Pontuacao();
                    pontuacao.setUsuario(usuarioLogado);
                    pontuacao.setMateria(materiaSelecionada);
                    pontuacao.setPontos(pontuacaoAtual);
                    PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
                    pontuacaoDAO.salvar(pontuacao);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao salvar pontuação: " + ex.getMessage());
                }

                dispose();
                TelaPontuacaoFinal telaFinal = new TelaPontuacaoFinal(materiaSelecionada, totalQuestoes,
                        numeroDeAcertos, pontuacaoAtual, usuarioLogado);
                telaFinal.setVisible(true);
            }
        });
        painelFundo.add(btnSair);

        // Botão Eliminar
        btnElimina = new JButton();
        btnElimina.setBounds(95, 15, 40, 35);
        btnElimina.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnElimina.setFocusPainted(false);
        btnElimina.setContentAreaFilled(false);
        btnElimina.setOpaque(false);
        btnElimina.setBorderPainted(false);
        btnElimina.setBorder(new RoundBorder(35));
        btnElimina.setToolTipText("Eliminar três alternativas");
        btnElimina.addActionListener((ActionEvent e) -> {
            if (ajudaUsada) {
                JOptionPane.showMessageDialog(this, "Você já usou essa ajuda.", "Ajuda já usada",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(this,
                    "Gostaria de eliminar 3 alternativas?\nVocê só poderá usar essa ajuda uma vez durante toda sua jogada.",
                    "Eliminar Alternativas", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                eliminarAlternativasErradas();
                ajudaUsada = true;
                JOptionPane.showMessageDialog(this, "Três alternativas foram eliminadas!", "Ajuda utilizada",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        painelFundo.add(btnElimina);

        // Botão Pular
        btnPular = new JButton();
        btnPular.setBounds(145, 15, 35, 35);
        btnPular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPular.setFocusPainted(false);
        btnPular.setContentAreaFilled(false);
        btnPular.setOpaque(false);
        btnPular.setBorderPainted(false);
        btnPular.setBorder(new RoundBorder(35));
        btnPular.setToolTipText("Pular para próxima questão");
        btnPular.addActionListener((ActionEvent e) -> {
            if (puloUsado) {
                JOptionPane.showMessageDialog(this, "Você já usou essa ajuda.", "Ajuda já usada",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(this,
                    "Gostaria de pular essa questão?\nVocê só poderá usar essa ajuda uma vez durante toda sua jogada.",
                    "Pular Questão", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                puloUsado = true;
                indiceAtual++;
                exibirQuestaoAtual();
            }
        });
        painelFundo.add(btnPular);

        // Label Pergunta
        lblPergunta = new JLabel("");
        lblPergunta.setBounds(180, 120, 700, 60);
        lblPergunta.setFont(new Font("Arial", Font.BOLD, 24));
        lblPergunta.setForeground(Color.WHITE);
        painelFundo.add(lblPergunta);

        setContentPane(painelFundo);
    }

    private void carregarQuestoes() {
        try {
            QuestaoDAO qdao = new QuestaoDAO();
            questoes = qdao.buscar12Questoes(materiaSelecionada.getIdMateria());

            if (questoes.size() < 12) {
                JOptionPane.showMessageDialog(this, "Número insuficiente de questões para essa matéria.");
                dispose();
                return;
            }

            indiceAtual = 0;
            exibirQuestaoAtual();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar questões: " + e.getMessage());
            dispose();
        }
    }

    private void exibirQuestaoAtual() {
        if (indiceAtual >= questoes.size()) {
            JOptionPane.showMessageDialog(this, "Fim do jogo!");

            // Salvar pontuação
            try {
                Pontuacao pontuacao = new Pontuacao();
                pontuacao.setUsuario(usuarioLogado);
                pontuacao.setMateria(materiaSelecionada);
                pontuacao.setPontos(pontuacaoAtual);
                PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
                pontuacaoDAO.salvar(pontuacao);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar pontuação: " + e.getMessage());
            }

            // Abrir tela final
            TelaPontuacaoFinal telaFinal = new TelaPontuacaoFinal(materiaSelecionada, totalQuestoes, numeroDeAcertos,
                    pontuacaoAtual, usuarioLogado);
            telaFinal.setVisible(true);
            dispose();
            return;
        }

        // Remove os botões de alternativas antigos da tela
        for (JButton btn : alternativasBtns) {
            painelFundo.remove(btn);
        }
        alternativasBtns.clear();

        Questao q = questoes.get(indiceAtual);
        lblPergunta.setText("<html><b>" + q.getPergunta() + "</b></html>");

        try {
            AlternativasDAO altDAO = new AlternativasDAO();
            List<Alternativas> alternativas = altDAO.listar(q);

            int y = 190;
            for (Alternativas alt : alternativas) {
                JButton btnAlternativa = new JButton(alt.getAlternativa());
                btnAlternativa.setBounds(180, y, 580, 40);
                btnAlternativa.setFont(new Font("Arial", Font.PLAIN, 18));
                btnAlternativa.setFocusPainted(false);
                btnAlternativa.setHorizontalAlignment(SwingConstants.LEFT);
                btnAlternativa.setMargin(new Insets(0, 15, 0, 0));
                btnAlternativa.setBackground(new Color(18, 66, 177));
                btnAlternativa.setForeground(Color.WHITE);
                btnAlternativa.setOpaque(true);
                btnAlternativa.setBorderPainted(false);
                btnAlternativa.setCursor(new Cursor(Cursor.HAND_CURSOR));

                btnAlternativa.addActionListener(ev -> {
                    int resposta = JOptionPane.showConfirmDialog(
                            this,
                            "Tem certeza que deseja selecionar esta alternativa?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        if (alt.isCorreta()) {
                            pontuacaoAtual = valores[indiceAtual];
                            numeroDeAcertos++;
                            JOptionPane.showMessageDialog(this, "Resposta correta!\nVocê ganhou R$" + pontuacaoAtual);
                            indiceAtual++;
                            ajudaUsada = false;
                            exibirQuestaoAtual();
                        } else {
                            double checkpoint = getCheckpoint();
                            JOptionPane.showMessageDialog(this,
                                    "Resposta incorreta!\nVocê sairá com R$" + checkpoint,
                                    "Fim de jogo",
                                    JOptionPane.INFORMATION_MESSAGE);

                            try {
                                Pontuacao pontuacao = new Pontuacao();
                                pontuacao.setUsuario(usuarioLogado);
                                pontuacao.setMateria(materiaSelecionada);
                                pontuacao.setPontos(checkpoint);
                                PontuacaoDAO pontuacaoDAO = new PontuacaoDAO();
                                pontuacaoDAO.salvar(pontuacao);
                            } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Erro ao salvar pontuação: " + e.getMessage());
                            }

                            TelaPontuacaoFinal telaFinal = new TelaPontuacaoFinal(
                                    materiaSelecionada, totalQuestoes, numeroDeAcertos, checkpoint, usuarioLogado);
                            telaFinal.setVisible(true);
                            dispose();
                        }
                    }
                });

                alternativasBtns.add(btnAlternativa);
                painelFundo.add(btnAlternativa);
                y += 60;
            }

            painelFundo.repaint();
            painelFundo.revalidate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar alternativas: " + e.getMessage());
        }
    }

    private void eliminarAlternativasErradas() {
        // Busca qual é a alternativa correta
        Questao q = questoes.get(indiceAtual);
        try {
            AlternativasDAO altDAO = new AlternativasDAO();
            List<Alternativas> alternativas = altDAO.listar(q);

            String correta = alternativas.stream()
                    .filter(Alternativas::isCorreta)
                    .map(Alternativas::getAlternativa)
                    .findFirst()
                    .orElse("");

            List<JButton> erradas = alternativasBtns.stream()
                    .filter(btn -> !btn.getText().equals(correta))
                    .collect(Collectors.toList());

            Collections.shuffle(erradas);

            for (int i = 0; i < 3 && i < erradas.size(); i++) {
                JButton btn = erradas.get(i);
                btn.setEnabled(false);
                btn.setBackground(Color.GRAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao eliminar alternativas: " + e.getMessage());
        }
    }

    // Borda arredondada
    static class RoundBorder extends AbstractBorder {
        private int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getForeground());
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    private double getCheckpoint() {
        if (indiceAtual >= 8) {
            return 80000;
        } else if (indiceAtual >= 4) {
            return 5000;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
