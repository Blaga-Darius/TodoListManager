# To-Do List Manager

## Description
To-Do List Manager is a Java console application that allows users to manage their daily tasks.

## Features
- Add new tasks
- Display all tasks
- Mark tasks as completed
- Delete tasks
- Save tasks to a text file
- Load saved tasks when the application starts
- Validate user input

## Technologies
- Java
- Maven
- IntelliJ IDEA
- Git and GitHub

## Project Structure
- `Task` – represents a task.
- `TodoList` – manages the collection of tasks.
- `TaskValidator` – validates user input.
- `TaskFileStorage` – handles file persistence.
- `TaskOperations` – interface for task operations.
- `TaskStorage` – interface for file storage.
- `TaskNotFoundException` – custom exception for missing tasks.
- `InvalidTaskException` – custom exception for invalid input.
- `Main` – application entry point.

## Data Persistence
Tasks are stored in `tasks.txt` and automatically loaded when the application starts.

## How to Run
1. Open the project in IntelliJ IDEA.
2. Make sure a compatible JDK is configured.
3. Run the `Main.java` file.
4. Use the console menu to manage tasks.

## UML Diagram
See `UML_Diagram.png` for the class diagram.