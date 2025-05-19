package br.com.polishow.persistencia;

import java.sql.ResultSet;

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
    public void criarCadastroAluno(Aluno a) throws Exception{
        var sql = "INSERT INTO tb_usuario VALUES(null, ?, ?, ?, false) INSERT INTO tb_aluno VALUES (?, LAST_INSERT_ID())";
                

        try(
            var conexao = new ConnectionFactory().obterConexao();  
            var ps = conexao.prepareStatement(sql);
            
        ){
            ps.setString(1, a.getNomeUsuario());
            ps.setString(2, a.getSenhaUsuario());
            ps.setString(3, a.getEmailUsuario());
            ps.setString(4, a.getSerie());
            ps.executeUpdate();
        }
    }
}
