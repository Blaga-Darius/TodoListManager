package com.todolist;

import com.todolist.exceptions.InvalidTaskException;

public class TaskValidator {

    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTaskException("Task title cannot be empty.");
        }

        if (title.length() > 50) {
            throw new InvalidTaskException("Task title cannot exceed 50 characters.");
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidTaskException("Task description cannot be empty.");
        }

        if (description.length() > 200) {
            throw new InvalidTaskException("Task description cannot exceed 200 characters.");
        }
    }

    public static void validateId(int id) {
        if (id <= 0) {
            throw new InvalidTaskException("Task ID must be greater than 0.");
        }
    }
}