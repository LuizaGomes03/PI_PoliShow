package br.com.polishow.telas;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.modelo.Pontuacao;
import br.com.polishow.persistencia.PontuacaoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaDesempenhoAluno extends JDialog {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaDesempenhoAluno(JFrame parent, String serieFiltro) {
        super(parent, "Desempenho - " + serieFiltro + " Série", true);
        setSize(700, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"Nome","Série","Matéria","Pontuação"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        carregarDesempenho(serieFiltro);

        JPanel panelSul = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton voltar = new JButton("Voltar");
        
        voltar.addActionListener(e -> {
            new TelaSelecaoSerie(parent).setVisible(true);
            dispose();
        });
        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());
        panelSul.add(voltar);
        panelSul.add(fechar);
        add(panelSul, BorderLayout.SOUTH);
    }

    private void carregarDesempenho(String serieFiltro) {
        try {
            PontuacaoDAO pontDao = new PontuacaoDAO();
            List<Pontuacao> lista = pontDao.listar();

            modelo.setRowCount(0);

            String filtro = extrairNumeroSerie(serieFiltro);

            for (Pontuacao p : lista) {
                Aluno aluno = p.getAluno();
                if (aluno != null) {
                    String serieAluno = extrairNumeroSerie(aluno.getSerie());
                    if (filtro.equals(serieAluno)) {
                        modelo.addRow(new Object[]{
                            p.getUsuario().getNomeUsuario(),
                            aluno.getSerie(),
                            p.getMateria().getNomeMateria(),
                            p.getPontos()
                        });
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar desempenho: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String extrairNumeroSerie(String serie) {
        if (serie == null) return "";
        return serie.replaceAll("[^0-9]", "");
    }
}
