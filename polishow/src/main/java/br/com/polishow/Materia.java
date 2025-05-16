package br.com.polishow;

public class Materia {
    private int id;
    private String titulo;
    
    Materia(String titulo){
        this.titulo = titulo;
    }

    public int getIdMateria(){
        return id;
    }

    public String getNomeMateria(){
        return titulo;
    }
}
