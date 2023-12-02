package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.daov2.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ErroValidacao;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;

public class VendedorMediator {
    private static VendedorMediator instancia;
    private VendedorDAO repositorioVendedor;
    private AcumuloResgateMediator caixaDeBonusMediator;

    private VendedorMediator() {
        repositorioVendedor = new VendedorDAO();
        caixaDeBonusMediator = AcumuloResgateMediator.getInstancia();
    }

    public static VendedorMediator getInstancia() {
        if (instancia == null) {
            instancia = new VendedorMediator();
        }
        return instancia;
    }

    public long incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente, ExcecaoValidacao {
        validar(vendedor);
        repositorioVendedor.incluir(vendedor);
        return caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
    }

    public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
        validar(vendedor);
        repositorioVendedor.alterar(vendedor);
    }

    public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
        return repositorioVendedor.buscar(cpf);
    }

    private void validar(Vendedor vendedor) throws ExcecaoValidacao {
        List<ErroValidacao> erros = new ArrayList<>();

        if (StringUtil.ehNuloOuBranco(vendedor.getCpf())) {
            erros.add(new ErroValidacao(1, "CPF não informado"));
        } else if (!ValidadorCPF.ehCpfValido(vendedor.getCpf())) {
            erros.add(new ErroValidacao(2, "CPF inválido"));
        }

        if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
            erros.add(new ErroValidacao(3, "Nome completo não informado"));
        }

        if (vendedor.getSexo() == null) {
            erros.add(new ErroValidacao(4, "Sexo não informado"));
        }

        if (vendedor.getDataNascimento() == null) {
            erros.add(new ErroValidacao(5, "Data de nascimento não informada"));
        } else if (dataNascimentoInvalida(vendedor.getDataNascimento())) {
            erros.add(new ErroValidacao(6, "Data de nascimento inválida"));
        }

        if (vendedor.getRenda() < 0) {
            erros.add(new ErroValidacao(7, "Renda menor que zero"));
        }

        if (vendedor.getEndereco() == null) {
            erros.add(new ErroValidacao(8, "Endereço não informado"));
        } else {
            validarEndereco(vendedor.getEndereco(), erros);
        }

        if (!erros.isEmpty()) {
//            throw new ExcecaoValidacao(erros);
        }
    }

    private void validarEndereco(Endereco endereco, List<ErroValidacao> erros) {
        if (StringUtil.ehNuloOuBranco(endereco.getLogradouro())) {
            erros.add(new ErroValidacao(9, "Logradouro não informado"));
        } else if (endereco.getLogradouro().length() < 4) {
            erros.add(new ErroValidacao(10, "Logradouro tem menos de 04 caracteres"));
        }

        if (endereco.getNumero() < 0) {
            erros.add(new ErroValidacao(11, "Número menor que zero"));
        }

        if (StringUtil.ehNuloOuBranco(endereco.getCidade())) {
            erros.add(new ErroValidacao(12, "Cidade não informada"));
        }

        if (StringUtil.ehNuloOuBranco(endereco.getEstado())) {
            erros.add(new ErroValidacao(13, "Estado não informado"));
        }

        if (StringUtil.ehNuloOuBranco(endereco.getPais())) {
            erros.add(new ErroValidacao(14, "País não informado"));
        }
    }

    private boolean dataNascimentoInvalida(LocalDate dataNasc) {
        long yearsDifference = ChronoUnit.YEARS.between(dataNasc, LocalDate.now());
        return yearsDifference < 17;
    }
}
