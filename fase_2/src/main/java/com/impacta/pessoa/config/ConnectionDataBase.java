package com.impacta.pessoa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    private static String senha;
    private static String usuario;

    public ConnectionDataBase(String senha, String password) {
        this.senha = senha;
        this.usuario = password;
    }

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/impacta?useTimezone=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, usuario, senha);
    }
}