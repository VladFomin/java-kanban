import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    public void shouldBeEqualIfIdsAreEqual() {
        // Тест проверяет, что Task равны, если равны их id
        Task task1 = new Task("Task1", "Description1", Task.Status.NEW);
        Task task2 = new Task("Task2", "Description2", Task.Status.DONE);
        task1.setId(1);
        task2.setId(1);
        assertEquals(task1, task2);
    }
}