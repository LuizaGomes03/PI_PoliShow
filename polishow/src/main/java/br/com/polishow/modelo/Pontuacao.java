package br.com.polishow.modelo;

public class Pontuacao {
    private int id;
    private Usuario usuario;
    private Materia materia;
    private double pontos;

    public Pontuacao(Usuario usuario, Materia materia, double pontos) {
        this.usuario = usuario;
        this.materia = materia;
        this.pontos = pontos;
    }

    public Pontuacao(){
        
    }

    public int getIdPontuacao() {
        return id;
    }

    public void setidPontuacao(int idPontuacao) {
        this.id = idPontuacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }
}
