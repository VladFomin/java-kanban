package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        // Проверяет есть ли задача в истории,если есть удаляет
        boolean containsTask = history.contains(task);
        if (containsTask) {
            history.remove(task);
        }
        history.add(task);
        while (history.size() > 10) {
            history.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }


}