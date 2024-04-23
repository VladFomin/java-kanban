import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;
import service.Manager;
import service.HistoryManager;

public class Main {
    public static void main(String[] args) {
        // Создание экземпляра HistoryManager
        HistoryManager historyManager = Manager.getDefaultHistory();

        // Создание менеджера задач, используя HistoryManager
        TaskManager taskManager = Manager.getDefault(historyManager);

        // Создание двух задач
        Task task1 = new Task("Задача 1", "Описание задачи 1", Task.Status.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", Task.Status.IN_PROGRESS);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        // Создание эпика с двумя подзадачами
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Task.Status.NEW, 1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Task.Status.IN_PROGRESS, 1);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", Task.Status.IN_PROGRESS);
        epic1.addSubtask(subtask1);
        epic1.addSubtask(subtask2);
        taskManager.createEpic(epic1);

        // Создание эпика с одной подзадачей
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Task.Status.DONE, 2);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", Task.Status.NEW);
        epic2.addSubtask(subtask3);
        taskManager.createEpic(epic2);

        // Распечатка списка эпиков, задач и подзадач
        System.out.println("Список эпиков: " + taskManager.getAllEpics());
        System.out.println("Список задач: " + taskManager.getAllTasks());
        System.out.println("Список подзадач: " + taskManager.getAllSubtasks());

        // Изменение статусов созданных объектов
        task1.setStatus(Task.Status.DONE);
        subtask1.setStatus(Task.Status.DONE);
        epic1.calculateEpicStatus();

        // Распечатка обновленных статусов
        System.out.println("Обновленный статус задачи 1: " + task1.getStatus());
        System.out.println("Обновленный статус подзадачи 1: " + subtask1.getStatus());
        System.out.println("Обновленный статус эпика 1: " + epic1.getStatus());

        // Удаление одной из задач и одного из эпиков
        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteEpicById(epic1.getId());

        // Распечатка обновленных списков
        System.out.println("Обновленный список задач: " + taskManager.getAllTasks());
        System.out.println("Обновленный список эпиков: " + taskManager.getAllEpics());
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Subtask subtask : manager.getSubtasksByEpic(epic.getId())) {
                System.out.println("--> " + subtask);
            }
        }
        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}