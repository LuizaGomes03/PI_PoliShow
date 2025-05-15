package br.com.polishow;

public class Materia {
    private int idMateria;
    private String nomeMateria;
    
    Materia(String nomeMateria){
        this.nomeMateria = nomeMateria;
    }

    public int getIdMateria(){
        return idMateria;
    }

    public String getNomeMateria(){
        return nomeMateria;
    }
}
