package ru.akirakozov.sd.refactoring.dao;

public class productDao {
    final public static String URL = "jdbc:sqlite:test.db";
    final public static String COLUMN_NAME = "name";
    final public static String COLUMN_PRICE = "price";

    final public static String QUERY_GET_ALL = "SELECT * FROM PRODUCT";
    final public static String QUERY_COUNT_ALL = "SELECT COUNT(*) FROM PRODUCT";
    final public static String QUERY_GET_MAX_PRICE = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    final public static String QUERY_GET_MIN_PRICE = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
    final public static String QUERY_GET_SUM_PRICE = "SELECT SUM(price) FROM PRODUCT";

    public static String queryInsertNamePrice(final String name, final long price) {
        return "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")";
    }
}
