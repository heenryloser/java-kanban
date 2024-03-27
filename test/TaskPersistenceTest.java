import com.yandex.app.model.*;
import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskPersistenceTest {

    @Test
    void testTaskPersistence() {
        TaskManager taskManager = new InMemoryTaskManager();

        Task task = new Task(1, "Title", "Description", TaskStatus.NEW);

        taskManager.addTask(task);

        Task savedTask = taskManager.getTaskById(task.getTaskId());

        assertNotNull(savedTask);
        assertEquals(task, savedTask);
    }
}