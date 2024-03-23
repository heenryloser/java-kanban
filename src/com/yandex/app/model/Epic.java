package com.yandex.app.model;
import com.yandex.app.service.TaskStatus;

import java.util.List;
import java.util.ArrayList;

public class Epic extends Task {
    private List<Integer> subtaskIds;

    public Epic(int taskId, String title, String description, TaskStatus status) {
        super(taskId, title, description, status);
        this.subtaskIds = new ArrayList<>();
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(List<Integer> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }

    public void removeSubtask(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }

    public void clearSubtasks() {
        subtaskIds.clear();
    }

    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
    }
}


