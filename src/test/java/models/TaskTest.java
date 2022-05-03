package models;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Task task = setupNewTask();
        assertEquals(true, task instanceof Task);
    }

    @Test
    public void TaskInstantiatesWithDescription_true() throws Exception {
        Task task = setupNewTask();
        assertEquals("Mow the lawn", task.getDescription());
    }

    @Test
    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
        Task task = setupNewTask();
        assertEquals(false, task.getcompletes()); //should never start as completed
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
        Task task = setupNewTask();
        assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    }

    //helper methods
    public Task setupNewTask(){
        return new Task("environment", "Mow the lawn",1);
    }
}




/*@Before
    public void setUp() throws Exception {
    } remove

    @After
    public void tearDown() throws Exception {
        Task.clearAllTasks();
    } remove

    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Task task = setupNewTask();
        assertEquals(true, task instanceof Task);
    } keep

    @Test
    public void TaskInstantiatesWithDescription_true() throws Exception {
        Task task = setupNewTask();
        assertEquals("Mow the lawn", task.getDescription());
    } keep

    @Test
    public void AllTasksAreCorrectlyReturned_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, Task.getAll().size());
    }

    @Test
    public void AllTasksContainsAllTasks_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertTrue(Task.getAll().contains(task));
        assertTrue(Task.getAll().contains(otherTask));
    }

    @Test
    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
        Task task = setupNewTask();
        assertEquals(false, task.getCompleted()); //should never start as completed
    } keep

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Task task = setupNewTask();
        assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    } keep

    @Test
    public void tasksInstantiateWithId() throws Exception {
        Task task = setupNewTask();
        assertEquals(1, task.getId());
    } remove

    @Test
    public void findReturnsCorrectTask() throws Exception {
        Task task = setupNewTask();
        assertEquals(1, Task.findById(task.getId()).getId());
    } remove


    @Test
    public void findReturnsCorrectTaskWhenMoreThanOneTaskExists() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        assertEquals(2, Task.findById(otherTask.getId()).getId());
    } remove

    @Test
    public void updateChangesTaskContent() throws Exception {
        Task task = setupNewTask();
        String formerContent = task.getDescription();
        LocalDateTime formerDate = task.getCreatedAt();
        int formerId = task.getId();

        task.update("Floss the cat");

        assertEquals(formerId, task.getId());
        assertEquals(formerDate, task.getCreatedAt());
        assertNotEquals(formerContent, task.getDescription());
    } remove

    @Test
    public void deleteDeletesASpecificTask() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat");
        task.deleteTask();
        assertEquals(1, Task.getAll().size()); //one is left
        assertEquals(Task.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2
    } remove

    @Test
    public void deleteAllTasksDeletesAllTasks() throws Exception {
        Task task = setupNewTask();
        Task otherTask = setupNewTask();
        Task.clearAllTasks();
        assertEquals(0, Task.getAll().size());
    } remove


    //helper methods
    public Task setupNewTask(){

        return new Task("Mow the lawn");
        keep
    }*/