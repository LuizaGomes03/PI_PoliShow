package br.com.polishow;

public class Aluno extends Usuario {

    private String serie;

    // Construtor padrão (vazio)
    // public Aluno() {
    //     super("", "", "", 0); // valores padrão temporários
    //     this.serie = "";
    // }

    // Construtor completo
    public Aluno(String nomeUsuario, String senhaUsuario, String emailUsuario, int adm, String serie) {
        super(nomeUsuario, senhaUsuario, emailUsuario, adm);
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
