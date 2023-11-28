package br.gov.cesarschool.poo.bonusvendas.util;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class Ordenadora {

    private Ordenadora() {
        // Construtor privado para impedir instanciação da classe
    }

    public static void ordenar(CaixaDeBonus[] lista, Comparador ComparadorCaixaDeBonusSaldoDec) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int k = i + 1; k < lista.length; k++) {
                // Comparação utilizando o método comparar do Comparador
                if (ComparadorCaixaDeBonusSaldoDec.comparar(lista[i], lista[k]) > 0) {
                    // Troca de posição caso o resultado da comparação seja maior que zero
                    trocarPosicao(lista, i, k);
                }
            }
        }
    }

    private static void trocarPosicao(CaixaDeBonus[] lista, int i, int k) {
        CaixaDeBonus temp = lista[i];
        lista[i] = lista[k];
        lista[k] = temp;
    }

}
