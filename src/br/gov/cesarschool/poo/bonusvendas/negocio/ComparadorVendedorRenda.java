package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class ComparadorVendedorRenda implements Comparator<Vendedor> {

    private static ComparadorVendedorRenda instance;

    private ComparadorVendedorRenda() {
    }

    public static ComparadorVendedorRenda getInstance() {
    	if (instance == null) {
			instance = new ComparadorVendedorRenda();
		}
        return instance;
    }

    // Método de comparação
    @Override
    public int compare(Vendedor vendedor1, Vendedor vendedor2) {
        double renda1 = vendedor1.getRenda();
        double renda2 = vendedor2.getRenda();

        // Comparação das rendas
        if (renda1 < renda2) {
            return -1;
        } else if (renda1 > renda2) {
            return 1;
        } else {
            return 0;
        }
    }
}