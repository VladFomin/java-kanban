public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Task 1", "Description for Task 1", Task.TaskStatus.NEW);
        Task task2 = new Task("Task 2", "Description for Task 2", Task.TaskStatus.IN_PROGRESS);
        Task task3 = new Task("Task 3", "Description for Task 3", Task.TaskStatus.DONE);

        Subtask subtask1 = new Subtask("Subtask 1", "Description for Subtask 1", Task.TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask("Subtask 2", "Description for Subtask 2", Task.TaskStatus.IN_PROGRESS, 1);
        Subtask subtask3 = new Subtask("Subtask 3", "Description for Subtask 3", Task.TaskStatus.DONE, 2);


        Epic epic1 = new Epic("Epic 1", "Description for Epic 1", Task.TaskStatus.IN_PROGRESS);
        Epic epic2 = new Epic("Epic 2", "Description for Epic 2", Task.TaskStatus.NEW);
        epic1.addSubtask(subtask1);
        epic1.addSubtask(subtask2);
        epic2.addSubtask(subtask3);


        TaskManager taskManager = new TaskManager();


        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

    }
}