package ru.akirakozov.sd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akirakozov.sd.mvc.dao.TodoListDao;
import ru.akirakozov.sd.mvc.model.Task;
import ru.akirakozov.sd.mvc.model.TodoList;

import java.util.List;

/**
 * @author valrun
 */
@Controller
public class TodoListController {
    private final TodoListDao todoListDao;

    public TodoListController(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @RequestMapping(value = "/add-todolist", method = RequestMethod.POST)
    public String addTodoList(@ModelAttribute("todolist") TodoList todoList) {
        todoListDao.addTodoList(todoList);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/remove-todolist", method = RequestMethod.POST)
    public String removeTodoList(@ModelAttribute("id") Integer id) {
        todoListDao.removeTodoList(id);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/get-todolists", method = RequestMethod.GET)
    public String getTodoLists(ModelMap map) {
        prepareModelMap(map, todoListDao.getTodoLists());
        return "index";
    }


    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") Task task,
                          @ModelAttribute("list-id") Integer id) {
        todoListDao.addTask(task, id);
        return "redirect:/get-todolists";
    }


    @RequestMapping(value = "/check-task", method = RequestMethod.POST)
    public String checkTask(@ModelAttribute("id") Integer id) {
        todoListDao.checkTask(id);
        return "redirect:/get-todolists";
    }

    private void prepareModelMap(ModelMap map, List<TodoList> todoLists) {
        map.addAttribute("todolists", todoLists);
        map.addAttribute("todolist", new TodoList());
        map.addAttribute("task", new Task());
    }
}
