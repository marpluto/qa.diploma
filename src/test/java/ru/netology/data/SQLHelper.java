package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static String url = System.getProperty("db.url");
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, "app", "pass");
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity;");
        runner.execute(connection, "DELETE FROM order_entity;");
        runner.execute(connection, "DELETE FROM payment_entity;");
    }

    public static String getPaymentStatus() {
        var codesSQL = "SELECT status FROM payment_entity;";
        return getData(codesSQL);
    }

    public static String getCreditRequestStatus() {
        var codesSQL = "SELECT status FROM credit_request_entity;";
        return getData(codesSQL);
    }

    // Уточнить закомменченное
    @SneakyThrows
    public static String getOrderCount() {
        var connection = getConn();
        var codesSQL = " SELECT COUNT(*) FROM order_entity;";
        // Class.forName("com.mysql.cj.jdbc.Driver");
        return runner.query(connection,codesSQL,new ScalarHandler<>());
    }


    // Уточнить закомменченное
    @SneakyThrows
    private static String getData(String query) {
        var connection = getConn();
        // Class.forName("com.mysql.cj.jdbc.Driver");
        return runner.query(connection, query, new ScalarHandler<>());
    }
}