package com.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import com.todolist.interfaces.TaskOperations;
import com.todolist.exceptions.TaskNotFoundException;

public class TodoList implements TaskOperations {

    private ArrayList<Task> tasks;
    private HashMap<Integer, Task> taskMap;

    public TodoList() {
        tasks = new ArrayList<>();
        taskMap = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskMap.put(task.getId(), task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void completeTask(int id) {
        Task task = taskMap.get(id);

        if (task != null) {
            task.markAsCompleted();
            System.out.println("Task completed successfully!");
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " was not found.");
        }
    }

    public void deleteTask(int id) {
        Task task = taskMap.get(id);

        if (task != null) {
            tasks.remove(task);
            taskMap.remove(id);
            System.out.println("Task deleted successfully!");
        } else {
            throw new TaskNotFoundException("Task with ID " + id + " was not found.");
        }
    }

    public Task findTask(int id) {
        return taskMap.get(id);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}