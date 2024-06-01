package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    void removeAllTasks();

    void removeAllEpics();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    void updateTask(Task updatedTask);

    void updateEpic(Epic updatedEpic);

    void updateSubtasks(int epicId, List<Subtask> newSubtasks);

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    List<Subtask> getSubtasksByEpic(int id);

    void calculateEpicStatus(Epic epic);

    List<Task> getHistory();
}