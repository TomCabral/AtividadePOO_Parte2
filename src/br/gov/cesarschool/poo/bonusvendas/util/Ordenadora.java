package br.gov.cesarschool.poo.bonusvendas.util;

public class Ordenadora {

    private Ordenadora() {
        // Construtor privado para impedir instanciação da classe
    }

    public static void ordenar(Object[] lista, Comparador comp) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int k = i + 1; k < lista.length; k++) {
                // Comparação utilizando o método comparar do Comparador
                if (comp.comparar(lista[i], lista[k]) > 0) {
                    // Troca de posição caso o resultado da comparação seja maior que zero
                    trocarPosicao(lista, i, k);
                }
            }
        }
    }

    private static void trocarPosicao(Object[] lista, int i, int k) {
        Object temp = lista[i];
        lista[i] = lista[k];
        lista[k] = temp;
    }

}
