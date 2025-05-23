package br.com.polishow.modelo;

public class Alternativas {

    private int id;
    private Questao questao;
    private String alternativa;
    private Boolean correta;

    // Construtor com ID (para carregar do banco)
    // public Alternativa(int idAlternativa, Questao questao, String alternativa) {
    //     this.id = id;
    //     this.questao = questao;
    //     this.alternativa = alternativa;
    // }


    public Alternativas() {
    // Construtor padrão necessário para setIdAlternativa
}

    public Alternativas(Questao questao, String alternativa, Boolean correta) {
        this.questao = questao;
        this.alternativa = alternativa;
        this.correta = correta;
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
    
    public Boolean isCorreta(){
        return correta;
    }

    public void setCorreta(Boolean correta){
        this.correta = correta;
    }
}
