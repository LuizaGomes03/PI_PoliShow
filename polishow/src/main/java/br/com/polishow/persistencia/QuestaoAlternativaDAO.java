package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Alternativas;
import br.com.polishow.modelo.Questao;
import br.com.polishow.modelo.QuestaoAlternativa;

public class QuestaoAlternativaDAO {

    public void cadastrar(QuestaoAlternativa qalt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_questao_alternativa(id_questao, id_alternativa, pontos, dificuldade) VALUES (?, ?, ?, ?)";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, qalt.getQuestao().getIdQuestao());
            ps.setInt(2, qalt.getAlternativa().getIdAlternativa());
            ps.setInt(3, qalt.getPontos());
            ps.setString(4, qalt.getDificuldade());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    qalt.setIdQuestAlter(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void remover(QuestaoAlternativa qalt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "DELETE FROM tb_questao_alternativa WHERE id_quest_alter = ?";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, qalt.getIdQuestAlter());
            ps.executeUpdate();
        }
    }

    public void atualizar(QuestaoAlternativa qalt) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_questao_alternativa SET id_questao = ?, id_alternativa = ?, pontos = ?, dificuldade = ? WHERE id_quest_alter = ?";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, qalt.getQuestao().getIdQuestao());
            ps.setInt(2, qalt.getAlternativa().getIdAlternativa());
            ps.setInt(3, qalt.getPontos());
            ps.setString(4, qalt.getDificuldade());
            ps.setInt(5, qalt.getIdQuestAlter());
            ps.executeUpdate();
        }
    }

    public List<QuestaoAlternativa> listar(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "SELECT q.id_quest_alter, q.id_questao, q.id_alternativa, q.pontos, q.dificuldade, " +
                  "a.alternativa FROM tb_questao_alternativa q " +
                  "JOIN tb_alternativas a ON q.id_alternativa = a.id_alternativa " +
                  "WHERE q.id_questao = ?";

        List<QuestaoAlternativa> lista = new ArrayList<>();

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, q.getIdQuestao());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alternativas alt = new Alternativas(q, rs.getString("alternativa"));
                    alt.setIdAlternativa(rs.getInt("id_alternativa"));

                    QuestaoAlternativa qalt = new QuestaoAlternativa(
                        q,
                        alt,
                        rs.getInt("pontos"),
                        rs.getString("dificuldade")
                    );
                    qalt.setIdQuestAlter(rs.getInt("id_quest_alter"));
                    lista.add(qalt);
                }
            }
        }
        return lista;
    }

    
}
