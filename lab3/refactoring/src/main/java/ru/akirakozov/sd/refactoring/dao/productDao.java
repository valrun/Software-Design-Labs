package ru.akirakozov.sd.refactoring.dao;

import java.sql.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class productDao {
    final public static String URL = "jdbc:sqlite:test.db";
    final public static String COLUMN_NAME = "name";
    final public static String COLUMN_PRICE = "price";

    final private static String QUERY_GET_ALL = "SELECT * FROM PRODUCT";
    final private static String QUERY_COUNT_ALL = "SELECT COUNT(*) FROM PRODUCT";
    final private static String QUERY_GET_MAX_PRICE = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    final private static String QUERY_GET_MIN_PRICE = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
    final private static String QUERY_GET_SUM_PRICE = "SELECT SUM(price) FROM PRODUCT";

    private static String queryInsertNamePrice(final String name, final long price) {
        return "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")";
    }

    public static void insertNamePrice(final String name, final long price) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL)) {
            String sql = queryInsertNamePrice(name, price);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    private static List<SimpleEntry<String, Long>> executeOrderQuery(final String sql) throws SQLException {
        List<SimpleEntry<String, Long>> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(URL)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString(COLUMN_NAME);
                int price = rs.getInt(COLUMN_PRICE);
                result.add(new SimpleEntry<>(name, (long) price));
            }

            rs.close();
            stmt.close();
        }
        return result;
    }

    public static List<SimpleEntry<String, Long>> getAll() throws SQLException {
        return executeOrderQuery(QUERY_GET_ALL);
    }

    public static List<SimpleEntry<String, Long>> getMax() throws SQLException {
        return executeOrderQuery(QUERY_GET_MAX_PRICE);
    }

    public static List<SimpleEntry<String, Long>> getMin() throws SQLException {
        return executeOrderQuery(QUERY_GET_MIN_PRICE);
    }

    private static int executeFunctionQuery(final String sql, final int initValue) throws SQLException {
        int result = initValue;

        try (Connection c = DriverManager.getConnection(URL)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        }

        return result;
    }

    public static int getSum() throws SQLException {
        return executeFunctionQuery(QUERY_GET_SUM_PRICE, -1);
    }

    public static int getCount() throws SQLException {
        return executeFunctionQuery(QUERY_COUNT_ALL, -1);
    }
}
