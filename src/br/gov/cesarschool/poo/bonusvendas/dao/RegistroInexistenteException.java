package br.gov.cesarschool.poo.bonusvendas.dao;

public class RegistroInexistenteException extends Exception {
    public RegistroInexistenteException(String message) {
        super(message);
    }
}