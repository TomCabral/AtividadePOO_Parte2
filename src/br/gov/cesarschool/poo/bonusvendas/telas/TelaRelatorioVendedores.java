package br.gov.cesarschool.poo.bonusvendas.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class TelaRelatorioVendedores extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public TelaRelatorioVendedores() {
        setTitle("Relatório de Vendedores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Renda");

        table = new JTable(tableModel);

        carregarDadosVendedores();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarDadosVendedores() {
        VendedorMediator mediator = VendedorMediator.getInstancia();
        Registro[] registros = mediator.gerarListagemClienteOrdenadaPorNome();

        for (Registro registro : registros) {
            if (registro instanceof Vendedor) {
                Vendedor vendedor = (Vendedor) registro;
                tableModel.addRow(new Object[]{vendedor.getCpf(), vendedor.getNomeCompleto(), "R$ " + vendedor.getRenda()});
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaRelatorioVendedores frame = new TelaRelatorioVendedores();
            frame.setVisible(true);
        });
    }
}
