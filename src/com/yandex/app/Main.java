package com.yandex.app;
import com.yandex.app.model.*;
import com.yandex.app.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Task task1 = new Task(1, "Переезд", "Переезд в квартиру мечты.", TaskStatus.NEW);
        Task task2 = new Task(2, "Устроиться на работу", "Сменить сферу деятельности, найти свое место.", TaskStatus.IN_PROGRESS);
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Epic epic1 = new Epic(3, "Учеба", "Выполнение практических заданий или лабораторных работ.", TaskStatus.NEW);
        Epic epic2 = new Epic(4, "Подготовка к экзамену по программированию", "Подготовка и сдача экзамена", TaskStatus.NEW);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        Subtask subtask1ForEpic1 = new Subtask(5, "Вебинар", "Участие в онлайн-курсах или вебинарах.", TaskStatus.NEW, epic1.getTaskId());
        Subtask subtask2ForEpic1 = new Subtask(6, "Курсы", "Успешное прохождение курсов, выполнение заданий к дедлайнам.", TaskStatus.NEW, epic1.getTaskId());
        taskManager.addSubtask(subtask1ForEpic1);
        taskManager.addSubtask(subtask2ForEpic1);

        Subtask subtask1ForEpic2 = new Subtask(7, "Репетитор", "Выполнение пробников по экзамену.", TaskStatus.NEW, epic2.getTaskId());
        taskManager.addSubtask(subtask1ForEpic2);

        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(taskManager.getEpic());

        System.out.println("Список всех подзадач:");
        System.out.println(taskManager.getSubtasks());

        task1.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task1);
        task2.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task2);

        System.out.println("Обновленные статусы задач:");
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(taskManager.getEpic());

        System.out.println("Список всех подзадач:");
        System.out.println(taskManager.getSubtasks());

        taskManager.removeTask(1);
        taskManager.removeEpic(4);

        System.out.println("После удаления задачи и эпика:");
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(taskManager.getEpic());

        System.out.println("Список всех подзадач:");
        System.out.println(taskManager.getSubtasks());
    }
}
