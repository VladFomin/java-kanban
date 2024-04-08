package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.Task.TaskStatus;

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
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
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
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    // Создание объектов

    public void createTask(Task task) {
        task.setId(++lastId);
        tasks.put(lastId, task);
    }

    public void createEpic(Epic epic) {
        epic.setId(++lastId);
        epics.put(lastId, epic);
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(++lastId);
        subtasks.put(lastId, subtask);
    }

    //Обновление задач

    public void updateTask(Task updatedTask) {
        int taskId = updatedTask.getId();

        if (tasks.containsKey(taskId)) {
            tasks.put(taskId, updatedTask);
            System.out.println("Задача с идентификатором " + taskId + " успешно обновлена.");
        } else {
            System.out.println("Задача с идентификатором " + taskId + " не найдена.");
        }
    }

    public void updateEpic(Epic updatedEpic) {
        int epicId = updatedEpic.getId();

        if (epics.containsKey(epicId)) {
            epics.put(epicId, updatedEpic);
            System.out.println("Задача с индефикатором" + epicId + "успешно добавлена");
        } else {
            System.out.println("Задача с идентификатором" + epicId + "успешно добавлена");
        }
    }

    public void updateSubtasks(int epicId, List<Subtask> newSubtasks) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            epic.setSubtasks(newSubtasks);
            System.out.println("Сабтаски эпика с идентификатором " + epicId + " успешно обновлены.");
        } else {
            System.out.println("Эпик с идентификатором " + epicId + " не найден.");
        }
    }

    //Удаление по индификатору

    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            System.out.println("Задача с идентификатором" + id + "удалена.");
        } else {
            System.out.println("Задача с идентификатором" + id + "не найдена");
        }
    }

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

    //Получение всех сабтасков определенного эпика
public List<Subtask> getSubtasksByEpic(int id) {
    if (epics.containsKey(id)) {
        Epic epic = epics.get(id);
        List<Subtask> subtasksByEpic = epic.getSubtasks();
        return subtasksByEpic;
    } else {
        System.out.println("Эпик с идентификатором" + id + "не найден.");
        return new ArrayList<>();
    }
 }


    public void calculateEpicStatus(Epic epic) {
        List<Subtask> subtasks = epic.getSubtasks();
        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus() != TaskStatus.NEW) {
                allNew = false;
            }
            if (subtask.getTaskStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (allDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
