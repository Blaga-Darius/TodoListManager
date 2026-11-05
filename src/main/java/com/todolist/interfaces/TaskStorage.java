package com.todolist.interfaces;

import com.todolist.Task;
import java.io.IOException;
import java.util.ArrayList;

public interface TaskStorage {

    void saveTasks(ArrayList<Task> tasks) throws IOException;

    ArrayList<Task> loadTasks() throws IOException;
}