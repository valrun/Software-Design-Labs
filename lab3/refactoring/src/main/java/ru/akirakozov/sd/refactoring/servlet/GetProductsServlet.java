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
public class GetProductsServlet extends CommonAbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            try (Connection c = DriverManager.getConnection(URL)) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY_GET_ALL);
                response.getWriter().println(OPEN_HTML_BODY);

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

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
