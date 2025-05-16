package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.polishow.modelo.Questao;

public class QuestaoDAO {

    public void cadastrar(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_questao(id_materia, pergunta) VALUES (?, ?)";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, q.getMateria().getIdMateria());
            ps.setString(2, q.getPergunta());
            ps.execute();
        }
    }

    public void remover(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "DELETE FROM tb_questao WHERE id_questao = ?";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, q.getIdQuestao());
            ps.execute();
        }
    }

    public void atualizar(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_questao SET id_materia = ?, pergunta = ? WHERE id_questao = ?";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, q.getMateria().getIdMateria());
            ps.setString(2, q.getPergunta());
            ps.setInt(3, q.getIdQuestao());
            ps.executeUpdate();
        }
    }

    public void listarQuestoes() throws Exception {
        var c = new ConnectionFactory();
        var sql = "SELECT q.id_questao, q.id_materia, q.pergunta, m.nome_materia FROM tb_questao q JOIN tb_materia m ON q.id_materia = m.id_materia";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id_questao");
                int idMateria = rs.getInt("id_materia");
                String pergunta = rs.getString("pergunta");
                String nomeMateria = rs.getString("nome_materia");

                System.out.printf("ID: %d | Matéria: %s (ID: %d) | Pergunta: %s%n", id, nomeMateria, idMateria, pergunta);
            }
        }
    }
}
