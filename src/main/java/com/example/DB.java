package com.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    public static Connection getConnection()
    {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/exemploLogin", "root", "root");
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Algo deu errado com a conexão");
            }
        }
        return connection;
    }
}
