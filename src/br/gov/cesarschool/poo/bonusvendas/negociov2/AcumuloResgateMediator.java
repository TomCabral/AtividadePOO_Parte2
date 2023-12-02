package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.daov2.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.daov2.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;

public class AcumuloResgateMediator {
    private static AcumuloResgateMediator instancia;

    private CaixaDeBonusDAO repositorioCaixaDeBonus;
    private LancamentoBonusDAO repositorioLancamento;

    private AcumuloResgateMediator() {
        repositorioCaixaDeBonus = new CaixaDeBonusDAO();
        repositorioLancamento = new LancamentoBonusDAO();
    }

    public static AcumuloResgateMediator getInstancia() {
        if (instancia == null) {
            instancia = new AcumuloResgateMediator();
        }
        return instancia;
    }

    public long gerarCaixaDeBonus(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        long numero = Long.parseLong(vendedor.getCpf().substring(0, 9) + dataAtual.format(customFormatter));
        CaixaDeBonus caixa = new CaixaDeBonus(numero);
        repositorioCaixaDeBonus.incluir(caixa);
        return numero;
    }

    public void acumularBonus(long numeroCaixaDeBonus, double valor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
        if (valor <= 0) {
            throw new ExcecaoValidacao("Valor menor ou igual a zero");
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        caixa.creditar(valor);
        repositorioCaixaDeBonus.alterar(caixa);
        LancamentoBonusCredito lancamento = new LancamentoBonusCredito(numeroCaixaDeBonus, valor, LocalDateTime.now());
        try {
            repositorioLancamento.incluir(lancamento);
        } catch (ExcecaoObjetoJaExistente e) {
            throw new ExcecaoValidacao("Inconsistencia no cadastro de lancamento");
        }
    }

    public void resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipoResgate) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
        if (valor <= 0) {
            throw new ExcecaoValidacao("Valor menor ou igual a zero");
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa.getSaldo() < valor) {
            throw new ExcecaoValidacao("Saldo insuficiente");
        }
        caixa.debitar(valor);
        repositorioCaixaDeBonus.alterar(caixa);
        LancamentoBonusDebito lancamento = new LancamentoBonusDebito(numeroCaixaDeBonus, valor, LocalDateTime.now(), tipoResgate);
        try {
            repositorioLancamento.incluir(lancamento);
        } catch (ExcecaoObjetoJaExistente e) {
            throw new ExcecaoValidacao("Inconsistencia no cadastro de lancamento");
        }
    }

    public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) throws ExcecaoObjetoNaoExistente {
        CaixaDeBonus[] todasAsCaixas = repositorioCaixaDeBonus.buscarTodos();
        Arrays.sort(todasAsCaixas, ComparadorCaixaDeBonusSaldoDec.getInstance());
        return Arrays.stream(todasAsCaixas)
                .filter(caixa -> caixa.getSaldo() >= saldoInicial)
                .toArray(CaixaDeBonus[]::new);
    }

    public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) throws ExcecaoObjetoNaoExistente {
        LancamentoBonus[] todosOsLancamentos = repositorioLancamento.buscarTodos();
        List<LancamentoBonus> lancamentosFiltrados = Arrays.stream(todosOsLancamentos)
                .filter(lancamento -> {
                    LocalDate dataLancamento = lancamento.getDataHoraLancamento().toLocalDate();
                    return (dataLancamento.isEqual(d1) || dataLancamento.isAfter(d1)) && (dataLancamento.isEqual(d2) || dataLancamento.isBefore(d2));
                })
                .toList();
        Collections.sort(lancamentosFiltrados, new ComparadorLancamentoBonusDataHoraDec());
        return lancamentosFiltrados.toArray(new LancamentoBonus[0]);
    }

    static class ComparadorCaixaDeBonusSaldoDec implements Comparator<CaixaDeBonus> {
        private static ComparadorCaixaDeBonusSaldoDec instance;

        private ComparadorCaixaDeBonusSaldoDec() {
        }

        public static ComparadorCaixaDeBonusSaldoDec getInstance() {
            if (instance == null) {
                instance = new ComparadorCaixaDeBonusSaldoDec();
            }
            return instance;
        }

        @Override
        public int compare(CaixaDeBonus caixa1, CaixaDeBonus caixa2) {
            return Double.compare(caixa2.getSaldo(), caixa1.getSaldo());
        }
    }

    static class ComparadorLancamentoBonusDataHoraDec implements Comparator<LancamentoBonus> {
        @Override
        public int compare(LancamentoBonus lancamento1, LancamentoBonus lancamento2) {
            return lancamento2.getDataHoraLancamento().compareTo(lancamento1.getDataHoraLancamento());
        }
    }
}
