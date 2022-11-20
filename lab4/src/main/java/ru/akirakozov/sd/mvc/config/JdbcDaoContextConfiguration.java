package ru.akirakozov.sd.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.akirakozov.sd.mvc.dao.TodoListJdbcDao;

import javax.sql.DataSource;

/**
 * @author valrun
 */
@Configuration
public class JdbcDaoContextConfiguration {
    @Bean
    public TodoListJdbcDao productJdbcDao(DataSource dataSource) {
        return new TodoListJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todolist.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
}
