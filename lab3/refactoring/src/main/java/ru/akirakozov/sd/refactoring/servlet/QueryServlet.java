package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.dao.productDao.*;
import static ru.akirakozov.sd.refactoring.l10n.HttpMessage.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends CommonAbstractServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter(PARAMETER_COMMAND);

        if (Command.MAX.toString().equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection(URL)) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY_GET_MAX_PRICE);
                    response.getWriter().println(OPEN_HTML_BODY);
                    response.getWriter().println(HEAD_PRODUCT_WITH_MAX_PRICE);

                    while (rs.next()) {
                        String  name = rs.getString(COLUMN_NAME);
                        int price  = rs.getInt(COLUMN_PRICE);
                        response.getWriter().println(getLineOfNamePrice(name, price));
                    }
                    response.getWriter().println(CLOSE_HTML_BODY);

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.MIN.toString().equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection(URL)) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY_GET_MIN_PRICE);
                    response.getWriter().println(OPEN_HTML_BODY);
                    response.getWriter().println(HEAD_PRODUCT_WITH_MIN_PRICE);

                    while (rs.next()) {
                        String  name = rs.getString(COLUMN_NAME);
                        int price  = rs.getInt(COLUMN_PRICE);
                        response.getWriter().println(getLineOfNamePrice(name, price));
                    }
                    response.getWriter().println(CLOSE_HTML_BODY);

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.SUM.toString().equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection(URL)) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY_GET_SUM_PRICE);
                    response.getWriter().println(OPEN_HTML_BODY);
                    response.getWriter().println(TEXT_SUM_PRICE);

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println(CLOSE_HTML_BODY);

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.COUNT.toString().equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection(URL)) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY_COUNT_ALL);
                    response.getWriter().println(OPEN_HTML_BODY);
                    response.getWriter().println(TEXT_NUMBER_PRODUCTS);

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println(CLOSE_HTML_BODY);

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println(TEXT_UNKNOWN_COMMAND + command);
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
