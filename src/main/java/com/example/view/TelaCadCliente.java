package com.example.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.example.DB;
import com.example.model.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TelaCadCliente extends JFrame {
    private final JTextField campoNome = new JTextField();
    private final JFormattedTextField campoRg = masker("##.###.###-#");
    private final JFormattedTextField campoCpf = masker("###.###.###-##");
    private final JFormattedTextField campoTelefone = masker("(##) #####-####");
    private final JTextField campoEmail = new JTextField();
    private final JButton botaoCadastrar = new JButton("Cadastrar");
    private final JButton botaoConsultar = new JButton("Consultar Clientes");

    public TelaCadCliente() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#ccffff"));

        JLabel titulo = new JLabel("Cadastro de Produtos");
        titulo.setBounds(250, 0, 200, 30);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo);

        JLabel icon = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/assets/IconCadCliente.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        icon.setBounds(510, 40, 150, 150);
        icon.setHorizontalAlignment(JLabel.CENTER);
        panel.add(icon);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 40, 80, 30);
        panel.add(labelNome);
        campoNome.setBounds(100, 40, 400, 30);
        panel.add(campoNome);

        JLabel labelRg = new JLabel("RG:");
        labelRg.setBounds(20, 80, 80, 30);
        panel.add(labelRg);
        campoRg.setBounds(100, 80, 175, 30);
        panel.add(campoRg);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(285, 80, 80, 30);
        panel.add(labelCpf);
        campoCpf.setBounds(325, 80, 175, 30);
        panel.add(campoCpf);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(20, 120, 80, 30);
        panel.add(labelTelefone);
        campoTelefone.setBounds(100, 120, 125, 30);
        panel.add(campoTelefone);

        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(240, 120, 80, 30);
        panel.add(labelEmail);
        campoEmail.setBounds(300, 120, 200, 30);
        panel.add(campoEmail);

        botaoCadastrar.setBounds(100, 160, 190, 30);
        botaoCadastrar.addActionListener(this::cadastrarCliente);
        panel.add(botaoCadastrar);

        botaoConsultar.setBounds(310, 160, 190, 30);
        botaoConsultar.addActionListener(this::consultarCliente);
        panel.add(botaoConsultar);

        setBackground(Color.decode("#ccffff"));
        setContentPane(panel);
        setSize(700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Cadastro de Clientes");
        setResizable(false);
    }

    private JFormattedTextField masker(String maskFormat) {
        JFormattedTextField formattedTextField = new JFormattedTextField();
        try {
            formattedTextField = new JFormattedTextField(new MaskFormatter(maskFormat));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTextField;
    }

    private void cadastrarCliente(ActionEvent e)
    {
        Connection conn = DB.getConnection();
        Cliente cliente = new Cliente(campoNome.getText(), campoRg.getText(), campoCpf.getText(),
                campoTelefone.getText(), campoEmail.getText());

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cadCliente (nome, rg, cpf, telefone, email) VALUES (?,?,?,?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getRg());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getEmail());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Cliente cadastrado com sucesso");

            campoNome.setText("");
            campoRg.setText("");
            campoCpf.setText("");
            campoTelefone.setText("");
            campoEmail.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Erro ao cadastrar cliente");
        }
    }

    private void consultarCliente(ActionEvent e)
    {
        Connection conn = DB.getConnection();
        Cliente cliente = new Cliente();
        cliente.setNome(campoNome.getText());

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cadCliente WHERE nome = ?");
            ps.setString(1, cliente.getNome());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                campoNome.setText(rs.getString(2));
                campoRg.setText(rs.getString(3));
                campoCpf.setText(rs.getString(4));
                campoTelefone.setText(rs.getString(5));
                campoEmail.setText(rs.getString(6));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Erro ao consultar cliente");
        }
    }
}