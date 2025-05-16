package br.com.polishow.modelo;

public class Alternativas {

    private int id;
    private Questao questao;
    private String alternativa;
    private String valorAlternativa; // valores possíveis: "CORRETA" ou "ERRADA"

    // Construtor com ID (para carregar do banco)
    // public Alternativa(int idAlternativa, Questao questao, String alternativa) {
    //     this.id = id;
    //     this.questao = questao;
    //     this.alternativa = alternativa;
    // }

    // Construtor sem ID (para inserção no banco)
    public Alternativas(Questao questao, String alternativa, String valorAlternativa) {
        this.questao = questao;
        this.alternativa = alternativa;
        this.valorAlternativa = valorAlternativa;
    }

    public int getIdAlternativa() {
        return id;
    }

    public void setIdAlternativa(int idAlternativa) {
        this.id = idAlternativa;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    
    public String getValorAlternativa() {
        return valorAlternativa;
    }

    public void setValorAlternativa(String valorAlternativa) {
        if (!valorAlternativa.equalsIgnoreCase("CORRETA") && !valorAlternativa.equalsIgnoreCase("ERRADA")) {
            throw new IllegalArgumentException("valorAlternativa deve ser 'CORRETA' ou 'ERRADA'");
        }
        this.valorAlternativa = valorAlternativa.toUpperCase();
    }
    
}
