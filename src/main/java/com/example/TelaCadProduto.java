package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaCadProduto extends JFrame{

    private JTextField txtProduto = new JTextField(25);
    private JTextField txtPreco = new JTextField(25);
    private JTextField txtEstoque = new JTextField(25);
    private JButton btnCadProdutos = new JButton("Cadastrar");
    private JButton btnConsultaProdutos = new JButton("Consultar");

    public TelaCadProduto() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new BorderLayout());

        panel.setLayout(null);

        JLabel labelTitle = new JLabel("Cadastro de Produtos");
        labelTitle.setBounds(175, 20, 200, 30);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelTitle);

        JLabel labelIcon = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/assets/IconCadProduto.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        labelIcon.setBounds(250, 70, 200, 200);
        panel.add(labelIcon);

        JLabel labelProduto = new JLabel("Produto");
        labelProduto.setBounds(10, 70, 80, 25);
        panel.add(labelProduto);

        txtProduto.setBounds(10, 105, 200, 30);
        panel.add(txtProduto);

        JLabel labelPreco = new JLabel("Preço");
        labelPreco.setBounds(10, 145, 80, 25);
        panel.add(labelPreco);

        txtPreco.setBounds(10, 170, 200, 30);
        panel.add(txtPreco);

        JLabel labelEstoque = new JLabel("Estoque");
        labelEstoque.setBounds(10, 210, 80, 25);
        panel.add(labelEstoque);

        txtEstoque.setBounds(10, 245, 200, 30);
        panel.add(txtEstoque);

        btnCadProdutos.setBounds(10, 300, 120, 40);
        panel.add(btnCadProdutos);

        btnConsultaProdutos.setBounds(150, 300, 120, 40);
        panel.add(btnConsultaProdutos);

        panel.setBackground(Color.decode("#ccffff"));
        btnCadProdutos.setBackground(Color.white);
        btnConsultaProdutos.setBackground(Color.white);
        btnCadProdutos.addActionListener(this::cadastro);
        btnConsultaProdutos.addActionListener(this::consulta);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);
        setTitle("Cadastro de Produtos");
    }

    private void cadastro(ActionEvent event) {
        try {
            String produto = txtProduto.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            double estoque = Double.parseDouble(txtEstoque.getText());

            Produto prod = new Produto(produto, preco, estoque);
            Connection conexao = DB.getConnection();

            String sql = "INSERT INTO cadProduto(produto, preco, estoque) values (?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, prod.getProduto());
            ps.setDouble(2, prod.getPreco());
            ps.setDouble(3, prod.getEstoque());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso");
            txtProduto.setText("");
            txtPreco.setText("");
            txtEstoque.setText("");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Algo deu errado");
        }
    }

    private void consulta(ActionEvent event)
    {
        Connection conexao = DB.getConnection();

        try {
            String sql = "SELECT * FROM cadProduto WHERE produto = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, txtProduto.getText());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                txtPreco.setText(rs.getString(3));
                txtEstoque.setText(rs.getString(4));
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado");
            txtProduto.setText("");
            txtPreco.setText("");
            txtEstoque.setText("");
        }
    }
}