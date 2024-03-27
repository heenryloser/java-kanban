import com.yandex.app.model.*;
import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskIdUniquenessTest {

    @Test
    void testTaskIdUniqueness() {
        Task task1 = new Task(1, "Title 1", "Description 1", TaskStatus.NEW);
        Task task2 = new Task(2, "Title 2", "Description 2", TaskStatus.IN_PROGRESS);

        assertNotEquals(task1.getTaskId(), task2.getTaskId());
    }
}
