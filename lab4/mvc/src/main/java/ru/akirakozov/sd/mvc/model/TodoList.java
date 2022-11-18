package ru.akirakozov.sd.mvc.model;


import java.util.Collections;
import java.util.List;

/**
 * @author valrun
 */
public class TodoList {
    private int id;
    private String name;

    private List<Task> taskList;

    public TodoList() {
    }

    public TodoList(int id, String name) {
        this.id = id;
        this.name = name;
        this.taskList = Collections.emptyList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
