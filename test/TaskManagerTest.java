import com.yandex.app.model.*;
import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TaskManagerTest {
    private TaskManager taskManager = Managers.getDefault();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void updateTask() {
        Task task = new Task(1, "Test updateTask", "Description", TaskStatus.NEW);
        final int taskId = taskManager.addTask(task).getTaskId();

        Task updatedTask = new Task(taskId, "Updated Task", "Updated Description", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updatedTask);

        Task savedTask = taskManager.getTaskById(taskId);
        assertEquals(updatedTask, savedTask);
    }
}
