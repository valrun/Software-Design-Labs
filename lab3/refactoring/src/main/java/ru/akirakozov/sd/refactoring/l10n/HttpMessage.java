package ru.akirakozov.sd.refactoring.l10n;

public class HttpMessage {
    public final static String OPEN_HTML_BODY = "<html><body>";
    public final static String CLOSE_HTML_BODY = "</body></html>";
    private final static String OPEN_HEAD = "<h1>";
    private final static String CLOSE_HEAD = "</h1>";

    public final static String HEAD_PRODUCT_WITH_MAX_PRICE = OPEN_HEAD + "Product with max price: " + CLOSE_HEAD;
    public final static String HEAD_PRODUCT_WITH_MIN_PRICE = OPEN_HEAD + "Product with min price: " + CLOSE_HEAD;
    public final static String TEXT_SUM_PRICE = "Summary price: ";
    public final static String TEXT_NUMBER_PRODUCTS  = "Number of products: ";
    public final static String TEXT_UNKNOWN_COMMAND  = "Unknown command: ";

    private final static String TAB = "\t";
    private final static String END_LINE = "</br>";

    public static String getLineOfNamePrice(final String name, final long price) {
        return name + TAB + price + END_LINE;
    }
}
