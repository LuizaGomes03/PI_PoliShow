package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.PartidaResposta;
import br.com.polishow.modelo.Questao;
import br.com.polishow.modelo.Partida;
import br.com.polishow.modelo.Alternativas;

public class PartidaRespostaDAO {

    public void cadastrar(PartidaResposta pr) throws Exception {
        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_partida_resposta(id_partida, id_questao, id_alternativa_selecionada) VALUES (?, ?, ?)";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, pr.getPartida().getIdPartida());
            ps.setInt(2, pr.getQuestao().getIdQuestao());
            ps.setInt(3, pr.getAlternativaSelecionada().getIdAlternativa());
            ps.executeUpdate();
        }
    }



    public void atualizar(PartidaResposta pr) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_partida_resposta SET id_alternativa_selecionada = ? WHERE id_partida = ? AND id_questao = ?";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, pr.getAlternativaSelecionada().getIdAlternativa());
            ps.setInt(2, pr.getPartida().getIdPartida());
            ps.setInt(3, pr.getQuestao().getIdQuestao());
            ps.executeUpdate();
        }
    }

    public List<PartidaResposta> listarPorPartida(int idPartida) throws Exception {
        var c = new ConnectionFactory();
        var sql = "SELECT * FROM tb_partida_resposta WHERE id_partida = ?";
        List<PartidaResposta> respostas = new ArrayList<>();

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, idPartida);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Partida partida = new Partida();
                    partida.setIdPartida(rs.getInt("id_partida"));

                    Questao qa = new Questao();
                    qa.setIdQuestao(rs.getInt("id_questao"));

                    Alternativas alt = new Alternativas();
                    alt.setIdAlternativa(rs.getInt("id_alternativa_selecionada"));

                    PartidaResposta pr = new PartidaResposta(partida, qa, alt);
                    respostas.add(pr);
                }
            }
        }

        return respostas;
    }
}
