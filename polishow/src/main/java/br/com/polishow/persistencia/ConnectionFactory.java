package br.com.polishow.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionFactory {
    private String host = "mysqlpolishow-pii-polishow.i.aivencloud.com";
    private String port = "16580";
    private String db = "polishow";
    private String user = "avnadmin";
    private String password = "AVNS_IKqj4WNCJEoAA_Ig9J3";
    
    
    public Connection obterConexao() throws Exception{ //declare
        var s = String.format(
                "jdbc:mysql://%s:%s/%s",
                host, port, db
        );
        //cláusula catch or declare
        Connection c = DriverManager.getConnection(s, user, password);
        return c;
    }
    
    public static void main(String[] args) throws Exception{
        var fabricaDeConexoes = new ConnectionFactory();
        //cláusula catch or declare
        Connection conexao = fabricaDeConexoes.obterConexao();
        if(conexao != null){
            System.out.println("Conectou");
        }
        else{
            System.out.println("Não conectou");
        }
    }
}
