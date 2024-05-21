import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void shouldCorrectlyLinkAndRemoveVersionsOfTask() {
        // проверяем работу связного списка задач
        Task task1 = new Task("Task1", "Description1", Task.Status.NEW);
        Task task2 = new Task("Task2", "Description2", Task.Status.IN_PROGRESS);
        task1.setId(1);
        task2.setId(2);

        historyManager.linkLast(task1);
        historyManager.linkLast(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));

        historyManager.remove(1);
        history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task2, history.get(0));
    }

    @Test
    void shouldCorrectlyRemoveTaskFromHistory() {
        // Создаем и добавляем задачи в историю
        Task task1 = new Task("Task1", "Description1", Task.Status.NEW);
        Task task2 = new Task("Task2", "Description2", Task.Status.IN_PROGRESS);
        task1.setId(1);
        task2.setId(2);

        historyManager.linkLast(task1);
        historyManager.linkLast(task2);

        // Удаляем одну из задач
        historyManager.remove(1);

        // Проверяем, что история содержит только одну задачу
        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertFalse(history.contains(task1)); // Проверяем, что удаленная задача больше не присутствует в истории
        assertEquals(task2, history.get(0)); // Проверяем, что оставшаяся задача соответствует ожидаемым данным
    }
}