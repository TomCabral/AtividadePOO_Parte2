package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class DAOGenerico<T extends Registro> {

    private CadastroObjetos cadastro;
    private String nomeEntidade;

    public DAOGenerico(Class<T> tipo, String nomeEntidade) {
        this.cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(T reg) throws ExcecaoObjetoJaExistente {
        if (cadastro.buscar(reg.getIdUnico()) != null) {
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
        }
    }

    public void alterar(T reg) throws ExcecaoObjetoNaoExistente {
        if (cadastro.buscar(reg.getIdUnico()) == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
        }
    }

    public T buscar(String id) throws ExcecaoObjetoNaoExistente {
        T result = (T) cadastro.buscar(id);
        if (result == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        }
        return result;
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
