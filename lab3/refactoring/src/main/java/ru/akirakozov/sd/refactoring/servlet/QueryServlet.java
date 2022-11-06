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
public class QueryServlet extends CommonAbstractServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter(PARAMETER_COMMAND);

        if (Command.MAX.toString().equals(command)) {
            try {
                String body = productDao.getMax()
                        .stream()
                        .map((entry) -> getLineOfNamePrice(entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(System.getProperty("line.separator")));

                response.getWriter().println(OPEN_HTML_BODY);
                response.getWriter().println(HEAD_PRODUCT_WITH_MAX_PRICE);
                response.getWriter().println(body);
                response.getWriter().println(CLOSE_HTML_BODY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.MIN.toString().equals(command)) {
            try {
                String body = productDao.getMin()
                        .stream()
                        .map((entry) -> getLineOfNamePrice(entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining(System.getProperty("line.separator")));

                response.getWriter().println(OPEN_HTML_BODY);
                response.getWriter().println(HEAD_PRODUCT_WITH_MIN_PRICE);
                response.getWriter().println(body);
                response.getWriter().println(CLOSE_HTML_BODY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.SUM.toString().equals(command)) {
            try {
                String body = String.valueOf(productDao.getSum());

                response.getWriter().println(OPEN_HTML_BODY);
                response.getWriter().println(TEXT_SUM_PRICE);
                response.getWriter().println(body);
                response.getWriter().println(CLOSE_HTML_BODY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.COUNT.toString().equals(command)) {
            try {
                String body = String.valueOf(productDao.getCount());

                response.getWriter().println(OPEN_HTML_BODY);
                response.getWriter().println(TEXT_NUMBER_PRODUCTS);
                response.getWriter().println(body);
                response.getWriter().println(CLOSE_HTML_BODY);
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
