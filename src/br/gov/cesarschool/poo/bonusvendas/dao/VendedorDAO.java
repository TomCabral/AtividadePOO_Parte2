package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class VendedorDAO {
    private DAOGenerico<Vendedor> dao = new DAOGenerico<>(Vendedor.class);

    public boolean incluir(Vendedor vend) {
        return dao.incluir(vend);
    }

    public boolean alterar(Vendedor vend) {
        return dao.alterar(vend);
    }

    public Vendedor buscar(String cpf) {
        return dao.buscar(cpf);
    }

    public Vendedor[] buscarTodos() {
        return dao.buscarTodos();
    }
}