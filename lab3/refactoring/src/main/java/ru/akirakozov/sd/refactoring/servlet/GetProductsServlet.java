package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.productDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static ru.akirakozov.sd.refactoring.l10n.HttpMessage.*;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends CommonAbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String body = productDao.getAll()
                    .stream()
                    .map((entry) -> getLineOfNamePrice(entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(System.getProperty("line.separator")));

            response.getWriter().println(OPEN_HTML_BODY);
            response.getWriter().println(body);
            response.getWriter().println(CLOSE_HTML_BODY);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
