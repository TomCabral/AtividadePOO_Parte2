package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.Registro;

public class DAOGenerico<T extends Registro> {

    private CadastroObjetos cadastro;

    public DAOGenerico(Class<T> tipo) {
        this.cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(T reg) {
        if (buscar(reg.getIdUnico()) != null) {
            return false;
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
            return true;
        }
    }

    public boolean alterar(T reg) {
        if (buscar(reg.getIdUnico()) == null) {
            return false;
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
            return true;
        }
    }

    public T buscar(String id) {
        return (T) cadastro.buscar(id);
    }

    public T[] buscarTodos() {
        Object[] objs = cadastro.buscarTodos();
        T[] registros = (T[]) java.lang.reflect.Array.newInstance(objs.getClass().getComponentType(), objs.length);
        for (int i = 0; i < objs.length; i++) {
            registros[i] = (T) objs[i];
        }
        return registros;
    }
}
