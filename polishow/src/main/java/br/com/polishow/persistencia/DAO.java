package br.com.polishow.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.polishow.modelo.Aluno;
import br.com.polishow.modelo.Usuario;

public class DAO {
     public boolean existeLogin (Usuario u) throws Exception {
        var sql = "SELECT email_usuario, senha_usuario FROM tb_usuario WHERE email_usuario=? AND senha_usuario = ?";
        
        try(
            var conexao = new ConnectionFactory().obterConexao();  
            var ps = conexao.prepareStatement(sql);
            
        ){
            ps.setString(1, u.getEmailUsuario());
            ps.setString(2, u.getSenhaUsuario());
             try(
                 ResultSet rs = ps.executeQuery();
                     
            ){
                 return rs.next ();
            }
        }
       
    }
    public void criarCadastroAluno(Aluno a) throws Exception {
    var sqlUsuario = "INSERT INTO tb_usuario VALUES(null, ?, ?, ?, false)";
    var sqlAluno = "INSERT INTO tb_aluno VALUES(?, ?)";

    try (
        var conexao = new ConnectionFactory().obterConexao();
        var psUsuario = conexao.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)
    ) {
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

    public void criarCadastroAdm(Usuario u) throws Exception{
        var sql = "INSERT INTO tb_usuario VALUES(null, ?, ?, ?, true)";

        try(
            var conexao = new ConnectionFactory().obterConexao();
            var ps = conexao.prepareStatement(sql)
        ){
            ps.setString(1, u.getNomeUsuario());
            ps.setString(2, u.getEmailUsuario());
            ps.setString(3, u.getSenhaUsuario());
            ps.executeUpdate();
        }
    }

}
