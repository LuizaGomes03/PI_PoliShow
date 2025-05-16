package br.com.polishow.modelo;

public class QuestaoAlternativa {

    private int id;
    private Questao questao;
    private Alternativas alternativa;
    private int pontos;
    private String dificuldade; // valores válidos: "FACIL", "MEDIO", "DIFICIL"

    // Construtor com ID (carregamento do banco)
    // public QuestaoAlternativa(int idQuestaoAlter, Questao questao, Alternativas alternativa, int pontos, String dificuldade) {
    //     this.id = id;
    //     this.questao = questao;
    //     this.alternativa = alternativa;
    //     this.pontos = pontos;
    //     setDificuldade(dificuldade); // usa setter para validar
    // }

    // Construtor sem ID (para inserção)
    public QuestaoAlternativa(Questao questao, Alternativas alternativa, int pontos, String dificuldade) {
        this.questao = questao;
        this.alternativa = alternativa;
        this.pontos = pontos;
        setDificuldade(dificuldade); // usa setter para validar
    }

    public int getIdQuestAlter() {
        return id;
    }

    public void setIdQuestAlter(int idQuestAlter) {
        this.id = idQuestAlter;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public Alternativas getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(Alternativas alternativa) {
        this.alternativa = alternativa;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        String upper = dificuldade.toUpperCase();
        if (!upper.equals("FACIL") && !upper.equals("MEDIO") && !upper.equals("DIFICIL")) {
            throw new IllegalArgumentException("Dificuldade deve ser 'FACIL', 'MEDIO' ou 'DIFICIL'");
        }
        this.dificuldade = upper;
    }
}
