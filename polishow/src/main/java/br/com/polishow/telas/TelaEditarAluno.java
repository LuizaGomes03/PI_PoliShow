package br.com.polishow.telas;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.persistencia.AlunoDAO;

import javax.swing.*;
import java.awt.*;

public class TelaEditarAluno extends JDialog {

    private JTextField campoNome, campoEmail, campoSerie;
    private JPasswordField campoSenha;
    private final Aluno aluno;

    public TelaEditarAluno(JDialog parent, Aluno aluno) {
        super(parent, "Editar Aluno", true);
        this.aluno = aluno;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nome:"));
        campoNome = new JTextField(aluno.getNomeUsuario());
        add(campoNome);

        add(new JLabel("Email:"));
        campoEmail = new JTextField(aluno.getEmailUsuario());
        add(campoEmail);

        add(new JLabel("Senha:"));
        campoSenha = new JPasswordField(aluno.getSenhaUsuario());
        add(campoSenha);

        add(new JLabel("Série:"));
        campoSerie = new JTextField(aluno.getSerie());
        add(campoSerie);

        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> salvarAlteracoes());
        add(salvar);

        setVisible(true);
    }

    private void salvarAlteracoes() {
        aluno.setNomeUsuario(campoNome.getText());
        aluno.setEmailUsuario(campoEmail.getText());
        aluno.setSenhaUsuario(new String(campoSenha.getPassword()));
        aluno.setSerie(campoSerie.getText());

        try {
            new AlunoDAO().atualizar(aluno);
            JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno");
        }
    }
}
