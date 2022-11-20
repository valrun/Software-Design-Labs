package ru.akirakozov.sd.mvc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.akirakozov.sd.mvc.model.Task;
import ru.akirakozov.sd.mvc.model.TodoList;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author valrun
 */
public class TodoListJdbcDao extends JdbcDaoSupport implements TodoListDao {

    public TodoListJdbcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    private <T> List<T> getDataByRequest(String sql, Class tclass) {
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(tclass));
    }

    @Override
    public List<TodoList> getTodoLists() {
        String sql = "SELECT * FROM LISTS";
        String sqlTask = "SELECT * FROM TASKS WHERE LIST_ID = ";

        List<TodoList> list = getDataByRequest(sql, TodoList.class);

        list.forEach(todoList ->
                todoList.setTaskList(getDataByRequest(sqlTask + todoList.getId(), Task.class))
        );

        return list;
    }

    @Override
    public int addTodoList(TodoList todoList) {
        String sql = "INSERT INTO LISTS (NAME) VALUES (?)";
        return getJdbcTemplate().update(sql, todoList.getName());
    }

    @Override
    public void removeTodoList(int id) {
        String sql = "DELETE FROM LISTS WHERE ID = ?";
        getJdbcTemplate().update(sql, id);

        String sqlTask = "DELETE FROM TASKS WHERE LIST_ID = ?";
        getJdbcTemplate().update(sqlTask, id);
    }

    @Override
    public int addTask(Task task, int todoListId) {
        String sql = "INSERT INTO TASKS (NAME, LIST_ID) VALUES (?, ?)";
        return getJdbcTemplate().update(sql, task.getName(), todoListId);
    }

    @Override
    public void checkTask(int id) {
        String sql = "UPDATE TASKS SET DONE = 'T' WHERE ID = ?";
        getJdbcTemplate().update(sql, id);
    }
}
