package br.com.polishow.persistencia;

import br.com.polishow.modelo.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AlunoDAO {

    public void cadastrar(Aluno a) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int idGerado = usuarioDAO.cadastrar(a);
        a.setIdUsuario(idGerado);

        var c = new ConnectionFactory();
        var sql = "INSERT INTO tb_aluno(id_usuario_aluno, serie) VALUES (?, ?)";
        try (
            var conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
        ) {
            ps.setInt(1, a.getIdUsuario());
            ps.setString(2, a.getSerie());
            ps.execute();
        }
    }

    public void remover(Aluno a) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.remover(a);
    }

    public void atualizar(Aluno a) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizar(a);

        var c = new ConnectionFactory();
        var sql = "UPDATE tb_aluno SET serie = ? WHERE id_usuario_aluno = ?";
        try (
            var conexao = c.obterConexao();
            var ps = conexao.prepareStatement(sql);
        ) {
            ps.setString(1, a.getSerie());
            ps.setInt(2, a.getIdUsuario());
            ps.executeUpdate();
        }
    }

    public void listar() throws Exception {
        var c = new ConnectionFactory();
        var sql = """
            SELECT u.nome_usuario, u.senha_usuario, u.email_usuario, u.adm, a.serie
            FROM tb_usuario u
            INNER JOIN tb_aluno a ON u.id_usuario = a.id_usuario_aluno """;

        try (
            var conexao = c.obterConexao();
            var ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                var nome = rs.getString("nome_usuario");
                var senha = rs.getString("senha_usuario");
                var email = rs.getString("email_usuario");
                var adm = rs.getInt("adm");
                var serie = rs.getString("serie");

                System.out.printf("%s %s %s %d %s", nome, senha, email, adm, serie);
            }
        }
    }
}
