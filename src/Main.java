//TIP Чтобы <b>запустить</b> код, нажмите <shortcut actionId="Run"/> или
// нажмите на значок <icon src="AllIcons.Actions.Execute"/> в поле.
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    TaskRepository repository = new TaskRepository();
    TaskService service = new TaskService(repository);
    TaskConsoleUI ui = new TaskConsoleUI(sc, service);
     ui.start();

}
