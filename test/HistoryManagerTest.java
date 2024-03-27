import com.yandex.app.model.*;
import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HistoryManagerTest {

    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void testHistoryManager() {
        Task originalTask = new Task(1, "Original Task", "Original Description", TaskStatus.NEW);
        Task updatedTask1 = new Task(1, "Updated Task 1", "Updated Description 1", TaskStatus.IN_PROGRESS);
        Task updatedTask2 = new Task(1, "Updated Task 2", "Updated Description 2", TaskStatus.DONE);

        historyManager.add(originalTask);

        historyManager.add(updatedTask1);
        historyManager.add(updatedTask2);

        List<Task> taskVersions = historyManager.getTaskHistory(1);

        assertEquals(3, taskVersions.size());
    }
}
