package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDateTime;
import java.util.Comparator;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class ComparadorLancamentoBonusDHDec implements Comparator<Object> {

    private static final ComparadorLancamentoBonusDHDec instance = new ComparadorLancamentoBonusDHDec();

    private ComparadorLancamentoBonusDHDec() {
    }

    // Método estático para obter a instância única
    public static ComparadorLancamentoBonusDHDec getInstance() {
        return instance;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (!(o1 instanceof LancamentoBonus) || !(o2 instanceof LancamentoBonus)) {
            throw new IllegalArgumentException("Os objetos comparados devem ser do tipo LancamentoBonus");
        }

        LancamentoBonus lancamento1 = (LancamentoBonus) o1;
        LancamentoBonus lancamento2 = (LancamentoBonus) o2;

        LocalDateTime dataHoraLancamento1 = lancamento1.getDataHoraLancamento();
        LocalDateTime dataHoraLancamento2 = lancamento2.getDataHoraLancamento();

        // Comparação invertida para ordem decrescente
        return dataHoraLancamento2.compareTo(dataHoraLancamento1);
    }
}