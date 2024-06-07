package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private int lastId = 0;
    private HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    // Получение списков задач
    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Удаление всех задач
    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Получение по индификатору
    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    // Создание объектов
    @Override
    public void createTask(Task task) {
        task.setId(++lastId);
        tasks.put(lastId, task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(++lastId);
        epics.put(lastId, epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(++lastId);
        subtasks.put(lastId, subtask);
    }

    // Обновление задач
    @Override
    public void updateTask(Task updatedTask) {
        int taskId = updatedTask.getId();

        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, updatedTask);
            System.out.println("Задача с идентификатором " + taskId + " успешно обновлена.");
        } else {
            System.out.println("Задача с идентификатором " + taskId + " не найдена.");
        }
    }

    @Override
    public String toString() {
        return "InMemoryTaskManager{" +
                "historyManager=" + historyManager +
                '}';
    }

    @Override
    public void updateEpic(Epic updatedEpic) {
        int epicId = updatedEpic.getId();

        if (epics.containsKey(epicId)) {
            epics.put(epicId, updatedEpic);
            System.out.println("Эпик с идентификатором " + epicId + " успешно обновлен.");
        } else {
            System.out.println("Эпик с идентификатором " + epicId + " не найден.");
        }
    }

    @Override
    public void updateSubtasks(int epicId, List<Subtask> newSubtasks) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            epic.setSubtasks(newSubtasks);
            System.out.println("Сабтаски эпика с идентификатором " + epicId + " успешно обновлены.");
        } else {
            System.out.println("Эпик с идентификатором " + epicId + " не найден.");
        }
    }

    // Удаление по индификатору
    @Override
    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            System.out.println("Задача с идентификатором " + id + " удалена.");
        } else {
            System.out.println("Задача с идентификатором " + id + " не найдена.");
        }
    }

    @Override
    public void deleteEpicById(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            List<Subtask> subtasksToDelete = epic.getSubtasks();
            for (Subtask subtask : subtasksToDelete) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
            System.out.println("Эпик с идентификатором " + id + " и все связанные с ним сабтаски успешно удалены.");
        } else {
            System.out.println("Эпик с идентификатором " + id + " не найден.");
        }
    }

    // Получение всех сабтасков определенного эпика
    @Override
    public List<Subtask> getSubtasksByEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            return epic.getSubtasks();
        } else {
            System.out.println("Эпик с идентификатором " + id + " не найден.");
            return new ArrayList<>();
        }
    }

    @Override
    public void calculateEpicStatus(Epic epic) {
        List<Subtask> subtasks = epic.getSubtasks();
        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != Task.Status.NEW) {
                allNew = false;
            }
            if (subtask.getStatus() != Task.Status.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            epic.setStatus(Task.Status.NEW);
        } else if (allDone) {
            epic.setStatus(Task.Status.DONE);
        } else {
            epic.setStatus(Task.Status.IN_PROGRESS);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}