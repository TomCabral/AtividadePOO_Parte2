package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class ComparadorCaixaDeBonusSaldoDec implements Comparator<CaixaDeBonus> {

    private static ComparadorCaixaDeBonusSaldoDec instance;

    private ComparadorCaixaDeBonusSaldoDec() {
    }

    public static ComparadorCaixaDeBonusSaldoDec getInstance() {
    	if (instance == null) {
			instance = new ComparadorCaixaDeBonusSaldoDec();
		}
        return instance;
    }

    @Override
    public int compare(CaixaDeBonus caixa1, CaixaDeBonus caixa2) {
        double saldo1 = caixa1.getSaldo();
        double saldo2 = caixa2.getSaldo();

        if (saldo1 < saldo2) {
            return 1;
        } else if (saldo1 > saldo2) {
            return -1;
        } else {
            return 0;
        }
    }
}