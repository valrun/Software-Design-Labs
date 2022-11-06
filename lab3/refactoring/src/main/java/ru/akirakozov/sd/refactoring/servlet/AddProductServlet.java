package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static ru.akirakozov.sd.refactoring.dao.productDao.*;

/**
 * @author akirakozov
 */
public class AddProductServlet extends CommonAbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(COLUMN_NAME);
        long price = Long.parseLong(request.getParameter(COLUMN_PRICE));

        try {
            try (Connection c = DriverManager.getConnection(URL)) {
                String sql = queryInsertNamePrice(name, price);
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(OK);
    }
}
