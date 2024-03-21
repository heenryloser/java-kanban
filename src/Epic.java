import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks;

    public Epic(int taskId, String title, String description, TaskStatus status, List<Subtask> subtasks) {
        super(taskId, title, description, status);
        this.subtasks = subtasks;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}
