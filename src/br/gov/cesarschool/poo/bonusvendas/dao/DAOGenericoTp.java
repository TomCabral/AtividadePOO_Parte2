package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class DAOGenericoTp<T extends Registro> {

    private CadastroObjetos cadastro;
    private Class<T> tipo;

    public DAOGenericoTp(Class<T> tipo) {
        this.cadastro = new CadastroObjetos(tipo);
    }

    public void incluir(T reg) throws RegistroExistenteException {
        if (buscar(reg.getIdUnico()) != null) {
            throw new RegistroExistenteException("Já existe um registro com o ID único fornecido.");
        } else {
            cadastro.incluir(reg, reg.getIdUnico());
        }
    }

    public void alterar(T reg) throws RegistroInexistenteException {
        if (buscar(reg.getIdUnico()) == null) {
            throw new RegistroInexistenteException("Registro com o ID único fornecido não encontrado.");
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
        }
    }

    public T buscar(String id) {
        return (T) cadastro.buscar(id);
    }

    public T[] buscarTodos() {
        Object[] objs = cadastro.buscarTodos();
        T[] registros = (T[]) java.lang.reflect.Array.newInstance(tipo, objs.length);

        for (int i = 0; i < objs.length; i++) {
            registros[i] = (T) objs[i];
        }
        return registros;
    }
}