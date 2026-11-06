TO-DO LIST MANAGER - UML

Task
- id: int
- title: String
- description: String
- completed: boolean

TodoList implements TaskOperations
- tasks: ArrayList<Task>
- taskMap: HashMap<Integer, Task>

TaskFileStorage implements TaskStorage

TaskValidator

Main

TaskOperations (interface)
TaskStorage (interface)

TaskNotFoundException extends RuntimeException
InvalidTaskException extends RuntimeException

RELATIONSHIPS

Main --> TaskOperations
Main --> TaskStorage

TodoList ..|> TaskOperations
TaskFileStorage ..|> TaskStorage

TodoList --> Task : manages
TodoList --> HashMap : stores tasks by ID
TodoList --> ArrayList : stores tasks

TaskValidator --> Task : validates input

TaskNotFoundException --|> RuntimeException
InvalidTaskException --|> RuntimeException

UML DIAGRAM (PlantUML)

@startuml

class Task {
  - id: int
  - title: String
  - description: String
  - completed: boolean
  + markAsCompleted(): void
}

interface TaskOperations {
  + addTask(task: Task): void
  + displayTasks(): void
  + completeTask(id: int): void
  + deleteTask(id: int): void
  + findTask(id: int): Task
  + getTasks(): ArrayList<Task>
}

interface TaskStorage {
  + saveTasks(tasks: ArrayList<Task>): void
  + loadTasks(): ArrayList<Task>
}

class TodoList
class TaskFileStorage
class TaskValidator
class Main

class TaskNotFoundException
class InvalidTaskException

TodoList ..|> TaskOperations
TaskFileStorage ..|> TaskStorage

TaskNotFoundException --|> RuntimeException
InvalidTaskException --|> RuntimeException

TodoList "1" --> "*" Task
TodoList --> ArrayList
TodoList --> HashMap

Main --> TaskOperations
Main --> TaskStorage
Main --> TaskValidator

@enduml