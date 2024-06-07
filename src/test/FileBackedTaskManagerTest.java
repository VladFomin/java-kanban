import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import service.FileBackedTaskManager;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileBackedTaskManagerTest {

    private HistoryManager historyManager;
    private FileBackedTaskManager taskManager;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        File tempFile = Files.createTempFile(tempDir, "tasks", ".csv").toFile();
        historyManager = new InMemoryHistoryManager();
        taskManager = new FileBackedTaskManager(tempFile, historyManager);
    }

    @Test
    public void testSaveAndLoadEmptyFile() throws IOException {
        // Save the empty task manager state
        taskManager.save();

        // Load from the same file
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(taskManager.file, historyManager);

        // Ensure both task managers are equal and empty
        assertTrue(loadedManager.getAllTasks().isEmpty());
        assertTrue(loadedManager.getAllEpics().isEmpty());
        assertTrue(loadedManager.getAllSubtasks().isEmpty());
    }

    @Test
    public void testSaveAndLoadWithTasks() throws IOException {
        // Create and add tasks
        Task task1 = new Task("Task 1", "Description 1", Task.Status.NEW);
        Task task2 = new Task("Task 2", "Description 2", Task.Status.IN_PROGRESS);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        // Save the state
        taskManager.save();

        // Load from the same file
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(taskManager.file, historyManager);

        // Ensure the loaded manager has the same tasks
        List<Task> tasks = loadedManager.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

}
