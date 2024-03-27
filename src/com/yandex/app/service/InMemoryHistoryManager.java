package com.yandex.app.service;

import com.yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history;
    private Map<Integer, List<Task>> taskHistoryMap;

    public InMemoryHistoryManager() {
        this.history = new ArrayList<>();
        this.taskHistoryMap = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        history.add(task);

        if (history.size() > 10) {
            history.remove(0);
        }

        List<Task> taskHistory = taskHistoryMap.getOrDefault(task.getTaskId(), new ArrayList<>());
        taskHistory.add(task);
        taskHistoryMap.put(task.getTaskId(), taskHistory);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public List<Task> getTaskHistory(int taskId) {
        return taskHistoryMap.getOrDefault(taskId, new ArrayList<>());
    }
}
