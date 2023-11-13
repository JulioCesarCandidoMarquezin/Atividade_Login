package com.example.view;

import javax.swing.*;

public class TelaMenu extends JFrame{

    public TelaMenu()
    {
        JPanel panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemCadastroProdutos = new JMenuItem("Cadastro de Produtos");
        itemCadastroProdutos.addActionListener((e) -> new TelaCadProduto());
        menuCadastro.add(itemCadastroProdutos);
        JMenuItem itemCadastroClientes = new JMenuItem("Cadastro de Produtos");
        itemCadastroProdutos.addActionListener((e) -> new TelaCadCliente());
        menuCadastro.add(itemCadastroClientes);
        menuBar.add(menuCadastro);

        JMenu menuConsulta = new JMenu("Consulta");
        JMenuItem itemConsultaProdutos = new JMenuItem("Consulta de Produtos");
        menuConsulta.add(itemConsultaProdutos);
        JMenuItem itemConsultaClientes = new JMenuItem("Consulta de Produtos");
        menuConsulta.add(itemConsultaClientes);
        menuBar.add(menuConsulta);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setResizable(false);
        setVisible(true);
        setContentPane(panel);
        setTitle("Menu");
        setJMenuBar(menuBar);
    }
}
