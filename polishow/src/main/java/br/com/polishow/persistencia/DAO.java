package br.com.polishow.persistencia;

import java.sql.ResultSet;

import br.com.polishow.modelo.*;

public class DAO {
    public boolean existeLogin(Usuario u) throws Exception {
        var sql = "SELECT email_usuario, senha_usuario FROM tb_usuario WHERE email_usuario=? AND senha_usuario = ?";

        try (
                var conexao = new ConnectionFactory().obterConexao();
                var ps = conexao.prepareStatement(sql);

        ) {
            ps.setString(1, u.getEmailUsuario());
            ps.setString(2, u.getSenhaUsuario());
            try (
                    ResultSet rs = ps.executeQuery();

            ) {
                return rs.next();
            }
        }

    }

}
