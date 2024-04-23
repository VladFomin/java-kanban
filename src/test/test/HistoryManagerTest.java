package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import java.util.List;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void shouldSavePreviousVersionOfTask() {
        // Создаем и добавляем начальную версию задачи
        Task initialTask = new Task("Task", "Initial Description", Task.TaskStatus.NEW);
        historyManager.add(initialTask);

        // Создаем и добавляем обновленную версию задачи
        Task updatedTask = new Task("Updated Task", "Updated Description", Task.TaskStatus.IN_PROGRESS);
        historyManager.add(updatedTask);

        List<Task> history = historyManager.getHistory();

        // Проверяем,что история содержит две версии задачи
        assertEquals(2, history.size());

        // Проверяем,что первая версия задачи соответствует начальным данным
        assertEquals("Task", history.get(0).getName());
        assertEquals("Initial Description", history.get(0).getDescription());
        assertEquals(Task.TaskStatus.NEW, history.get(0).getTaskStatus());

        // Проверяем,что вторая версия задачи соответствует обновленным данным
        assertEquals("Updated Task", history.get(1).getName());
        assertEquals("Updated Description", history.get(1).getDescription());
        assertEquals(Task.TaskStatus.IN_PROGRESS, history.get(1).getTaskStatus());
    }
}