import java.util.*;

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
                if (description != null){
                    task.setDescription(description);
                } else{
                    return false;
                }
                task.setPriority(Priority.fromLevel(priorityNumber));
                return true;
            }
        }
        return false;
    }
    //DONE
    public boolean updateDone(long id, boolean b){
        for(Task task: listTask){
            if (task.getId() == id){
                task.setDone(b);
                return true;
            }
        }
        return false;
    }

    //DELETE
    public boolean deleteById(long id){
        Iterator<Task> it = listTask.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }
    public Optional<Task> findById(long id) {
        return listTask.stream().filter(t -> t.getId() == id).findFirst();
    }




}
