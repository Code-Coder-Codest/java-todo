
import java.util.HashMap;
import java.util.Map;

import dao.Sql2oCategoryDao;
import dao.Sql2oTaskDao;
import models.Category;
import models.Task;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 1020; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {//type “psvm + tab” to autocreate this

        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        port(1020);

        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        /*String connectionString = "jdbc:postgres://sozweozelznzva:e871d85c3e87dccba94c2f1509f5147f0abc749f1b0381135402d1a41aaed572@ec2-34-194-73-236.compute-1.amazonaws.com:5432/d4r5nas3jat6cc";*/
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oCategoryDao categoryDao = new Sql2oCategoryDao(sql2o);
        Sql2oTaskDao taskDao = new Sql2oTaskDao(sql2o);

        Map<String, Object> model = new HashMap<>();

        //get: show all tasks in all categories and show all categories
        get("/", (req, res) -> { //1
            model.put("categories", categoryDao.getAllCategories());
            model.put("tasks", taskDao.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new category
        get("/categories/new", (req, res) -> { //working 2
            return new ModelAndView(model, "category-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new category
        post("/categories", (req, res) -> { //new 3
            categoryDao.addCategory(new Category(req.queryParams("name")));
            model.put("categories", categoryDao.getAllCategories());
            model.put("tasks", taskDao.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/tasks/new", (req, res) -> { //working 4
            model.put("categories", categoryDao.getAllCategories());
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/tasks", (req, res) -> { //URL to make new task on POST route 5
            model.put("categories", categoryDao.getAllCategories());
            model.put("tasks", taskDao.getAll());
            taskDao.add(new Task(req.queryParams("name"), req.queryParams("description"), Integer.parseInt(req.queryParams("categoryId"))));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: delete all categories and all tasks
        get("/categories/delete", (req, res) -> { //6
            categoryDao.clearAllCategories();
            taskDao.clearAllTasks();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all tasks
        get("/tasks/delete", (req, res) -> { //*****
            taskDao.clearAllTasks();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get a specific category (and the tasks it contains)
        get("/categories/:id", (req, res) -> { //7
            model.put("category", categoryDao.findById(Integer.parseInt(req.params("id"))));
            model.put("tasks", categoryDao.getAllTasksByCategory(Integer.parseInt(req.params("id"))));
            model.put("categories", categoryDao.getAllCategories()); //refresh list of links for navbar
            return new ModelAndView(model, "category-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a category
        get("/categories/:id/edit", (req, res) -> { //8
            model.put("editCategory", true);
            model.put("category", categoryDao.findById(Integer.parseInt(req.params("id"))));
            model.put("categories", categoryDao.getAllCategories()); //refresh list of links for navbar
            return new ModelAndView(model, "category-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a category
        post("/categories/:id", (req, res) -> { //9
            categoryDao.update(Integer.parseInt(req.params(":id")), req.queryParams("newCategoryName"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/categories/:category_id/tasks/:task_id/delete", (req, res) -> { //10
            taskDao.deleteById(Integer.parseInt(req.params("task_id")));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new task form

        //get: show an individual task that is nested in a category
        get("/categories/:category_id/tasks/:task_id", (req, res) -> { //11
            model.put("category", categoryDao.findById(Integer.parseInt(req.params("category_id"))));
            model.put("task", taskDao.findById(Integer.parseInt(req.params("task_id")))); //add it to model for template to display
            model.put("categories", categoryDao.getAllCategories()); //refresh list of links for navbar
            return new ModelAndView(model, "task-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/tasks/:id/edit", (req, res) -> { //12
            model.put("categories", categoryDao.getAllCategories());
            model.put("task", taskDao.findById(Integer.parseInt(req.params("id"))));
            model.put("editTask", true);
            return new ModelAndView(model, "task-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/tasks/:id", (req, res) -> { //URL to update task on POST route 13
            taskDao.update(Integer.parseInt(req.params(":id")), req.queryParams("name"), req.queryParams("description"), Integer.parseInt(req.queryParams("categoryId")));  // remember the hardcoded categoryId we placed? See what we've done to/with it?
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}