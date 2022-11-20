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
import static ru.akirakozov.sd.refactoring.servlet.TestUtils.*;

/**
 * @author valrun
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DriverManager.class)
public class AddProductServletTest {

    @Test
    public void test() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection(URL)).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter(COLUMN_NAME)).thenReturn("1");
            when(request.getParameter(COLUMN_PRICE)).thenReturn("2");

            final AddProductServlet servlet = new AddProductServlet();
            servlet.doGet(request, response);

            assertEquals("OK\n", stringWriter.toString());

            verify(statement).executeUpdate(QUERY_INSERT);
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType(CONTENT_TYPE);
        }
    }
}
