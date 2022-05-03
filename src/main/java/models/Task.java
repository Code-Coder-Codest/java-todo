package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String description;
    private String name;
    private boolean completes;
    private LocalDateTime createdAt;
    private int id;
    private int categoryId;

    public Task(String name, String description, int categoryId){
        this.description = description;
        this.name = name;
        this.completes = false;
        this.createdAt = LocalDateTime.now();
        this.categoryId = categoryId;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getcompletes() == task.getcompletes() && getId() == task.getId() && Objects.equals(getDescription(), task.getDescription());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getName(), getcompletes(), getId());
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCompletes(boolean completes) {
        this.completes = completes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public boolean getcompletes(){

        return this.completes;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public int getId() {
        return id;
    }
}



/*private static ArrayList<Task> instances = new ArrayList<>();
instances.add(this);
this.id = instances.size();
public static ArrayList<Task> getAll(){
        return instances;
    }
 public void deleteTask(){
        instances.remove(id-1);
    }
public static Task findById(int id){
        return instances.get(id-1);
    }
public static void clearAllTasks(){

        instances.clear();
    }
    public void update(String content) {
        this.description = content;
    }
* */
