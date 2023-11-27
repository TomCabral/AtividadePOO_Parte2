package br.gov.cesarschool.poo.bonusvendas.telas;

import java.awt.EventQueue;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;

public class TelaAcumuloResgate extends JFrame {

    private JPanel contentPane;
    private JTextField txtNumeroDaCaixa;
    private JRadioButton rbOperacaoAcumular;
    private JRadioButton rbOperacaoResgatar;
    private JButton btnBuscar;
    private JTextField txtSaldoAtual;
    private JComboBox<TipoResgate> cmbTipoResgate;
    private JTextField txtValor;
    private JButton btnAcumularResgatar;
    private JButton btnVoltar;
    private AcumuloResgateMediator mediator = AcumuloResgateMediator.getInstancia();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaAcumuloResgate frame = new TelaAcumuloResgate();
            frame.setVisible(true);
        });
    }

    public TelaAcumuloResgate() {
        setTitle("Acumulo e Resgate de Bônus");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setupUIComponents();
        setupActionListeners();
    }

    private void setupUIComponents() {
        txtNumeroDaCaixa = new JTextField();
        txtNumeroDaCaixa.setBounds(120, 20, 150, 20);
        contentPane.add(txtNumeroDaCaixa);

        rbOperacaoAcumular = new JRadioButton("Acumular");
        rbOperacaoAcumular.setBounds(20, 50, 100, 20);
        contentPane.add(rbOperacaoAcumular);

        rbOperacaoResgatar = new JRadioButton("Resgatar");
        rbOperacaoResgatar.setBounds(120, 50, 100, 20);
        contentPane.add(rbOperacaoResgatar);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbOperacaoAcumular);
        buttonGroup.add(rbOperacaoResgatar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(280, 20, 100, 30);
        contentPane.add(btnBuscar);

        JLabel lblSaldoAtual = new JLabel("Saldo Atual:");
        lblSaldoAtual.setBounds(20, 100, 100, 20);
        contentPane.add(lblSaldoAtual);

        txtSaldoAtual = new JTextField();
        txtSaldoAtual.setBounds(120, 100, 150, 20);
        txtSaldoAtual.setEditable(false);
        contentPane.add(txtSaldoAtual);

        JLabel lblTipoResgate = new JLabel("Tipo de Resgate:");
        lblTipoResgate.setBounds(20, 140, 100, 20);
        contentPane.add(lblTipoResgate);

        cmbTipoResgate = new JComboBox<>();
        for (TipoResgate tipo : TipoResgate.values()) {
            cmbTipoResgate.addItem(tipo);
        }
        cmbTipoResgate.setBounds(120, 140, 150, 20);
        contentPane.add(cmbTipoResgate);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(20, 180, 100, 20);
        contentPane.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(120, 180, 150, 20);
        contentPane.add(txtValor);

        btnAcumularResgatar = new JButton("Acumular/Resgatar");
        btnAcumularResgatar.setBounds(120, 220, 150, 30);
        contentPane.add(btnAcumularResgatar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(280, 220, 100, 30);
        contentPane.add(btnVoltar);
    }

    private void setupActionListeners() {
        btnAcumularResgatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarOperacao();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarSaldo();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaAcumuloResgate.this.dispose();
            }
        });
    }

    private void realizarOperacao() {
        try {
            long numeroCaixa = Long.parseLong(txtNumeroDaCaixa.getText());
            double valor = Double.parseDouble(txtValor.getText());
            TipoResgate tipoResgate = (TipoResgate) cmbTipoResgate.getSelectedItem();

            String resultado;
            if (rbOperacaoAcumular.isSelected()) {
                resultado = mediator.acumularBonus(numeroCaixa, valor);
            } else {
                resultado = mediator.resgatar(numeroCaixa, valor, tipoResgate);
            }

            if (resultado != null) {
                JOptionPane.showMessageDialog(this, resultado);
            } else {
                buscarSaldo();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.");
        }
    }

    private void buscarSaldo() {
        try {
            long numeroCaixa = Long.parseLong(txtNumeroDaCaixa.getText());
            CaixaDeBonusDAO caixaDeBonusDAO = new CaixaDeBonusDAO();
            CaixaDeBonus caixaDeBonus = caixaDeBonusDAO.buscar(numeroCaixa);

            if (caixaDeBonus != null) {
                txtSaldoAtual.setText(String.format("%.2f", caixaDeBonus.getSaldo()));
            } else {
                JOptionPane.showMessageDialog(this, "Caixa de bônus não encontrada.");
                txtSaldoAtual.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para a caixa de bônus.");
        }
    }


}
