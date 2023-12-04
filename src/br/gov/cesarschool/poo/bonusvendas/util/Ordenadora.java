package br.gov.cesarschool.poo.bonusvendas.util;

public class Ordenadora {

    private Ordenadora() {
    }

    public static void ordenar(Object[] lista, Comparador comparador) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int k = i + 1; k < lista.length; k++) {
                if (comparador.comparar(lista[i], lista[k]) > 0) {
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
