package br.com.polishow.persistencia;

import br.com.polishow.modelo.Aluno;
import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {

    public void criarCadastro(Aluno a) throws Exception {
        String sqlUsuario = "INSERT INTO tb_usuario VALUES(null, ?, ?, ?, false)";
        String sqlAluno = "INSERT INTO tb_aluno VALUES(?, ?)";

        try (
            var conexao = new ConnectionFactory().obterConexao();
            var psUsuario = conexao.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)
        ) {
            psUsuario.setString(1, a.getNomeUsuario());
            psUsuario.setString(2, a.getEmailUsuario());
            psUsuario.setString(3, a.getSenhaUsuario());
            psUsuario.executeUpdate();

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
        new UsuarioDAO().remover(a);
    }

    public void atualizar(Aluno a) throws Exception {
        new UsuarioDAO().atualizar(a);

        String sql = "UPDATE tb_aluno SET serie = ? WHERE id_usuario_aluno = ?";
        try (
            var conexao = new ConnectionFactory().obterConexao();
            var ps = conexao.prepareStatement(sql)
        ) {
            ps.setString(1, a.getSerie());
            ps.setInt(2, a.getIdUsuario());
            ps.executeUpdate();
        }
    }

    public void listar() throws Exception {
        String sql = """
            SELECT u.nome_usuario, u.senha_usuario, u.email_usuario, u.adm, a.serie
            FROM tb_usuario u
            INNER JOIN tb_aluno a ON u.id_usuario = a.id_usuario_aluno
        """;

        try (
            var conexao = new ConnectionFactory().obterConexao();
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                var nome = rs.getString("nome_usuario");
                var senha = rs.getString("senha_usuario");
                var email = rs.getString("email_usuario");
                var adm = rs.getInt("adm");
                var serie = rs.getString("serie");

                System.out.printf("%s %s %s %d %s%n", nome, senha, email, adm, serie);
            }
        }
    }

    public ArrayList<Aluno> buscarTodos() throws Exception {
        ArrayList<Aluno> lista = new ArrayList<>();
        String sql = """
            SELECT u.id_usuario, u.nome_usuario, u.senha_usuario, u.email_usuario, a.serie
            FROM tb_usuario u
            INNER JOIN tb_aluno a ON u.id_usuario = a.id_usuario_aluno
        """;

        try (
            var conexao = new ConnectionFactory().obterConexao();
            var ps = conexao.prepareStatement(sql);
            var rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setIdUsuario(rs.getInt("id_usuario"));
                a.setNomeUsuario(rs.getString("nome_usuario"));
                a.setEmailUsuario(rs.getString("email_usuario"));
                a.setSenhaUsuario(rs.getString("senha_usuario"));
                a.setSerie(rs.getString("serie"));
                lista.add(a);
            }
        }

        return lista;
    }

    public Aluno buscarPorEmail(String email) throws Exception {
        String sql = """
            SELECT u.id_usuario, u.nome_usuario, u.senha_usuario, u.email_usuario, a.serie
            FROM tb_usuario u
            INNER JOIN tb_aluno a ON u.id_usuario = a.id_usuario_aluno
            WHERE u.email_usuario = ?
        """;

        try (
            var conexao = new ConnectionFactory().obterConexao();
            var ps = conexao.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aluno a = new Aluno();
                    a.setIdUsuario(rs.getInt("id_usuario"));
                    a.setNomeUsuario(rs.getString("nome_usuario"));
                    a.setEmailUsuario(rs.getString("email_usuario"));
                    a.setSenhaUsuario(rs.getString("senha_usuario"));
                    a.setSerie(rs.getString("serie"));
                    return a;
                }
            }
        }

        return null;
    }
}
