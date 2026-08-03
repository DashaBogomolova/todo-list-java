import java.util.List;
import java.util.Scanner;

public class TaskConsoleUI {
//            Метод start() — запускает бесконечное меню.
//            Методы handleCreate(), handleUpdate(), handleDelete(), handleShowAll().
//            Вся работа с Scanner остаётся здесь.
    private final Scanner sc;
    private final TaskService taskService;
    public TaskConsoleUI(Scanner sc, TaskService service){
        this.sc = sc;
        this.taskService = service;
    }

    public void start(){
        showMenu();
        while(true){
            String action = sc.nextLine().trim();
            switch (action){
                case "0":
                    showMenu();
                    break;
                case "1":
                    handleShowAll();
                    break;

                case "2":
                    handleCreate();
                    break;

                case "3":
                    handleUpdate();
                    break;

                case "4":
                    handleMarkDone();
                    break;

                case "5":
                    handleDelete();
                    break;

                case "6":
                    System.out.println("До свидания!");
                    sc.close();
                    return;
            }
        }

    }

    public void showMenu(){
        System.out.println("""
            Действия:
            0. Вызов меню
            1. Просмотр задач
            2. Создание задачи
            3. Изменение задачи
            4. Изменения статуса выполнения задачи
            5. Удаление задачи
            6. Выход из приложения""");
    }


    public void handleCreate(){
        try{
            System.out.println("Создание задачи");
            String description = readNonEmptyLine("Введите описание задачи:");
            int priority = readPriority();
            Task created = taskService.createTask(description, priority);
            System.out.println("Задача успешно создана c id=" + created.getId());
        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

    }


    public void handleUpdate(){
        try{
            System.out.println("Изменение задачи");
            long id = readId();
            String description = readNonEmptyLine("Введите описание задачи:");
            int priority = readPriority();
            if(taskService.updateTask(id, description, priority)){
                System.out.println("Задача успешно изменена");
            }
            else{
                System.out.println("Задача с id " + id + " не найдена");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: " + e.getMessage());
        }

    }


    public void handleDelete(){
        System.out.println("Удаление задачи");
        long id = readId();
        if(taskService.deleteTask(id)){
            System.out.println("Задача успешно удалена");
        } else {
            System.out.println("Задача с id " + id + " не найдена");
        }

    }


    public void handleMarkDone(){
        System.out.println("Изменение статуса задачи");
        long id = readId();
        if(taskService.markTaskAsDone(id)){
            System.out.println("Задача выполнена");
        } else {
            System.out.println("Ошибка при изменении статуса задачи");
        }
    }


    public void handleShowAll(){
        List<Task> allTasks = taskService.getAllTasks();
        if (allTasks.isEmpty()){
            System.out.println("Список задач пуст");
        } else{
            for(Task task: allTasks){
                System.out.println(task);
            }
        }
    }


    //Вспомогательные методы
    String readNonEmptyLine(String prompt){
        System.out.println(prompt);
        String line = sc.nextLine().trim();
        while(line.trim().isEmpty()){
            System.out.println("Описание не может быть пустым");
            line = sc.nextLine().trim();
        }
        return line.trim();
    }


    int readPriority(){
        System.out.println("Введите приоритет задачи (1 - низкий, 2 - средний, 3 - высокий:");
        String priorityInput;
        int priority;

        while(true){
            priorityInput = sc.nextLine();
            try {
                priority = Integer.parseInt(priorityInput.trim());
                if (priority >= 1 && priority <= 3){
                    return priority;
                } else{
                    System.out.println("Приоритет должен быть от 1 до 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 3");
            }
        }

    }


    long readId(){
        System.out.println("Введите id задачи:");
        String idInput;
        long id;

        while(true){
            idInput = sc.nextLine();
            try {
                id = Long.parseLong(idInput.trim());
                return id;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }
}
