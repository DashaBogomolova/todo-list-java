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
            4. Изменения статуса выполнения задачи
            5. Удаление задачи
            6. Выход из приложения""");

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
                    String priorityInput2 = sc.nextLine();
                    int priority2;
                    try {
                        priority2 = Integer.parseInt(priorityInput2.trim());
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
                String stringInputId3 = sc.nextLine();
                long id3;
                try {
                    id3 = Long.parseLong(stringInputId3.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: неверый id задачи");
                    break;
                }

                System.out.println("Введите описание задачи:");
                String description3 = sc.nextLine();
                if (description3.trim().isEmpty()) {
                    System.out.println("Описание не может быть пустым");
                    break;
                }
                int priority3;
                while(true){
                    System.out.println("Введите приоритет задачи (1 - низкий, 2 - средний, 3 - высокий:");
                    String priorityInput3 = sc.nextLine();

                    try {
                        priority3 = Integer.parseInt(priorityInput3.trim());

                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите число от 1 до 3");
                        continue;
                    }
                    try {
                        System.out.println(tasks.update(id3, description3, priority3)?"Задача успешно изменена" : "Возникла ошибка изменении задачи");
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        System.out.println("Попробуйте снова");
                    }
                }

                break;

            //Изменения статуса выполнения
            case "4":
                System.out.println("Изменение статуса выполнения задачи");
                System.out.println("Введите id задачи:");
                String stringInputId4 = sc.nextLine();
                long id4;
                try {
                    id4 = Long.parseLong(stringInputId4.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: неверый id задачи");
                    break;
                }
                System.out.println(tasks.updateDone(id4, true) ? "Задача выполнена" : "Возникла ошибка при обновлении статуса выполнения задачи");
                break;

            case "5": //удаление
                System.out.println("Удаление задачи");
                System.out.println("Введите id задачи:");
                String stringInputId5 = sc.nextLine();
                long id5;
                try {
                    id5 = Long.parseLong(stringInputId5.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: неверый id задачи");
                    break;
                }
                System.out.println(tasks.deleteById(id5) ? "Задача успешно удалена" : "Возникла ошибка удалении задачи");
                break;

            case "6": //выход
                System.out.println("До скорых встреч!");
                sc.close();
                return;
            default:
                System.out.println("Вы ввели неверную команду, попробуйте еще раз");
        }
    }

}
