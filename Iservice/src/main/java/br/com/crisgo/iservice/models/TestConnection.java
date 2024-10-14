package br.com.crisgo.iservice.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Iservice";
        String username = "postgres";
        String password = "14273119";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to postgre database!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
