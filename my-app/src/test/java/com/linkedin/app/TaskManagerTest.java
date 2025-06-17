import org.junit.jupiter.api.Test;

public class TaskManagerTest {
    @Test
    public void addTask(){
        TaskManager taskManager = new TaskManager();
        Task task = new Task();
        taskManager.addTask(task);
        assertTrue(taskManager.exists(task.getId()));
    }
}