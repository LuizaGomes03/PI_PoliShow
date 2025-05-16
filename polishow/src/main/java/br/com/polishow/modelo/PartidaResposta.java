package br.com.polishow.modelo;

public class PartidaResposta {

    private Partida partida;
    private QuestaoAlternativa questaoAlternativa;
    private Alternativas alternativaSelecionada;

    public PartidaResposta(Partida partida, QuestaoAlternativa questaoAlternativa, Alternativas alternativaSelecionada) {
        this.partida = partida;
        this.questaoAlternativa = questaoAlternativa;
        this.alternativaSelecionada = alternativaSelecionada;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public QuestaoAlternativa getQuestaoAlternativa() {
        return questaoAlternativa;
    }

    public void setQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        this.questaoAlternativa = questaoAlternativa;
    }

    public Alternativas getAlternativaSelecionada() {
        return alternativaSelecionada;
    }

    public void setAlternativaSelecionada(Alternativas alternativaSelecionada) {
        this.alternativaSelecionada = alternativaSelecionada;
    }
}
