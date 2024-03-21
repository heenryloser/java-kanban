import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int taskIdCounter;
    private int epicIdCounter;
    private Map<Integer, Task> allTasks;
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, Subtask> subtasks;

    public TaskManager() {
        taskIdCounter = 1;
        epicIdCounter = 1;
        allTasks = new HashMap<>();
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    public void addTask(Task task) {
        int taskId = task.getTaskId();
        if (taskId == 0) {
            taskId = taskIdCounter++;
            task.setTaskId(taskId);
        }
        allTasks.put(taskId, task);
        if (task instanceof Epic) {
            epics.put(taskId, (Epic) task);
        } else if (task instanceof Subtask) {
            subtasks.put(taskId, (Subtask) task);
        } else {
            tasks.put(taskId, task);
        }
    }

    public void removeAllTasks() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
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
        return allTasks.get(taskId);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(allTasks.values());
    }

    public void removeTask(int taskId) {
        Task taskToRemove = allTasks.remove(taskId);
        if (taskToRemove instanceof Epic) {
            epics.remove(taskId);
        } else if (taskToRemove instanceof Subtask) {
            subtasks.remove(taskId);
        }
    }

    public void removeEpic(int epicId) {
        epics.remove(epicId);
        allTasks.remove(epicId);
    }

    public List<Subtask> getAllSubtasksOfEpic(int epicId) {
        List<Subtask> subtasksOfEpic = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task instanceof Epic && task.getTaskId() == epicId) {
                Epic epic = (Epic) task;
                subtasksOfEpic.addAll(epic.getSubtasks());
                break;
            }
        }
        return subtasksOfEpic;
    }

    public void manageTaskAndEpicStatus(int taskId, TaskStatus status) {
        Task task = allTasks.get(taskId);
        if (task instanceof Epic) {
            Epic epic = (Epic) task;
            for (Subtask subtask : epic.getSubtasks()) {
                subtask.setStatus(status);
            }
            updateEpicStatus(epic);
        }
    }

    private void updateEpicStatus(Epic epic) {
        boolean allSubtasksDone = true;
        boolean allSubtasksNew = true;
        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getStatus() != TaskStatus.DONE) {
                allSubtasksDone = false;
            }
            if (subtask.getStatus() != TaskStatus.NEW) {
                allSubtasksNew = false;
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

    public List<Epic> getAllEpics() {
        List<Epic> epics = new ArrayList<>();
        for (Task task : allTasks.values()) {
            if (task instanceof Epic) {
                epics.add((Epic) task);
            }
        }
        return epics;
    }
}

