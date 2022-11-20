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
public class QueryServletTest {

    @Test
    public void test_max() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection(URL)).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(QUERY_GET_MAX_PRICE)).thenReturn(resultSet);
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getString(COLUMN_NAME))
                    .thenReturn("1");
            when(resultSet.getInt(COLUMN_PRICE))
                    .thenReturn(4);
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter(PARAMETER_COMMAND)).thenReturn("max");

            final QueryServlet servlet = new QueryServlet();
            servlet.doGet(request, response);

            assertEquals(
                    "<html><body>\n" +
                            "<h1>Product with max price: </h1>\n" +
                            "1\t4</br>\n" +
                            "</body></html>\n",
                    stringWriter.toString());

            verify(resultSet).close();
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType(CONTENT_TYPE);
        }
    }

    @Test
    public void test_min() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection(URL)).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(QUERY_GET_MIN_PRICE)).thenReturn(resultSet);
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getString(COLUMN_NAME))
                    .thenReturn("1");
            when(resultSet.getInt(COLUMN_PRICE))
                    .thenReturn(4);
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter(PARAMETER_COMMAND)).thenReturn("min");

            final QueryServlet servlet = new QueryServlet();
            servlet.doGet(request, response);

            assertEquals(
                    "<html><body>\n" +
                            "<h1>Product with min price: </h1>\n" +
                            "1\t4</br>\n" +
                            "</body></html>\n",
                    stringWriter.toString());

            verify(resultSet).close();
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType(CONTENT_TYPE);
        }
    }

    @Test
    public void test_sum() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection(URL)).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(QUERY_GET_SUM_PRICE)).thenReturn(resultSet);
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getInt(1))
                    .thenReturn(4);
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter(PARAMETER_COMMAND)).thenReturn("sum");

            final QueryServlet servlet = new QueryServlet();
            servlet.doGet(request, response);

            assertEquals(
                    "<html><body>\n" +
                            "Summary price: \n" +
                            "4\n" +
                            "</body></html>\n",
                    stringWriter.toString());

            verify(resultSet).close();
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType(CONTENT_TYPE);
        }
    }

    @Test
    public void test_count() throws SQLException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        try (MockedStatic<DriverManager> dummy = Mockito.mockStatic(DriverManager.class)) {
            dummy.when(() ->
                    DriverManager.getConnection(URL)).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(QUERY_COUNT_ALL)).thenReturn(resultSet);
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getInt(1))
                    .thenReturn(4);
            when(response.getWriter()).thenReturn(writer);
            when(request.getParameter(PARAMETER_COMMAND)).thenReturn("count");

            final QueryServlet servlet = new QueryServlet();
            servlet.doGet(request, response);

            assertEquals(
                    "<html><body>\n" +
                            "Number of products: \n" +
                            "4\n" +
                            "</body></html>\n",
                    stringWriter.toString());

            verify(resultSet).close();
            verify(statement).close();
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(response).setContentType(CONTENT_TYPE);
        }
    }

    @Test
    public void test_unknown_command() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(response.getWriter()).thenReturn(writer);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn("any");

        final QueryServlet servlet = new QueryServlet();
        servlet.doGet(request, response);

        assertEquals(
                "Unknown command: any\n",
                stringWriter.toString());

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).setContentType(CONTENT_TYPE);
    }
}
