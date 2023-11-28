package br.gov.cesarschool.poo.bonusvendas.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.gov.cesarschool.poo.bonusvendas.dao.DAOGenerico;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class ConsultaDeBonus extends JFrame {

    private JTextField textFieldId;
    private JTextArea textAreaResultado;
    private JButton btnConsultar;

    public ConsultaDeBonus() {
        setTitle("Consulta de Bônus");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel();
        textFieldId = new JTextField(20);
        btnConsultar = new JButton("Consultar");
        panelEntrada.add(new JLabel("ID do Bônus:"));
        panelEntrada.add(textFieldId);
        panelEntrada.add(btnConsultar);

        textAreaResultado = new JTextArea();
        textAreaResultado.setEditable(false);

        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(textAreaResultado), BorderLayout.CENTER);

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarBonus();
            }
        });
    }

    private void consultarBonus() {
        String id = textFieldId.getText();
        String resultado = buscarBonus(id);
        textAreaResultado.setText(resultado);
    }

    private String buscarBonus(String id) {
        DAOGenerico dao = new DAOGenerico(LancamentoBonus.class);
        Registro registro = dao.buscar(id);

        if (registro != null && registro instanceof LancamentoBonus) {
            LancamentoBonus bonus = (LancamentoBonus) registro;
            return "Detalhes do Bônus: " + bonus.toString();
        } else {
            return "Bônus não encontrado para o ID: " + id;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConsultaDeBonus().setVisible(true);
            }
        });
    }
}
