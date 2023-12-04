package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;
import java.util.Comparator;

public class ComparadorCaixaDeBonusSaldoDec implements Comparador, Comparator<CaixaDeBonus> {

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
    public int comparar(Object o1, Object o2) {
        if (!(o1 instanceof CaixaDeBonus) || !(o2 instanceof CaixaDeBonus)) {
            throw new IllegalArgumentException("Os objetos devem ser do tipo CaixaDeBonus");
        }
        CaixaDeBonus caixa1 = (CaixaDeBonus) o1;
        CaixaDeBonus caixa2 = (CaixaDeBonus) o2;
        return Double.compare(caixa2.getSaldo(), caixa1.getSaldo());
    }

    @Override
    public int compare(CaixaDeBonus caixa1, CaixaDeBonus caixa2) {
        return Double.compare(caixa2.getSaldo(), caixa1.getSaldo());
    }
}
