package br.com.polishow.telas;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.persistencia.AlunoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaListaAlunos extends JDialog {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaListaAlunos(JFrame parent) {
        super(parent, "Lista de Alunos", true);
        setSize(700, 400);
        setLocationRelativeTo(parent);

        
        modelo = new DefaultTableModel(new String[]{"Nome", "Email", "Série", "Senha"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton editar = new JButton("Editar");
        JButton excluir = new JButton("Excluir");

        JPanel botoes = new JPanel();
        botoes.add(editar);
        botoes.add(excluir);

        add(scroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        carregarAlunos();

        editar.addActionListener(e -> editarAlunoSelecionado());
        excluir.addActionListener(e -> excluirAlunoSelecionado());

        setVisible(true);
    }

    private void carregarAlunos() {
        modelo.setRowCount(0); 
        try {
            ArrayList<Aluno> alunos = new AlunoDAO().buscarTodos();
            for (Aluno a : alunos) {
                modelo.addRow(new Object[]{
                    a.getNomeUsuario(), 
                    a.getEmailUsuario(), 
                    a.getSerie(),
                    a.getSenhaUsuario()  
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar alunos");
        }
    }

    private void editarAlunoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para editar");
            return;
        }

        String email = modelo.getValueAt(linha, 1).toString(); 
        try {
            AlunoDAO dao = new AlunoDAO();
            Aluno aluno = dao.buscarPorEmail(email);
            new TelaEditarAluno(this, aluno);
            carregarAlunos(); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void excluirAlunoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir");
            return;
        }

        String email = modelo.getValueAt(linha, 1).toString();
        try {
            AlunoDAO dao = new AlunoDAO();
            Aluno aluno = dao.buscarPorEmail(email);
            dao.remover(aluno);
            carregarAlunos(); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
