package br.gov.cesarschool.poo.bonusvendas.entidade.geral;

import java.io.Serializable;
import br.gov.cesarschool.poo.bonusvendas.entidade.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Registro implements Serializable {

    private List<LancamentoBonus> lancamentos;
    private Vendedor vendedor;
    private Endereco endereco;
    private Sexo sexo;
    private CaixaDeBonus caixaDeBonus;

    public Registro() {
        lancamentos = new ArrayList<>();
    }

    public abstract String getIdUnico();

    public void adicionarLancamento(LancamentoBonus lancamento) {
        lancamentos.add(lancamento);
    }

    public void definirVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void definirEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void definirSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void definirCaixaDeBonus(CaixaDeBonus caixaDeBonus) {
        this.caixaDeBonus = caixaDeBonus;
    }

    public List<LancamentoBonus> getLancamentos() {
        return lancamentos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public CaixaDeBonus getCaixaDeBonus() {
        return caixaDeBonus;
    }
}
