package service;

public class Manager {
    public static TaskManager getDefaultHistory() {
        return new InMemoryTaskManager();
    }
}