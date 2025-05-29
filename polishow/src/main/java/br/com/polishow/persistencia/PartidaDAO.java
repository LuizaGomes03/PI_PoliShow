package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Partida;
import br.com.polishow.modelo.Usuario;

public class PartidaDAO {

    public Partida criar(Usuario usuario) throws Exception {
        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_partida (id_usuario_partida) VALUES (?)";

        try (
            var conn = c.obterConexao();
            var ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                var idPartida = rs.getInt(1);
                var partida = new Partida();
                partida.setIdPartida(idPartida);
                partida.setUsuario(usuario);
                return partida;
            } else {
                throw new Exception("Falha ao obter ID da nova partida.");
            }
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
            var idPartida = rs.getInt("id_partida");
            var idUsuario = rs.getInt("id_usuario_partida");
            var usuario = usuarioDAO.buscarPorId(idUsuario);
            Partida partida = new Partida(usuario);
            partida.setIdPartida(idPartida);
            partidas.add(partida);
        }
    }
    return partidas;
}



}
