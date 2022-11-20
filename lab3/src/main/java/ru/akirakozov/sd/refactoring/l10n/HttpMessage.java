package ru.akirakozov.sd.refactoring.l10n;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

public class HttpMessage {
    private final static String OPEN_HTML_BODY = "<html><body>";
    private final static String CLOSE_HTML_BODY = "</body></html>";
    private final static String OPEN_HEAD = "<h1>";
    private final static String CLOSE_HEAD = "</h1>";

    public final static String HEAD_PRODUCT_WITH_MAX_PRICE = OPEN_HEAD + "Product with max price: " + CLOSE_HEAD;
    public final static String HEAD_PRODUCT_WITH_MIN_PRICE = OPEN_HEAD + "Product with min price: " + CLOSE_HEAD;
    public final static String TEXT_SUM_PRICE = "Summary price: ";
    public final static String TEXT_NUMBER_PRODUCTS = "Number of products: ";
    public final static String TEXT_UNKNOWN_COMMAND = "Unknown command: ";

    private final static String TAB = "\t";
    private final static String END_LINE = "</br>";
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    private static String getLineOfNamePrice(final String name, final long price) {
        return name + TAB + price + END_LINE;
    }

    private static String getLineOfNamePrice(AbstractMap.SimpleEntry<String, Long> pair) {
        return getLineOfNamePrice(pair.getKey(), pair.getValue());
    }

    private static String getLinesOfNamePrice(List<AbstractMap.SimpleEntry<String, Long>> list) {
        return list.stream()
                .map(HttpMessage::getLineOfNamePrice)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public static String createHtml(List<AbstractMap.SimpleEntry<String, Long>> list) {
        return OPEN_HTML_BODY + LINE_SEPARATOR +
                getLinesOfNamePrice(list) + LINE_SEPARATOR +
                CLOSE_HTML_BODY;
    }

    public static String createHtml(String head, List<AbstractMap.SimpleEntry<String, Long>> list) {
        return createHtml(head, getLinesOfNamePrice(list));
    }

    public static String createHtml(String head, String body) {
        return OPEN_HTML_BODY + LINE_SEPARATOR +
                head + LINE_SEPARATOR +
                body + LINE_SEPARATOR +
                CLOSE_HTML_BODY;
    }
}
