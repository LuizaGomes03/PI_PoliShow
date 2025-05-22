package br.com.polishow.persistencia;

import br.com.polishow.modelo.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoDAO {

    public void criarCadastro(Aluno a) throws Exception {
        var sqlUsuario = "INSERT INTO tb_usuario VALUES(null, ?, ?, ?, false)";
        var sqlAluno = "INSERT INTO tb_aluno VALUES(?, ?)";

        try (
                var conexao = new ConnectionFactory().obterConexao();
                var psUsuario = conexao.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                psUsuario.setString(1, a.getNomeUsuario());
                psUsuario.setString(2, a.getSenhaUsuario());
                psUsuario.setString(3, a.getEmailUsuario());
                psUsuario.executeUpdate();

            // Obter o ID gerado
            try (var rs = psUsuario.getGeneratedKeys()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);

                    try (var psAluno = conexao.prepareStatement(sqlAluno)) {
                        psAluno.setInt(1, idUsuario);
                        psAluno.setString(2, a.getSerie());
                        psAluno.executeUpdate();
                    }
                } else {
                    throw new SQLException("Erro ao obter o ID do usuário inserido.");
                }
            }
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
                var ps = conexao.prepareStatement(sql);) {
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
                ResultSet rs = ps.executeQuery();) {
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
