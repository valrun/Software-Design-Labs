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
                response.getWriter().println(
                        createHtml(HEAD_PRODUCT_WITH_MAX_PRICE, productDao.getMax()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.MIN.toString().equals(command)) {
            try {
                response.getWriter().println(
                        createHtml(HEAD_PRODUCT_WITH_MIN_PRICE, productDao.getMin()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.SUM.toString().equals(command)) {
            try {
                response.getWriter().println(
                        createHtml(TEXT_SUM_PRICE, String.valueOf(productDao.getSum())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (Command.COUNT.toString().equals(command)) {
            try {
                response.getWriter().println(
                        createHtml(TEXT_NUMBER_PRODUCTS, String.valueOf(productDao.getCount())));
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
