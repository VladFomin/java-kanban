package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private HashMap<Integer, Node> history = new HashMap<>();
    private Node head = null;
    private Node tail = null;

    private static class Node {
        Node prev;
        Node next;
        Task task;

        Node(Node prev, Node next, Task task) {
            this.prev = prev;
            this.next = next;
            this.task = task;
        }
    }

    public void linkLast(Task task) {
        Node newNode = new Node(null, null, task);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        history.put(task.getId(), newNode);
    }

    public void remove(int id) {
        Node nodeToRemove = history.get(id);
        if (nodeToRemove != null) {
            if (nodeToRemove.prev != null) {
                nodeToRemove.prev.next = nodeToRemove.next;
            } else {
                head = nodeToRemove.next;
            }
            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
            } else {
                tail = nodeToRemove.prev;
            }
            history.remove(id);
        }
    }

    public List<Task> getTasks() {
        List<Task> tasksList = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.task != null) {  // Проверка, что задача не равна null
                tasksList.add(currentNode.task);
            }
            currentNode = currentNode.next;
        }
        return tasksList;
    }

    @Override
    public void add(Task task) {
        if (history.containsKey(task.getId())) {
            remove(task.getId());
        }
        linkLast(task);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}