package test;
import model.Task;
import model.Task.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.TaskManager;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import service.HistoryManager;
import service.InMemoryHistoryManager;
class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager(historyManager);
    }

    @Test
    void shouldNotConflictGeneratedId() {
        // Создаем задачу с генерированным id
        Task taskWithSpecifiedId = new Task("Task with specified ID", "Description", Status.NEW);
        taskWithSpecifiedId.setId(1); // Установливаем заданный id

        Task taskWithGeneratedId = new Task("Task with generated ID", "Description", Status.NEW);

        // Добавляем задачи
        taskManager.createTask(taskWithSpecifiedId);
        taskManager.createTask(taskWithGeneratedId);

        // Проверяем, что задачи с разными id не равны
        assertNotEquals(taskWithSpecifiedId, taskWithGeneratedId);
    }
}

