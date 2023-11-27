
package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class CaixaDeBonusDAO {
	private static final String BRANCO = "";
	private DAOGenerico<CaixaDeBonus> dao = new DAOGenerico<>(CaixaDeBonus.class);

	public boolean incluir(CaixaDeBonus caixaBonus) {
		return dao.incluir(caixaBonus);
	}

	public boolean alterar(CaixaDeBonus caixaBonus) {
		return dao.alterar(caixaBonus);
	}

	public CaixaDeBonus buscar(long codigo) {
		return dao.buscar(BRANCO + codigo);
	}

	public CaixaDeBonus[] buscarTodos() {
		return dao.buscarTodos();
	}
}