package br.com.polishow;

public class Partida {

    private int idPartida;
    private Usuario usuario;

    // // Construtor alternativo sem ID (para inserções onde o ID é auto incrementado)
    public Partida(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
