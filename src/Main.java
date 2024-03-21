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

        List<Subtask> subtasksForEpic1 = new ArrayList<>();
        Subtask subtask1ForEpic1 = new Subtask(5, "Вебинар", "Участие в онлайн-курсах или вебинарах.", TaskStatus.NEW);
        Subtask subtask2ForEpic1 = new Subtask(6, "Курсы", "Успешное прохождение курсов, выполнение заданий к дедлайнам.", TaskStatus.NEW);
        subtasksForEpic1.add(subtask1ForEpic1);
        subtasksForEpic1.add(subtask2ForEpic1);

        List<Subtask> subtasksForEpic2 = new ArrayList<>();
        Subtask subtask1ForEpic2 = new Subtask(7, "Репититор", "Выполнение пробников по экзамену.", TaskStatus.NEW);
        subtasksForEpic2.add(subtask1ForEpic2);

        Epic epic1 = new Epic(3, "Учеба", "Выполнение практических заданий или лабораторных работ.", TaskStatus.NEW, subtasksForEpic1);
        Epic epic2 = new Epic(4, "Подготовка к экзамену по программированию", "Подготовка и сдача экзамена", TaskStatus.NEW, subtasksForEpic2);

        TaskManager epicManager = new TaskManager();
        epicManager.addTask(epic1);
        epicManager.addTask(epic2);

        System.out.println("Список всех задач:");
        printTasks(taskManager.getAllTasks());

        System.out.println("Список всех эпиков:");
        printEpics(epicManager.getAllEpics());

        task1.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task1);
        task2.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task2);

        System.out.println("Обновленные статусы задач:");
        printTasks(taskManager.getAllTasks());

        taskManager.removeTask(1);
        epicManager.removeEpic(4);

        System.out.println("После удаления задачи и эпика:");
        System.out.println("Список всех задач:");
        printTasks(taskManager.getAllTasks());

        System.out.println("Список всех эпиков:");
        printEpics(epicManager.getAllEpics());
    }

    private static void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("Название: " + task.getTitle());
            System.out.println("ID: " + task.getTaskId());
            System.out.println("Описание: " + task.getDescription());
            System.out.println("Статус: " + task.getStatus());
            System.out.println("---------------------");
        }
    }

    private static void printEpics(List<Epic> epics) {
        for (Epic epic : epics) {
            System.out.println("Название: " + epic.getTitle());
            System.out.println("ID: " + epic.getTaskId());
            System.out.println("Описание: " + epic.getDescription());
            System.out.println("Статус: " + epic.getStatus());
            System.out.println("---------------------");

            System.out.println("Подзадачи эпика " + epic.getTitle() + ":");
            for (Subtask subtask : epic.getSubtasks()) {
                System.out.println("    Название: " + subtask.getTitle());
                System.out.println("    ID: " + subtask.getTaskId());
                System.out.println("    Описание: " + subtask.getDescription());
                System.out.println("    Статус: " + subtask.getStatus());
                System.out.println("    ---------------------");
            }
        }
    }
}
