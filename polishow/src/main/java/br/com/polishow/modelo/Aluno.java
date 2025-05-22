package br.com.polishow.modelo;

public class Aluno extends Usuario {

    private String serie;

    // Construtor padrão (vazio)
    // public Aluno() {
    //     super("", "", "", 0); // valores padrão temporários
    //     this.serie = "";
    // }

    // Construtor completo
    public Aluno(String nome, String email, String senha, int adm, String serie) {
        super(nome, email, senha, adm);
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
