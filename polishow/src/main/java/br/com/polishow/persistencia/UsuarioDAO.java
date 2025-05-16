package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.polishow.modelo.Usuario;

public class UsuarioDAO {

    public int cadastrar(Usuario usuario) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "INSERT INTO tb_usuario(nome_usuario, senha_usuario, email_usuario, adm) VALUES(?, ?, ?, ?)";
        
        try (
            var conexao = fabricaDeConexoes.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setString(3, usuario.getEmailUsuario());
            ps.setInt(4, usuario.getAdm());
            ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new Exception("Erro ao obter o ID gerado para o usuário.");
            }
        }
    }
}

    public void remover(Usuario usuario) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";
        try (
            var conexao = fabricaDeConexoes.obterConexao();
            var ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.execute();
        }
    }

    public void atualizar(Usuario usuario) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "UPDATE tb_usuario SET nome_usuario=?, senha_usuario=?, email_usuario=?, adm=? WHERE id_usuario = ?";
        try (
            var conexao = fabricaDeConexoes.obterConexao();
            var ps = conexao.prepareStatement(sql);
        ) {
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setString(3, usuario.getEmailUsuario());
            ps.setInt(4, usuario.getAdm());
            ps.setInt(5, usuario.getIdUsuario());
            ps.executeUpdate();
        }
    }

    public void listar() throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "SELECT * FROM tb_usuario";
        try (
            var conexao = fabricaDeConexoes.obterConexao();
            var ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                var nome_usuario = rs.getString("nome_usuario");
                var senha_usuario = rs.getString("senha_usuario");
                var email_usuario = rs.getString("email_usuario");
                var adm = rs.getInt("adm");
                System.out.printf("%s %s %s %d", nome_usuario, senha_usuario, email_usuario, adm);
            }
        }
    }

    public Usuario buscarPorId(int id) throws Exception {
    var fabricaDeConexoes = new ConnectionFactory();
    var sql = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
    
    try (
        var conexao = fabricaDeConexoes.obterConexao();
        var ps = conexao.prepareStatement(sql);
    ) {
        ps.setInt(1, id);
        try (var rs = ps.executeQuery()) {
            if (rs.next()) {
                String nome = rs.getString("nome_usuario");
                String senha = rs.getString("senha_usuario");
                String email = rs.getString("email_usuario");
                int adm = rs.getInt("adm");
                
                Usuario usuario = new Usuario(nome, senha, email, adm);
                usuario.setIdUsuario(id);
                return usuario;
            } else {
                return null;
            }
        }
    }
}

}
