package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author valrun
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DriverManager.class)
public class GetProductsServletTest {

    @Test
    public void test() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection("jdbc:sqlite:test.db")).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery("SELECT * FROM PRODUCT")).thenReturn(resultSet);
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getString("name"))
                    .thenReturn("1")
                    .thenReturn("2")
                    .thenReturn("3");
            when(resultSet.getInt("price"))
                    .thenReturn(4)
                    .thenReturn(5)
                    .thenReturn(6);
            when(response.getWriter()).thenReturn(writer);

            final GetProductsServlet servlet = new GetProductsServlet();
            servlet.doGet(request, response);

            assertEquals(
                    "<html><body>\n" +
                            "1\t4</br>\n" +
                            "2\t5</br>\n" +
                            "3\t6</br>\n" +
                            "</body></html>\n",
                    stringWriter.toString());

            verify(resultSet).close();
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType("text/html");
        }
    }
}
