package br.gov.cesarschool.poo.bonusvendas.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ExcecaoValidacao extends Exception {
    private List<String> errosValidacao = new ArrayList<>();

    public ExcecaoValidacao(String mensagem) {
        super(mensagem);
    }

    public ExcecaoValidacao(List<String> errosValidacao) {
        if (errosValidacao != null) {
            this.errosValidacao = errosValidacao;
        }
    }

    public List<String> getErrosValidacao() {
        return errosValidacao;
    }
}
