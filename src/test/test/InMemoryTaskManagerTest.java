package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import model.Epic;
import model.Subtask;
import model.Task;
import model.Task.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.TaskManager;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();

    }


    @Test
    void shouldNotConflictGeneratedId() {
        // Создаем задачу с генерированным id
        Task taskWithSpecifiedId = new Task("Task with specified ID", "Description", TaskStatus.NEW);
        taskWithSpecifiedId.setId(1); // Установливаем заданный id

        Task taskWithGeneratedId = new Task("Task with generated ID", "Description", TaskStatus.NEW);

        // Добавляем задачи
        taskManager.createTask(taskWithSpecifiedId);
        taskManager.createTask(taskWithGeneratedId);

        // Проверяем,что задачи с разными id не равны
        assertNotEquals(taskWithSpecifiedId, taskWithGeneratedId);
    }
}