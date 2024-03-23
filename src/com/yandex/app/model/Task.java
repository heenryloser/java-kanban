package com.yandex.app.model;
import com.yandex.app.service.TaskStatus;


import java.util.Objects;

public class Task {
    private int Id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(int taskId, String title, String description, TaskStatus status) {
        this.Id = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getTaskId() {
        return Id;
    }

    public void setTaskId(int taskId) {
        this.Id = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Id == task.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Название: " + getTitle() + "\n" +
                "ID: " + getTaskId() + "\n" +
                "Описание: " + getDescription() + "\n" +
                "Статус: " + getStatus() + "\n" +
                "---------------------" + "\n";
    }
}



