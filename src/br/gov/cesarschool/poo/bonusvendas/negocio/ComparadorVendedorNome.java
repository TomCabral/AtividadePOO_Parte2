package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class ComparadorVendedorNome implements Comparator<Vendedor> {

    private static final ComparadorVendedorNome instance = new ComparadorVendedorNome();

    private ComparadorVendedorNome() {
    }

    public static ComparadorVendedorNome getInstance() {
        return instance;
    }

    // Método de comparação 
    @Override
    public int compare(Vendedor vendedor1, Vendedor vendedor2) {
        String nomeCompleto1 = vendedor1.getNomeCompleto();
        String nomeCompleto2 = vendedor2.getNomeCompleto();

        // Comparação dos nomes
        return nomeCompleto1.compareTo(nomeCompleto2);
    }
}