package com.yandex.app.service;

import com.yandex.app.model.*;
import com.yandex.app.service.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    Task addTask(Task createdTask);

    Epic addEpic(Epic createdEpic);

    Subtask addSubtask(Subtask createdSubtask);

    void updateTask(Task updatedTask);

    Task getTaskById(int taskId);

    List<Task> getAllTasks();

    void removeTask(int taskId);

    void removeEpic(int epicId);

    void removeAllSubtasks();

    List<Integer> getAllSubtasksOfEpic(int epicId);

    void manageTaskAndEpicStatus(int taskId, TaskStatus status);

    ArrayList<Task> getTasks();

    ArrayList<Subtask> getSubtasks();

    ArrayList<Epic> getEpic();

    List<Task> getHistory();
}
