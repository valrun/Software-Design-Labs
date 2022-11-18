package ru.akirakozov.sd.mvc.dao;

import ru.akirakozov.sd.mvc.model.Task;
import ru.akirakozov.sd.mvc.model.TodoList;

import java.util.List;

/**
 * @author valrun
 */
public interface TodoListDao {
    List<TodoList> getTodoLists();

    int addTodoList(TodoList todoList);

    void removeTodoList(int id);

    int addTask(Task task, int todoListId);

    void checkTask(int id);

}
