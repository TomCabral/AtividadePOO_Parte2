package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.Registro;

public class DAOGenerico {

    private CadastroObjetos cadastro;

    public DAOGenerico(Class<?> tipo) {
        this.cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(Registro reg) {
        if (buscar(reg.getIdUnico()) != null) {
            return false;
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
            return true;
        }
    }

    public boolean alterar(Registro reg) {
        if (buscar(reg.getIdUnico()) == null) {
            return false;
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
            return true;
        }
    }

    public Registro buscar(String id) {
        return (Registro) cadastro.buscar(id);
    }

    public Registro[] buscarTodos() {
        Object[] objs = cadastro.buscarTodos();
        Registro[] registros = new Registro[objs.length];
        for (int i = 0; i < objs.length; i++) {
            registros[i] = (Registro) objs[i];
        }
        return registros;
    }
}
