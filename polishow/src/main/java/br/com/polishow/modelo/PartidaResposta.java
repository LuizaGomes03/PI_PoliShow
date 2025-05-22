package br.com.polishow.modelo;

public class PartidaResposta {

    private Partida partida;
    private Questao questao;
    private Alternativas alternativaSelecionada;

    public PartidaResposta(Partida partida, Questao questao, Alternativas alternativaSelecionada) {
        this.partida = partida;
        this.questao = questao;
        this.alternativaSelecionada = alternativaSelecionada;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public Alternativas getAlternativaSelecionada() {
        return alternativaSelecionada;
    }

    public void setAlternativaSelecionada(Alternativas alternativaSelecionada) {
        this.alternativaSelecionada = alternativaSelecionada;
    }
}
