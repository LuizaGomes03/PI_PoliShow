package br.com.polishow;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private int adm;

    Usuario(String nome, String senha, String email, int adm){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.adm = adm;
    }

    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int idUsuario) {
        this.id = idUsuario;
    }

    public String getNomeUsuario() {
        return nome;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nome = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senha;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senha = senhaUsuario;
    }

    public String getEmailUsuario() {
        return email;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.email = emailUsuario;
    }

    public int getAdm() {
        return adm;
    }

    public void setAdm(int adm) {
        this.adm = adm;
    }
}
