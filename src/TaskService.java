import java.util.List;
import java.util.Optional;

//Бизнес-логика, валидация, преобразование данных,
// вызов репозитория. Слой между UI и репозиторием.
public class TaskService {
    private final TaskRepository repository;

    public TaskService (TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String description, int priorityLevel){
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        Priority priority = Priority.fromLevel(priorityLevel);
        Task task = new Task(description, priority); //не указываем id задачи в конструкторе
        return repository.save(task); //репозиторий присвоит id
    }


    public boolean updateTask(long id, String description, int priorityLevel){
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }
        Optional<Task> optional = repository.findById(id);
        Task updateTask;
        if(optional.isPresent()){
            updateTask = new Task(description, Priority.fromLevel(priorityLevel));
            updateTask.setId(id);
            return repository.update(updateTask);
        }
        return false;
    }


    public List<Task> getAllTasks(){
        return repository.findAll();
    }
    public Optional<Task> getTaskById(long id){
        return repository.findById(id);
    }
    public boolean deleteTask(long id){
        return repository.deleteById(id);
    }


    public boolean markTaskAsDone(long id){
        return repository.updateDone(id, true);
    }


}
