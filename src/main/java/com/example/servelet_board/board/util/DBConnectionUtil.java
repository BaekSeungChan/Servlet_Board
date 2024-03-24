package com.example.servelet_board.board.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnectionUtil {
    DATABASE;

    private Connection connection;

    DBConnectionUtil() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/webdb",
                    "root", // 계정 이름
                    "Qortmdcks95" // 계정 비밀번호
            );

            System.out.println("연결 성공");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}