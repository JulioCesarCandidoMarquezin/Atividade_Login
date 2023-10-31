package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField txtUsuario = new JTextField(18);
    private JPasswordField txtSenha = new JPasswordField(18);
    private JButton btnEntrar = new JButton("Entrar");
    private JButton btnCadastrar = new JButton("Cadastrar");

    public TelaLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel image = new JLabel(new ImageIcon(getClass().getResource("/assets/login.png")));
        JLabel userLabel = new JLabel("Usuário");
        JLabel passwordLabel = new JLabel("Senha");

        btnEntrar.addActionListener(this::login);
        btnCadastrar.addActionListener((event) -> new TelaCadUsuario());
        btnEntrar.setBackground(Color.white);
        btnCadastrar.setBackground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(image, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);

        gbc.gridy = 2;
        panel.add(txtUsuario, gbc);

        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(txtSenha, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(btnEntrar, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnCadastrar, gbc);

        panel.setBackground(Color.decode("#ccffff"));

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 400);
        setContentPane(panel);
        setTitle("Login");

    }

    private void login(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        boolean achou = false;

        try {
            Connection conexao = DB.getConnection();
            String sql = "SELECT * FROM cadUsuario WHERE nome = ? and senha = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nomeBD = rs.getString("nome");
                String senhaBD = rs.getString("senha");
                if (nomeBD.equals(usuario) && senhaBD.equals(senha)) {
                    achou = true;
                }
            }

            if (achou) {
                new TelaMenu();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado...");
        }
    }
}
