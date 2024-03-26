import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private int lastId = 0;

        // Получение списков задач
    public List<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    // Удаление всех задач

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Получение по индефикатору
    public Task getTaskById (int id){
        return tasks.get(id);
    }

    public Epic getEpicById (int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id){
        return subtasks.get(id);
    }

    // Создание объектов

    public void createTask(Task task) {
        task.setId(++lastId);
        tasks.put(lastId, task);
    }1

}