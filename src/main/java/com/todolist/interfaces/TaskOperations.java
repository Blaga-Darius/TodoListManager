package com.todolist.interfaces;

import com.todolist.Task;
import java.util.ArrayList;

public interface TaskOperations {

    void addTask(Task task);

    void displayTasks();

    void completeTask(int id);

    void deleteTask(int id);

    Task findTask(int id);

    ArrayList<Task> getTasks();
}