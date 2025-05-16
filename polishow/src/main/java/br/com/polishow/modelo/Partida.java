package br.com.polishow.modelo;

public class Partida {

    private int id;
    private Usuario usuario;

    // // Construtor alternativo sem ID (para inserções onde o ID é auto incrementado)
    public Partida(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdPartida() {
        return id;
    }

    public void setIdPartida(int idPartida) {
        this.id = idPartida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
