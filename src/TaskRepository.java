import java.util.*;
//Ответственность: хранение задач в коллекции,
// базовые CRUD-операции, генерация ID.
public class TaskRepository {
    private ArrayList<Task> listTask = new ArrayList<>();
    private long nextId = 0;

    //CREATE
    public Task save(Task task){
        task.setId(nextId++);
        listTask.add(task);
        return task;
    }

    //UPDATE
    public boolean update(Task updateTask){
        if (updateTask == null) {
            return false; // или выбросить IllegalArgumentException
        }
        Optional<Task> optional = findById(updateTask.getId());
        if (optional.isPresent()){
            Task existing = optional.get();
            existing.setDescription(updateTask.getDescription());
            existing.setPriority(updateTask.getPriority());
            //existing.setDone(updateTask.isDone());
            return true;
        }
        return false;

    }
    //DONE
    public boolean updateDone(long id, boolean done){
        Optional<Task> optional = findById(id);
        if(optional.isPresent()){
            optional.get().setDone(done);
            return true;
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

    //SEARCH
    public List<Task> findAll(){ // аккуратно возвращаем список, не нарушая инкапсуляции
        return Collections.unmodifiableList(listTask);
    }
    public Optional<Task> findById(long id) {
        return listTask.stream().filter(t -> t.getId() == id).findFirst();
    }




}
