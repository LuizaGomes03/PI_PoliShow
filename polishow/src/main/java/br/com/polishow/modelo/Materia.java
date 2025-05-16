package br.com.polishow.modelo;

public class Materia {
    private int id;
    private String nome;

    public Materia(String nome) {
        this.nome = nome;
    }

    public int getIdMateria() {
        return id;
    }

    public void setIdMateria(int id) {
        this.id = id;
    }

    public String getNomeMateria() {
        return nome;
    }

    public void setNomeMateria(String nome) {
        this.nome = nome;
    }
}
