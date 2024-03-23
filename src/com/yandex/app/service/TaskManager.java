package com.yandex.app.service;
import com.yandex.app.model.*;
import com.yandex.app.service.TaskStatus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int IdCounter;
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, Subtask> subtasks;

    public TaskManager() {
        IdCounter = 1;
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    public Task addTask(Task createdTask) {
        int taskId = createdTask.getTaskId();
        if (taskId == 0) {
            taskId = IdCounter++;
            createdTask.setTaskId(taskId);
        }

        tasks.put(taskId, createdTask);
        return createdTask;
    }

    public Epic addEpic(Epic createdEpic) {
        int epicId = createdEpic.getTaskId();
        if (epicId == 0) {
            epicId = IdCounter++;
            createdEpic.setTaskId(epicId);
        }

        epics.put(epicId, createdEpic);
        return createdEpic;
    }

    public Subtask addSubtask(Subtask createdSubtask){
        int subtaskId = createdSubtask.getTaskId();
        if (subtaskId == 0) {
            subtaskId = IdCounter++;
            createdSubtask.setTaskId(subtaskId);
        }
        if (createdSubtask.getStatus() == null){
            createdSubtask.setStatus(TaskStatus.NEW);
        }
        subtasks.put(createdSubtask.getTaskId(), createdSubtask);

        Epic relatedEpic = epics.get(createdSubtask.getEpicId());
        relatedEpic.addSubtask(createdSubtask.getTaskId());
        updateEpicStatus(relatedEpic);

        return createdSubtask;
    }

    public void updateTask(Task updatedTask) {
        int taskId = updatedTask.getTaskId();
        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, updatedTask);
        } else if (epics.containsKey(taskId)) {
            epics.put(taskId, (Epic) updatedTask);
        } else if (subtasks.containsKey(taskId)) {
            subtasks.put(taskId, (Subtask) updatedTask);
        }
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subtasks.values());
        return allTasks;
    }

    public void removeTask(int taskId) {
        Task taskToRemove = tasks.remove(taskId);
        if (taskToRemove instanceof Epic) {
            epics.remove(taskId);
        } else if (taskToRemove instanceof Subtask) {
            subtasks.remove(taskId);
        }
    }

    public void removeEpic(int epicId) {
        for (Epic epic : epics.values()) {
            epic.removeSubtask(epicId);
        }
        epics.remove(epicId);
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
        }
    }

    public List<Integer> getAllSubtasksOfEpic(int epicId) {
        List<Integer> subtaskIds = new ArrayList<>();
        for (Epic epic : epics.values()) {
            if (epic.getTaskId() == epicId) {
                subtaskIds.addAll(epic.getSubtaskIds());
                break;
            }
        }
        return subtaskIds;
    }

    public void manageTaskAndEpicStatus(int taskId, TaskStatus status) {
        Task task = tasks.get(taskId);
        if (task instanceof Epic) {
            Epic epic = (Epic) task;
            for (int subtaskId : epic.getSubtaskIds()) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    subtask.setStatus(status);
                }
            }
            updateEpicStatus(epic);
        }
    }

    private void updateEpicStatus(Epic epic) {
        boolean allSubtasksDone = true;
        boolean allSubtasksNew = true;
        for (int subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask != null) {
                if (subtask.getStatus() != TaskStatus.DONE) {
                    allSubtasksDone = false;
                }
                if (subtask.getStatus() != TaskStatus.NEW) {
                    allSubtasksNew = false;
                }
            }
        }
        if (allSubtasksDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (allSubtasksNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public ArrayList<Task> getTasks(){
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getEpic(){
        return new ArrayList<>(epics.values());
    }
}

