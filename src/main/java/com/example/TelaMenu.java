package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaMenu extends JFrame{

    public TelaMenu()
    {
        JPanel panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemCadastroProdutos = new JMenuItem("Cadastro de Produtos");
        itemCadastroProdutos.addActionListener(this::openCadProduto);
        menuCadastro.add(itemCadastroProdutos);
        menuBar.add(menuCadastro);

        JMenu menuConsulta = new JMenu("Consulta");
        JMenuItem itemConsultaProdutos = new JMenuItem("Consulta de Produtos");
        menuConsulta.add(itemConsultaProdutos);
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

    private void openCadProduto(ActionEvent event)
    {
        new TelaCadProduto();
    }
}
