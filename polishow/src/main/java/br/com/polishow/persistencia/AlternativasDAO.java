package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Alternativas;
import br.com.polishow.modelo.Questao;

public class AlternativasDAO {

    public void cadastrar(Alternativas alt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_alternativas(id_questao, alternativa) VALUES (?, ?)";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, alt.getQuestao().getIdQuestao());
            ps.setString(2, alt.getAlternativa());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alt.setIdAlternativa(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void remover(Alternativas alt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "DELETE FROM tb_alternativas WHERE id_alternativa = ?";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, alt.getIdAlternativa());
            ps.executeUpdate();
        }
    }

    public void atualizar(Alternativas alt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_alternativas SET id_questao = ?, alternativa = ? WHERE id_alternativa = ?";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, alt.getQuestao().getIdQuestao());
            ps.setString(2, alt.getAlternativa());
            ps.setInt(3, alt.getIdAlternativa());
            ps.executeUpdate();
        }
    }

    public List<Alternativas> listar(Questao questao) throws Exception {
        var c = new ConnectionFactory();
        var sql = "SELECT id_alternativa, alternativa FROM tb_alternativas WHERE id_questao = ?";
        var lista = new ArrayList<Alternativas>();

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, questao.getIdQuestao());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var alt = new Alternativas(questao, rs.getString("alternativa"), rs.getBoolean("Correta"));
                    alt.setIdAlternativa(rs.getInt("id_alternativa"));
                    lista.add(alt);
                }
            }
        }

        return lista;
    }
}
