package br.gov.cesarschool.poo.bonusvendas.util;

public interface Comparador {
    default int comparar(Object o1, Object o2) {
        if (o1.equals(o2)) {
            return 0;
        } else if (o1.hashCode() > o2.hashCode()) {
            return 1;
        } else {
            return -1;
        }
    }
}
