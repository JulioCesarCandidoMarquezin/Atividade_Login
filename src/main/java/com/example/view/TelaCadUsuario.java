package com.example.view;

import javax.swing.*;

import com.example.DB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadUsuario extends JFrame {

    private JTextField txtUsuario = new JTextField(20);
    private JPasswordField txtSenha = new JPasswordField(20);
    private JButton btnCadastrar = new JButton("Cadastrar");

    public TelaCadUsuario() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 5, 5, 5);

        JLabel titulo = new JLabel("Cadastro de Usuário");
        JLabel nomeLabel = new JLabel("Nome");
        JLabel senhaLabel = new JLabel("Senha");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(nomeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(senhaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(this::cadastrar);
        btnCadastrar.setBackground(Color.white);

        panel.setBackground(Color.decode("#ccffff"));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 250);
        setVisible(true);
        setResizable(false);
        setContentPane(panel);
        setTitle("Cadastro de Usuário");
    }

    private void cadastrar(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        try {
            Connection conexao = DB.getConnection();
            String sql = "insert into cadUsuario(nome, senha) values (?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
            txtUsuario.setText("");
            txtSenha.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado...");
        }
    }
}
