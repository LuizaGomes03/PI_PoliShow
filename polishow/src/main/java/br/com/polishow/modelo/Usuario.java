package br.com.polishow.modelo;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private int adm;

    public Usuario(String nome, String email, String senha, int adm){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.adm = adm;
    }

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;

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
