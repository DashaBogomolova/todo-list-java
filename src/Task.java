import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description; //Описание задачи
    private Priority priority; //приоритет выполнение
    private boolean done; //статус выполнения

    private final long ID;
    private final LocalDateTime createdAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    //description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //priority
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    //done
    public boolean getDone(){ return done;}
    public void setDone(boolean done){ this.done = done;}

    //final
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public long getId(){ return ID;}

    //Конструктор
    public Task(long id, String description, Priority priority){
        this.ID = id;
        createdAt = LocalDateTime.now();

        if(description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("Описание задачи не может быть пустым");
        }
        this.description = description.trim();

        this.priority = priority;
        this.done = false;
    }

    //toString
    public String toString(){
        return "id: " + ID + ", Приоритет: " + priority.getTranslation() + "; задача: " + description + "; Статус выполнения: " + (done ? "Выполнено" : "Не выполнено") + "; Дата создания: " + createdAt.format(formatter);
    }
}

//Перечисление для приоритета
enum Priority{

    LOW(1, "низкий"),
    MEDIUM(2, "средний"),
    HIGH(3, "высокий");

    private int level;
    private String translation;

    //Конструктор
    Priority(int level, String rusDescription) {
        this.level = level;
        this.translation = rusDescription;
    }
    public static Priority fromLevel(int level){
        switch(level){
            case 1: return LOW;
            case 2: return MEDIUM;
            case 3: return  HIGH;
            default: throw new IllegalArgumentException("Неверный уровень приоритета: " + level);
        }

    }

    //Геттеры
    public String getTranslation(){
        return translation;
    }
    public int getLevel(){
        return level;
    }

}
