import com.yandex.app.model.*;
import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskEqualityTest {

    @Test
    void testTaskEqualityById() {
        Task task1 = new Task(1, "Title 1", "Description 1", TaskStatus.NEW);
        Task task2 = new Task(1, "Title 2", "Description 2", TaskStatus.IN_PROGRESS);

        assertEquals(task1.getTaskId(), task2.getTaskId());
    }

    @Test
    void testEpicEqualityById() {
        Epic epic1 = new Epic(2, "Epic 1", "Description 1", TaskStatus.NEW);
        Epic epic2 = new Epic(2, "Epic 2", "Description 2", TaskStatus.IN_PROGRESS);

        assertEquals(epic1.getTaskId(), epic2.getTaskId());
    }

    @Test
    void testSubtaskEqualityById() {
        Subtask subtask1 = new Subtask(3, "Subtask 1", "Description 1", TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask(3, "Subtask 2", "Description 2", TaskStatus.IN_PROGRESS, 2);

        assertEquals(subtask1.getTaskId(), subtask2.getTaskId());
    }
}

