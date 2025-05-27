package br.com.polishow.telas;

import br.com.polishow.modelo.Pontuacao;
import br.com.polishow.persistencia.PontuacaoDAO;
import br.com.polishow.persistencia.AlunoDAO;

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
            dispose();
            if (parent instanceof TelaInicialProfessor telaProfessor) {
                telaProfessor.abrirSeletorSeries();
            }
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
            AlunoDAO   alunoDao = new AlunoDAO();

            List<Pontuacao> lista = pontDao.listar();
            for (Pontuacao p : lista) {
                var usuario = p.getUsuario();
                var aluno   = alunoDao.buscarPorEmail(usuario.getEmailUsuario());
                if (aluno != null && serieFiltro.equals(aluno.getSerie())) {
                    modelo.addRow(new Object[]{
                        usuario.getNomeUsuario(),
                        aluno.getSerie(),
                        p.getMateria().getNomeMateria(),
                        p.getPontos()
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar desempenho: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}