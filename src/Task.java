import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//хранит данные задачи (поля, геттеры/сеттеры,
// конструктор с проверкой, toString).
public class Task {
    private String description; //Описание задачи
    private Priority priority; //приоритет выполнение
    private boolean done; //статус выполнения
    private  long id;

    private final LocalDateTime createdAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    //description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        this.description = description;
    }

    //priority
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Приоритет не может быть null");
        }
        this.priority = priority;
    }

    //done
    public boolean isDone(){ return done;}
    public void setDone(boolean done){ this.done = done;}

    //id
    void setId(long id) {
        this.id = id;
    }
    public long getId(){ return id;}

    //final
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    //Конструктор
    public Task(String description, Priority priority){
        //this.id = id;
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        this.description = description.trim();
        this.priority = priority;
        this.done = false;
        createdAt = LocalDateTime.now();
    }

    //toString
    public String toString(){
        return "id: " + id + ", Приоритет: " + priority.getTranslation() + "; задача: " + description + "; Статус выполнения: " + (done ? "Выполнено" : "Не выполнено") + "; Дата создания: " + createdAt.format(formatter);
    }
}

//Перечисление для приоритета
//определяет уровни приоритета,
// их числовые значения и строковые названия.
enum Priority{

    LOW(1, "низкий"),
    MEDIUM(2, "средний"),
    HIGH(3, "высокий");

    private final int level;
    private final String translation;

    //Конструктор
    Priority(int level, String rusDescription) {
        this.level = level;
        this.translation = rusDescription;
    }
    public static Priority fromLevel(int level){
        return switch (level) {
            case 1 -> LOW;
            case 2 -> MEDIUM;
            case 3 -> HIGH;
            default -> throw new IllegalArgumentException("Неверный уровень приоритета: " + level);
        };

    }

    //Геттеры
    public String getTranslation(){
        return translation;
    }
    public int getLevel(){
        return level;
    }

}
