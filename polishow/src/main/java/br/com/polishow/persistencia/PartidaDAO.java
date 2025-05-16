package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Partida;
import br.com.polishow.modelo.Usuario;

public class PartidaDAO {

    public void cadastrar(Partida partida) throws Exception {
    var fabricaDeConexoes = new ConnectionFactory();
    var sql = "INSERT INTO tb_partida (id_usuario_partida) VALUES (?)";
    try (
        var conexao = fabricaDeConexoes.obterConexao();
        var ps = conexao.prepareStatement(sql);
    ) {
        ps.setInt(1, partida.getUsuario().getIdUsuario());
        ps.execute();
    }
}

    public void remover(Partida partida) throws Exception {
        var c = new ConnectionFactory();
        var sql = "DELETE FROM tb_partida WHERE id_partida = ?";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, partida.getIdPartida());
            ps.executeUpdate();
        }
    }

    public void atualizar(Partida partida) throws Exception {
        var c = new ConnectionFactory();
        var sql = "UPDATE tb_partida SET id_usuario = ? WHERE id_partida = ?";

        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, partida.getUsuario().getIdUsuario());
            ps.setInt(2, partida.getIdPartida());
            ps.executeUpdate();
        }
    }

    public List<Partida> listar() throws Exception {
    var partidas = new ArrayList<Partida>();
    var fabricaDeConexoes = new ConnectionFactory();
    var sql = "SELECT id_partida, id_usuario_partida FROM tb_partida";
    var usuarioDAO = new UsuarioDAO();

    try (
        var conexao = fabricaDeConexoes.obterConexao();
        var ps = conexao.prepareStatement(sql);
        var rs = ps.executeQuery();
    ) {
        while (rs.next()) {
            int idPartida = rs.getInt("id_partida");
            int idUsuario = rs.getInt("id_usuario_partida");
            Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
            Partida partida = new Partida(usuario);
            partida.setIdPartida(idPartida);
            partidas.add(partida);
        }
    }
    return partidas;
}



}
