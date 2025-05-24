package br.com.polishow.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Materia;
import br.com.polishow.modelo.Pontuacao;
import br.com.polishow.modelo.Usuario;

public class PontuacaoDAO {
    public void salvar(Pontuacao p) throws Exception {
        var c = new ConnectionFactory();
        String sql = "INSERT INTO tb_pontuacao (id_usuario, id_materia, pontos) VALUES (?, ?, ?)";
        try (
                var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, p.getUsuario().getIdUsuario());
            ps.setInt(2, p.getMateria().getIdMateria());
            ps.setDouble(3, p.getPontos());
            ps.executeUpdate();
        }
    }

    public List<Pontuacao> listar() throws Exception {
        List<Pontuacao> lista = new ArrayList<>();

        var c = new ConnectionFactory();

        String sql = "SELECT p.id_pontuacao, p.pontos, " +
                "u.id_usuario, u.nome AS nome_usuario, " +
                "m.id_materia, m.nome AS nome_materia " +
                "FROM tb_pontuacao p " +
                "JOIN tb_usuario u ON p.id_usuario = u.id_usuario " +
                "JOIN tb_materia m ON p.id_materia = m.id_materia";

        try (var conexao = c.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pontuacao pontuacao = new Pontuacao();

                pontuacao.setidPontuacao(rs.getInt("id_pontuacao"));
                pontuacao.setPontos(rs.getInt("pontos"));

                // Criar e setar o objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                pontuacao.setUsuario(usuario);

                // Criar e setar o objeto Materia
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNomeMateria(rs.getString("nome_materia"));
                pontuacao.setMateria(materia);

                lista.add(pontuacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
