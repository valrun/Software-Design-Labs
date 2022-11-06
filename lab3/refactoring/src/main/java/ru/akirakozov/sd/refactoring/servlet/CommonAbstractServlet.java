package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;

public abstract class CommonAbstractServlet extends HttpServlet {

    protected final static String CONTENT_TYPE = "text/html";
    protected final static String OK = "OK";

    protected final static String PARAMETER_COMMAND = "command";

    enum Command {
        MIN, MAX, SUM, COUNT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

}
