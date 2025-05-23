package br.com.polishow.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.polishow.modelo.Alternativas;
import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Questao;

public class QuestaoDAO {

    public void cadastrar(Questao questao, List<Alternativas> alternativas, String letraCorreta) throws Exception {
        Connection conexao = null;
        PreparedStatement stmtQuestao = null;
        PreparedStatement stmtAlternativa = null;

        try {
            ConnectionFactory fabrica = new ConnectionFactory();
            conexao = fabrica.obterConexao();
            conexao.setAutoCommit(false);

            String sqlQuestao = "INSERT INTO tb_questao (id_materia, pergunta, dificuldade) VALUES (?, ?, ?)";
            stmtQuestao = conexao.prepareStatement(sqlQuestao, Statement.RETURN_GENERATED_KEYS);
            stmtQuestao.setInt(1, questao.getMateria().getIdMateria());
            stmtQuestao.setString(2, questao.getPergunta());
            stmtQuestao.setString(3, questao.getDificuldade());
            stmtQuestao.executeUpdate();

            ResultSet rs = stmtQuestao.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                questao.setIdQuestao(idGerado);
            }

            String sqlAlt = "INSERT INTO tb_alternativas (id_questao, alternativa, correta) VALUES (?, ?, ?)";
            stmtAlternativa = conexao.prepareStatement(sqlAlt);

            String[] letras = { "A", "B", "C", "D", "E" };

            for (int i = 0; i < alternativas.size(); i++) {
                Alternativas alt = alternativas.get(i);
                stmtAlternativa.setInt(1, questao.getIdQuestao());
                stmtAlternativa.setString(2, alt.getAlternativa());

                // compara a letra da alternativa com a correta selecionada
                boolean correta = letras[i].equalsIgnoreCase(letraCorreta);
                stmtAlternativa.setBoolean(3, correta);

                stmtAlternativa.addBatch();
            }

            stmtAlternativa.executeBatch();
            conexao.commit();

        } catch (SQLException e) {
            if (conexao != null)
                conexao.rollback();
            throw e;
        } finally {
            if (stmtAlternativa != null)
                stmtAlternativa.close();
            if (stmtQuestao != null)
                stmtQuestao.close();
            if (conexao != null)
                conexao.close();
        }
    }

    public void remover(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "DELETE FROM tb_questao WHERE id_questao = ?";
        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, q.getIdQuestao());
            ps.execute();
        }
    }

    public void atualizar(Questao q) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_questao SET id_materia = ?, pergunta = ? WHERE id_questao = ?";
        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
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
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("id_questao");
                int idMateria = rs.getInt("id_materia");
                String pergunta = rs.getString("pergunta");
                String nomeMateria = rs.getString("nome_materia");

                System.out.printf("ID: %d | Matéria: %s (ID: %d) | Pergunta: %s%n", id, nomeMateria, idMateria,
                        pergunta);
            }
        }
    }

    public List<Questao> buscarPorMateria(int idMateria) throws Exception {
        List<Questao> lista = new ArrayList<>();
        var c = new ConnectionFactory();
        var sql = "SELECT q.id_questao, q.id_materia, q.pergunta, q.dificuldade, m.nome_materia " +
                "FROM tb_questao q JOIN tb_materia m ON q.id_materia = m.id_materia " +
                "WHERE q.id_materia = ?";

        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Questao q = new Questao();
                q.setIdQuestao(rs.getInt("id_questao"));
                q.setPergunta(rs.getString("pergunta"));
                q.setDificuldade(rs.getString("dificuldade"));

                Materia m = new Materia();
                m.setIdMateria(rs.getInt("id_materia"));
                m.setNomeMateria(rs.getString("nome_materia"));

                q.setMateria(m);

                lista.add(q);
            }
        }

        return lista;
    }


    public List<Questao> buscar12Questoes(int idMateria) throws Exception {
    List<Questao> lista = new ArrayList<>();
    var c = new ConnectionFactory();

    String sql = "SELECT * FROM tb_questao WHERE id_materia = ? AND dificuldade = ? ORDER BY RAND() LIMIT 4";

    try (var conexao = c.obterConexao()) {
        String[] dificuldades = {"facil", "medio", "dificil"};

        for (String dificuldade : dificuldades) {
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, idMateria);
                ps.setString(2, dificuldade);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Questao q = new Questao();
                    q.setIdQuestao(rs.getInt("id_questao"));
                    q.setPergunta(rs.getString("pergunta"));
                    q.setDificuldade(rs.getString("dificuldade"));

                    Materia m = new Materia();
                    m.setIdMateria(rs.getInt("id_materia"));
                    q.setMateria(m);

                    lista.add(q);
                }
            }
        }
    }

    return lista;
}


}
