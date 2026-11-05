package com.todolist;

import com.todolist.interfaces.TaskStorage;

import java.io.*;
import java.util.ArrayList;
import com.todolist.interfaces.TaskStorage;

public class TaskFileStorage implements TaskStorage {

    private static final String FILE_NAME = "tasks.txt";

    @Override
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.getId() + ";" + task.getTitle() + ";" +
                        task.getDescription() + ";" + task.isCompleted());
                writer.newLine();
            }
        }
    }

    @Override
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", 4);

                if (data.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String description = data[2];
                    boolean completed = Boolean.parseBoolean(data[3]);

                    Task task = new Task(id, title, description);

                    if (completed) {
                        task.markAsCompleted();
                    }

                    tasks.add(task);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid task in file.");
                }
            }
        }

        return tasks;
    }
}