package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class ComparadorCaixaDeBonusSaldoDec implements Comparator<CaixaDeBonus> {

    private static final ComparadorCaixaDeBonusSaldoDec instance = new ComparadorCaixaDeBonusSaldoDec();

    private ComparadorCaixaDeBonusSaldoDec() {
    }

    public static ComparadorCaixaDeBonusSaldoDec getInstance() {
        return instance;
    }

    @Override
    public int compare(CaixaDeBonus caixa1, CaixaDeBonus caixa2) {
        double saldo1 = caixa1.getSaldo();
        double saldo2 = caixa2.getSaldo();

        return Double.compare(saldo2, saldo1);
    }
}