package br.com.polishow.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.polishow.modelo.Materia;

public class MateriaDAO {
    public void cadastrar(Materia m) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "INSERT INTO tb_materia(nome_materia) VALUES(?)";
        try (
                var conexao = fabricaDeConexoes.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setString(1, m.getNomeMateria());
            ps.execute();
        }
    }

    public void remover(Materia m) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "DELETE FROM tb_materia WHERE id_materia = ?";
        try (
                var conexao = fabricaDeConexoes.obterConexao();
                var ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, m.getIdMateria());
            ps.execute();
        }
    }

    public void atualizar(Materia m) throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "UPDATE tb_materia SET nome_materia=? WHERE id_materia = ?";
        try (
                var conexao = fabricaDeConexoes.obterConexao();
                var ps = conexao.prepareStatement(sql);) {
            ps.setString(1, m.getNomeMateria());
            ps.setInt(2, m.getIdMateria());
            ps.executeUpdate();
        }
    }

    public void listar() throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "SELECT * FROM tb_materia";
        try (
                var conexao = fabricaDeConexoes.obterConexao();
                var ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                var id = rs.getInt("id_materia");
                var nome = rs.getString("nome_materia");
                System.out.printf("%d - %s", id, nome);

            }
        }
    }

    public List<Materia> listarTodas() throws Exception {
        var fabricaDeConexoes = new ConnectionFactory();
        var sql = "SELECT * FROM tb_materia";
        List<Materia> materias = new ArrayList<>();

        try (
                var conexao = fabricaDeConexoes.obterConexao();
                var ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Materia m = new Materia();
                m.setIdMateria(rs.getInt("id_materia"));
                m.setNomeMateria(rs.getString("nome_materia"));
                materias.add(m);
            }
        }

        return materias;
    }
}
