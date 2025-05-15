package br.com.polishow;

public class Questao {

    private int idQuestao;
    private Materia materia;
    private String pergunta;

    // Construtor com ID (útil ao carregar do banco)
    // public Questao(int idQuestao, Materia materia, String pergunta) {
    //     this.idQuestao = idQuestao;
    //     this.materia = materia;
    //     this.pergunta = pergunta;
    // }

    // Construtor sem ID (útil antes de salvar no banco)
    public Questao(Materia materia, String pergunta) {
        this.materia = materia;
        this.pergunta = pergunta;
    }

    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
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
