import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();


    public void createTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Map<Integer, Task> getAllTasks() {
        return tasks;
    }


    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public void removeAllEpics() {
        epics.clear();
    }

    public Map<Integer, Epic> getAllEpics() {
        return epics;
    }


    public void createSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void deleteSubtaskById(int id) {
        subtasks.remove(id);
    }

    public void removeAllSubtasks() {
        subtasks.clear();
    }

    public Map<Integer, Subtask> getAllSubtasks() {
        return subtasks;
    }

    // Дополнительные методы
    public Map<Integer, Subtask> getSubtasksByEpicId(int epicId) {
        Map<Integer, Subtask> subtasksByEpicId = new HashMap<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                subtasksByEpicId.put(subtask.getId(), subtask);
            }
        }
        return subtasksByEpicId;
    }

}