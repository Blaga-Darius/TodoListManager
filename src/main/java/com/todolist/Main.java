package com.todolist;

import com.todolist.exceptions.InvalidTaskException;
import com.todolist.exceptions.TaskNotFoundException;
import com.todolist.interfaces.TaskOperations;
import com.todolist.interfaces.TaskStorage;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        TaskOperations todoList = new TodoList();

        String fileName = "tasks.txt";

        if (args.length > 0 && !args[0].isBlank()) {
            fileName = args[0];
        }

        TaskStorage storage = new TaskFileStorage(fileName);

        System.out.println("Using storage file: " + fileName);

        loadTasks(todoList, storage);

        boolean running = true;

        while (running) {

            System.out.println("\n===== TO-DO LIST MANAGER =====");
            System.out.println("1. Add a task");
            System.out.println("2. Display all tasks");
            System.out.println("3. Complete a task");
            System.out.println("4. Delete a task");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {

                    case "1":
                        addTask(todoList, storage);
                        break;

                    case "2":
                        todoList.displayTasks();
                        break;

                    case "3":
                        completeTask(todoList, storage);
                        break;

                    case "4":
                        deleteTask(todoList, storage);
                        break;

                    case "0":
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid menu option!");
                }

            } catch (InvalidTaskException | TaskNotFoundException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void addTask(TaskOperations todoList, TaskStorage storage)
            throws IOException {

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        TaskValidator.validateTitle(title);
        TaskValidator.validateDescription(description);

        int id = 1;

        for (Task task : todoList.getTasks()) {
            if (task.getId() >= id) {
                id = task.getId() + 1;
            }
        }

        Task task = new Task(id, title, description);

        todoList.addTask(task);

        storage.saveTasks(todoList.getTasks());

        System.out.println("Task added successfully!");
    }

    private static void completeTask(TaskOperations todoList, TaskStorage storage) throws IOException {
        System.out.print("Enter task ID: ");
        String input = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number!");
            return;
        }

        TaskValidator.validateId(id);
        todoList.completeTask(id);
        storage.saveTasks(todoList.getTasks());
    }

    private static void deleteTask(TaskOperations todoList, TaskStorage storage) throws IOException {
        System.out.print("Enter task ID: ");
        String input = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number!");
            return;
        }

        TaskValidator.validateId(id);
        todoList.deleteTask(id);
        storage.saveTasks(todoList.getTasks());
    }

    private static void loadTasks(TaskOperations todoList, TaskStorage storage) {

        try {
            for (Task task : storage.loadTasks()) {
                todoList.addTask(task);
            }

            System.out.println("Tasks loaded successfully!");

        } catch (IOException | NumberFormatException | SecurityException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
        }
    }
}