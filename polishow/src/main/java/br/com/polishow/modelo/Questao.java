package br.com.polishow.modelo;

public class Questao {

    private int id;
    private Materia materia;
    private String pergunta;

    // Construtor com ID (útil ao carregar do banco)
    // public Questao(int idQuestao, Materia materia, String pergunta) {
    //     this.id = id;
    //     this.materia = materia;
    //     this.pergunta = pergunta;
    // }

    // Construtor sem ID (útil antes de salvar no banco)
    public Questao(Materia materia, String pergunta) {
        this.materia = materia;
        this.pergunta = pergunta;
    }

    public int getIdQuestao() {
        return id;
    }

    public void setIdQuestao(int idQuestao) {
        this.id = idQuestao;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
