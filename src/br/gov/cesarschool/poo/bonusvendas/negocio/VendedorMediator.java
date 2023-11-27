package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

public class VendedorMediator {
    private static VendedorMediator instancia;
    private VendedorDAO repositorioVendedor;
    private AcumuloResgateMediator caixaDeBonusMediator;

    public static VendedorMediator getInstancia() {
        if (instancia == null) {
            instancia = new VendedorMediator();
        }
        return instancia;
    }

    private VendedorMediator() {
        repositorioVendedor = new VendedorDAO();
        caixaDeBonusMediator = AcumuloResgateMediator.getInstancia();
    }

    public ResultadoInclusaoVendedor incluir(Vendedor vendedor) {
        long numeroCaixaBonus = 0;
        String msg = validar(vendedor);
        if (msg == null) {
            boolean ret = repositorioVendedor.incluir(vendedor);
            if (!ret) {
                msg = "Vendedor já existente";
            } else {
                numeroCaixaBonus = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
                if (numeroCaixaBonus == 0) {
                    msg = "Caixa de bônus não foi gerada";
                }
            }
        }
        return new ResultadoInclusaoVendedor(numeroCaixaBonus, msg);
    }

    public String alterar(Vendedor vendedor) {
        String msg = validar(vendedor);
        if (msg == null) {
            boolean ret = repositorioVendedor.alterar(vendedor);
            if (!ret) {
                msg = "Vendedor inexistente";
            }
        }
        return msg;
    }

    public Vendedor[] gerarListagemClienteOrdenadaPorNome() {
        Vendedor[] vendedores = repositorioVendedor.buscarTodos();
        Arrays.sort(vendedores, Comparator.comparing(Vendedor::getNomeCompleto));
        return vendedores;
    }

    public Vendedor[] gerarListagemClienteOrdenadaPorRenda() {
        Vendedor[] vendedores = repositorioVendedor.buscarTodos();
        Arrays.sort(vendedores, Comparator.comparingDouble(Vendedor::getRenda));
        return vendedores;
    }

    private String validar(Vendedor vendedor) {
        if (StringUtil.ehNuloOuBranco(vendedor.getCpf())) {
            return "CPF não informado";
        }
        if (!ValidadorCPF.ehCpfValido(vendedor.getCpf())) {
            return "CPF inválido";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
            return "Nome completo não informado";
        }
        if (vendedor.getSexo() == null) {
            return "Sexo não informado";
        }
        if (vendedor.getDataNascimento() == null) {
            return "Data de nascimento não informada";
        }
        if (dataNascimentoInvalida(vendedor.getDataNascimento())) {
            return "Data de nascimento inválida";
        }
        if (vendedor.getRenda() < 0) {
            return "Renda menor que zero";
        }
        if (vendedor.getEndereco() == null) {
            return "Endereço não informado";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getLogradouro())) {
            return "Logradouro não informado";
        }
        if (vendedor.getEndereco().getLogradouro().length() < 4) {
            return "Logradouro tem menos de 04 caracteres";
        }
        if (vendedor.getEndereco().getNumero() < 0) {
            return "Número menor que zero";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getCidade())) {
            return "Cidade não informada";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getEstado())) {
            return "Estado não informado";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getPais())) {
            return "País não informado";
        }
        return null;
    }

    private boolean dataNascimentoInvalida(LocalDate dataNasc) {
        long yearsDifference = ChronoUnit.YEARS.between(dataNasc, LocalDate.now());
        return yearsDifference < 17;
    }
}
