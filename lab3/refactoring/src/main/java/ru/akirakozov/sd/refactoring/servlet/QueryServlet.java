package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static ru.akirakozov.sd.refactoring.l10n.HttpMessage.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends CommonAbstractServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter(PARAMETER_COMMAND);

        if (Arrays.stream(Command.values()).noneMatch(cmd -> cmd.toString().equals(command))) {
            response.getWriter().println(TEXT_UNKNOWN_COMMAND + command);
        } else {
            try {
                String html = switch (Command.valueOf(command.toUpperCase())) {
                    case MAX -> createHtml(HEAD_PRODUCT_WITH_MAX_PRICE, ProductDao.getMax());
                    case MIN -> createHtml(HEAD_PRODUCT_WITH_MIN_PRICE, ProductDao.getMin());
                    case SUM -> createHtml(TEXT_SUM_PRICE, String.valueOf(ProductDao.getSum()));
                    case COUNT -> createHtml(TEXT_NUMBER_PRODUCTS, String.valueOf(ProductDao.getCount()));
                };
                response.getWriter().println(html);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
