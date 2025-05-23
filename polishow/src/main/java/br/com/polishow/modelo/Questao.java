package br.com.polishow.modelo;

public class Questao {

    private int id;
    private Materia materia;
    private String pergunta;
    private String dificuldade;

    // Construtor com ID (útil ao carregar do banco)
    // public Questao(int idQuestao, Materia materia, String pergunta) {
    // this.id = id;
    // this.materia = materia;
    // this.pergunta = pergunta;
    // }

    // Construtor sem ID (útil antes de salvar no banco)
    public Questao(Materia materia, String pergunta, String dificuldade) {
        this.materia = materia;
        this.pergunta = pergunta;
        this.dificuldade = dificuldade;
    }

    public Questao() {
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

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return String.format("[%s] (%s) %s", materia.getNomeMateria(), dificuldade, pergunta);
    }

}
