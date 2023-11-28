package br.gov.cesarschool.poo.bonusvendas.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;

public class TelaManutencaoVendedor extends JFrame {
    private VendedorMediator mediator = VendedorMediator.getInstancia();

    private JTextField txtCPF, txtNomeCompleto, txtDataNascimento, txtRenda, txtLogradouro, txtNumero, txtComplemento, txtCEP, txtCidade;
    private JRadioButton rbMasculino, rbFeminino;
    private JComboBox<String> cmbEstado;
    private JButton btnSalvar, btnCancelar;

    public TelaManutencaoVendedor() {
        setTitle("Manutenção de Vendedor");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Component initialization
        initializeComponents();

        // Event handlers
        setupEventHandlers();
    }

    private void initializeComponents() {
        txtCPF = new JTextField(15);
        txtNomeCompleto = new JTextField(20);
        rbMasculino = new JRadioButton("Masculino");
        rbFeminino = new JRadioButton("Feminino");
        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(rbMasculino);
        sexoGroup.add(rbFeminino);
        txtDataNascimento = new JTextField(10);
        txtRenda = new JTextField(10);
        txtLogradouro = new JTextField(15);
        txtNumero = new JTextField(5);
        txtComplemento = new JTextField(10);
        txtCEP = new JTextField(8);
        txtCidade = new JTextField(15);
        cmbEstado = new JComboBox<>(new String[]{"SP", "RJ", "MG", "RS", "PR", /* Outros estados */});
        
        // Add components to frame
        add(new JLabel("CPF:"));
        add(txtCPF);
        add(new JLabel("Nome Completo:"));
        add(txtNomeCompleto);
        add(new JLabel("Sexo:"));
        add(rbMasculino);
        add(rbFeminino);
        add(new JLabel("Data de Nascimento:"));
        add(txtDataNascimento);
        add(new JLabel("Renda:"));
        add(txtRenda);
        add(new JLabel("Logradouro:"));
        add(txtLogradouro);
        add(new JLabel("Número:"));
        add(txtNumero);
        add(new JLabel("Complemento:"));
        add(txtComplemento);
        add(new JLabel("CEP:"));
        add(txtCEP);
        add(new JLabel("Cidade:"));
        add(txtCidade);
        add(new JLabel("Estado:"));
        add(cmbEstado);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        add(btnSalvar);
        add(btnCancelar);
    }

    private void setupEventHandlers() {
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarVendedor();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaManutencaoVendedor.this.dispose();
            }
        });
    }

    private void salvarVendedor() {
        String cpf = txtCPF.getText();
        String nomeCompleto = txtNomeCompleto.getText();
        Sexo sexo = rbMasculino.isSelected() ? Sexo.MASCULINO : Sexo.FEMININO;
        LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText());
        double renda = Double.parseDouble(txtRenda.getText());
        String logradouro = txtLogradouro.getText();
        int numero = Integer.parseInt(txtNumero.getText());
        String complemento = txtComplemento.getText();
        String cep = txtCEP.getText();
        String cidade = txtCidade.getText();
        String estado = (String) cmbEstado.getSelectedItem();
        String pais = "Brasil";

        Endereco endereco = new Endereco(logradouro, numero, complemento, cep, cidade, estado, pais);
        Vendedor vendedor = new Vendedor(cpf, nomeCompleto, sexo, dataNascimento, renda, endereco);

        VendedorMediator mediator = VendedorMediator.getInstancia();
        ResultadoInclusaoVendedor resultado = mediator.incluir(vendedor);
        String mensagem = resultado.getMensagemErroValidacao();

        if (mensagem == null || mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro: " + mensagem);
        }
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TelaManutencaoVendedor frame = new TelaManutencaoVendedor();
            frame.setVisible(true);
        });
    }
}
