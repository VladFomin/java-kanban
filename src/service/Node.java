package service;

import model.Task;

public class Node {
    Task task;
    Node prev;
    Node next;

    Node(Node prev, Node next, Task task) {
        this.task = task;
        this.prev = prev;
        this.next = next;
    }
}