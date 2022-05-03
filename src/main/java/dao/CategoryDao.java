package dao;

import models.Category;
import models.Task;
import java.util.List;

public interface CategoryDao {
    //LIST
    List<Category> getAllCategories();

    //CREATE
    void addCategory (Category category);

    //READ
    Category findById(int id);
    List<Task> getAllTasksByCategory(int categoryId);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllCategories();

}