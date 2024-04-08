import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        // Создание менеджера задач
        TaskManager taskManager = new TaskManager();

        // Создание двух задач
        Task task1 = new Task("Задача 1", "Описание задачи 1", Task.TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Task.TaskStatus.IN_PROGRESS);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        // Создание эпика с двумя подзадачами
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Task.TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Task.TaskStatus.IN_PROGRESS, 1);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", Task.TaskStatus.IN_PROGRESS);
        epic1.addSubtask(subtask1);
        epic1.addSubtask(subtask2);
        taskManager.createEpic(epic1);

        // Создание эпика с одной подзадачей
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Task.TaskStatus.DONE, 2);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", Task.TaskStatus.NEW);
        epic2.addSubtask(subtask3);
        taskManager.createEpic(epic2);

        // Распечатка списка эпиков, задач и подзадач
        System.out.println("Список эпиков: " + taskManager.getAllEpics());
        System.out.println("Список задач: " + taskManager.getAllTasks());
        System.out.println("Список подзадач: " + taskManager.getAllSubtasks());

        // Изменение статусов созданных объектов
        task1.setTaskStatus(Task.TaskStatus.DONE);
        subtask1.setTaskStatus(Task.TaskStatus.DONE);
        epic1.calculateEpicStatus();

        // Распечатка обновленных статусов
        System.out.println("Обновленный статус задачи 1: " + task1.getTaskStatus());
        System.out.println("Обновленный статус подзадачи 1: " + subtask1.getTaskStatus());
        System.out.println("Обновленный статус эпика 1: " + epic1.getTaskStatus());

        // Удаление одной из задач и одного из эпиков
        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteEpicById(epic1.getId());

        // Распечатка обновленных списков
        System.out.println("Обновленный список задач: " + taskManager.getAllTasks());
        System.out.println("Обновленный список эпиков: " + taskManager.getAllEpics());
    }
}