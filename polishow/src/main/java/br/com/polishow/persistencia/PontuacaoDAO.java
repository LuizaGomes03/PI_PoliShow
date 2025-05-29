package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Partida;
import br.com.polishow.modelo.Pontuacao;
import br.com.polishow.modelo.Usuario;

public class PontuacaoDAO {

    public void salvar(Pontuacao p) throws Exception {
        var c = new ConnectionFactory();
        String sql = "INSERT INTO tb_pontuacao (id_usuario, id_materia, id_partida, pontos) VALUES (?, ?, ?, ?)";
        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, p.getUsuario().getIdUsuario());
            ps.setInt(2, p.getMateria().getIdMateria());
            ps.setInt(3, p.getPartida().getIdPartida());
            ps.setDouble(4, p.getPontos());
            ps.executeUpdate();
        }
    }

    public List<Pontuacao> listar() throws Exception {
        List<Pontuacao> lista = new ArrayList<>();
        var c = new ConnectionFactory();

        String sql = """
                    SELECT p.id_pontuacao, p.pontos,
                           u.id_usuario, u.nome_usuario AS nome_usuario, u.email_usuario,
                           a.serie,
                           m.id_materia, m.nome_materia AS nome_materia,
                           pa.id_partida
                    FROM tb_pontuacao p
                    JOIN tb_usuario u ON p.id_usuario = u.id_usuario
                    JOIN tb_aluno a ON u.id_usuario = a.id_usuario_aluno
                    JOIN tb_materia m ON p.id_materia = m.id_materia
                    JOIN tb_partida pa ON p.id_partida = pa.id_partida
                """;

        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Pontuacao pontuacao = new Pontuacao();

                pontuacao.setidPontuacao(rs.getInt("id_pontuacao"));
                pontuacao.setPontos(rs.getDouble("pontos"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                pontuacao.setUsuario(usuario);

                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNomeMateria(rs.getString("nome_materia"));
                pontuacao.setMateria(materia);

                Partida partida = new Partida();
                partida.setIdPartida(rs.getInt("id_partida"));
                pontuacao.setPartida(partida);

                Aluno aluno = new Aluno();
                aluno.setIdUsuario(usuario.getIdUsuario());
                aluno.setSerie(rs.getString("serie"));
                pontuacao.setAluno(aluno);

                lista.add(pontuacao);
            }
        }

        return lista;
    }

}
