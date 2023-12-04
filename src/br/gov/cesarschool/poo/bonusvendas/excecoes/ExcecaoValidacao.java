package br.gov.cesarschool.poo.bonusvendas.excecoes;

import java.util.List;

public class ExcecaoValidacao extends Exception {
    private List<ErroValidacao> errosValidacao;

    public ExcecaoValidacao(String mensagem) {
        super(mensagem);
    }

    public ExcecaoValidacao(List<ErroValidacao> errosValidacao) {
        super("Erros de validação ocorreram");
        this.errosValidacao = errosValidacao;
    }

    public List<ErroValidacao> getErrosValidacao() {
        return errosValidacao;
    }
}
