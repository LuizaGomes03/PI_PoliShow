package br.com.polishow;

public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String emailUsuario;
    private int adm;

    Usuario(String nomeUsuario, String senhaUsuario, String emailUsuario, int adm){
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.emailUsuario = emailUsuario;
        this.adm = adm;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getAdm() {
        return adm;
    }

    public void setAdm(int adm) {
        this.adm = adm;
    }
}
