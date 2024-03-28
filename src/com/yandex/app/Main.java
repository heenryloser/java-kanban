package com.yandex.app;

import com.yandex.app.model.*;
import com.yandex.app.service.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task(1, "Task 1", null, TaskStatus.NEW);
        Task task2 = new Task(2, "Task 2", null, TaskStatus.IN_PROGRESS);
        Epic epic1 = new Epic(3, "Epic 1", null, TaskStatus.NEW);
        Subtask subtask1 = new Subtask(4, "Subtask 1", null, TaskStatus.IN_PROGRESS, 3);

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addEpic(epic1);
        manager.addSubtask(subtask1);

        printAllTasks(manager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpic()) {
            System.out.println(epic);

            for (Integer subtaskId : manager.getAllSubtasksOfEpic(epic.getTaskId())) {
                Task subtask = manager.getTaskById(subtaskId);
                if (subtask != null) {
                    System.out.println("--> " + subtask);
                }
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
