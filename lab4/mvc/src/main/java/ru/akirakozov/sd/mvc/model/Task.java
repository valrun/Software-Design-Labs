package ru.akirakozov.sd.mvc.model;

import java.util.Comparator;

/**
 * @author akirakozov
 */
public class Task {
    private int id;
    private String name;

    private String done;

    public Task() {
    }

    public Task(int id, String name, String done) {
        this.id = id;
        this.name = name;
        this.done = done;
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

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", done=" + done +
                '}';
    }
}
