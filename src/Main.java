//TIP Чтобы <b>запустить</b> код, нажмите <shortcut actionId="Run"/> или
// нажмите на значок <icon src="AllIcons.Actions.Execute"/> в поле.
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    TaskRepository tasks = new TaskRepository();
    System.out.println("""
            Действия:
            1. Просмотр задач
            2. Создание задачи
            3. Изменение задачи
            4. Удаление задачи
            5. Выход из приложения""");

    while(true){
        String action = sc.nextLine().trim();
        switch (action) {
            case "1": // просмотр
                System.out.println("Ваши задачи: ");
                for (Task task : tasks.read()) {
                    System.out.println(task);
                }
                break;
            case "2": //создание
                System.out.println("Создание задачи");
                System.out.println("Введите описание задачи:");
                String description2 = sc.nextLine();
                if (description2.trim().isEmpty()) {
                    System.out.println("Описание не может быть пустым");
                    break;
                }
                while (true) {
                    System.out.println("Введите приоритет задачи (1 - низкий, 2 - средний, 3 - высокий:");
                    String priorityInput = sc.nextLine();
                    int priority2;
                    try {
                        priority2 = Integer.parseInt(priorityInput.trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите число от 1 до 3");
                        continue;
                    }
                    try{
                        Task saved = tasks.save(description2, priority2);
                        if (saved != null) {
                            System.out.println("Задача успешно создана, id="+ saved.getId());
                        } else{
                            System.out.println("Ошибка при создании задачи");
                        }
                        break;
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        System.out.println("Попробуйте снова");
                    }
                }
                break;

            case "3": //изменение
                System.out.println("Изменение задачи");
                System.out.println("Введите id задачи:");
                int id3 = sc.nextInt();
                sc.nextLine();
                System.out.println("Введите описание задачи:");
                String description3 = sc.nextLine();
                System.out.println("Введите приоритет задачи (1 - низкий, 2 - средний, 3 - высокий:");
                int priority3 = sc.nextInt();
                System.out.println(tasks.update(id3, description3, priority3)?"Задача успешно изменена" : "Возникла ошибка изменении задачи");
                break;

            case "4": //удаление
                System.out.println("Удаление задачи");
                System.out.println("Введите id задачи:");
                long id4 = sc.nextLong();
                sc.nextLine();
                System.out.println(tasks.deleteById(id4) ? "Задача успешно удалена" : "Возникла ошибка удалении задачи");
                break;

            case "5": //выход
                System.out.println("До скорых встреч!");
                sc.close();
                return;
            default:
                System.out.println("Вы ввели неверную команду, попробуйте еще раз");
        }
    }

}
