package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class LancamentoBonusDAO {
    private DAOGenerico<LancamentoBonus> dao = new DAOGenerico<>(LancamentoBonus.class, "Lancamento");

    public void incluir(LancamentoBonus lancamento) throws ExcecaoObjetoJaExistente {
        dao.incluir(lancamento);
    }

    public void alterar(LancamentoBonus lancamento) throws ExcecaoObjetoNaoExistente {
        dao.alterar(lancamento);
    }

    public LancamentoBonus buscar(String codigo) throws ExcecaoObjetoNaoExistente {
        return dao.buscar(codigo);
    }

    public LancamentoBonus[] buscarTodos() {
        return dao.buscarTodos();
    }
}
