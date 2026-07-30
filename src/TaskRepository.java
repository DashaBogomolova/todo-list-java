import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskRepository {
    private ArrayList<Task> listTask = new ArrayList<>();
    private long nextId = 0;

    //CREATE
    public Task save(String description, int priorityNumber) throws IllegalArgumentException{
        long currentId = nextId;
        Task task = new Task(currentId, description, Priority.fromLevel(priorityNumber));
        listTask.add(task);
        nextId++;
        return task;
    }

    //READ
    public List<Task> read(){ // аккуратно возвращаем список, не нарушая инкапсуляции
        return Collections.unmodifiableList(listTask);
    }
    //UPDATE
    public boolean update(long id, String description, int priorityNumber){
        for(Task task: listTask){
            if (task.getId() == id){
                task.setDescription(description);
                task.setPriority(Priority.fromLevel(priorityNumber));
                return true;
            }
        }
        return false;
    }
    //DELETE
    public boolean deleteById(long id){
        for(Task task: listTask){
            if (task.getId() == id){
                listTask.remove(task);
                return true;
            }
        }
        return false;
    }
    //FIND
    public Task findById(long id){
        for(Task task: listTask){
            if (task.getId() == id){
                return task;
            }
        }
        return null;
    }




}
