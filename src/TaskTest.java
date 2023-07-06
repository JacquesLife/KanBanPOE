/*
this is the test class for the Task class, and it tests the adding of tasks and the creation of task IDs as well
the search for tasks
 */



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @ParameterizedTest
    @CsvSource({
            "1, AD, YER, Test case 1",
            "0, CR, AST, Test case 2",
            "1, CR, DER, Test case 3",
            "2, CR, HOP, Test case 4",
            "3, CR, VOR, Test case 5"
    })
    void testCreateTaskID(int taskNumber, String taskName, String developerLastName) {
        WorkerClass worker = new WorkerClass();
        String taskID = worker.createTaskID(taskName, taskNumber, developerLastName);
        String expectedTaskID = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3).toUpperCase();
        Assertions.assertEquals(expectedTaskID, taskID);
    }

    @Test
    void testCalculateTotalHours() {
        WorkerClass worker = new WorkerClass();
        Task task1 = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn", "Harrison", 8, "LF:1:SON");
        Task task2 = new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike", "Smith", 10, "AT:2:ITH");
        worker.tasks.add(task1);
        worker.tasks.add(task2);
        int totalHours = worker.calculateTotalHours();
        assertEquals(18, totalHours);
    }

    @ParameterizedTest// Test to see if TaskDescription is less than 50 characters
    @ValueSource(strings = {"less than 50  characters"})
    void testTaskDescription(String taskDescription) {
        Assertions.assertTrue(taskDescription.length() <= 50);
    }

    @ParameterizedTest// Test to see if TaskDescription is more than 50 characters
    @ValueSource(strings = {"more than 50 characters more than 50 characters more than 50 characters more than 50 characters more than 50 characters"})
    void testTaskDescriptionFalse(String taskDescription) {
        Assertions.assertFalse(taskDescription.length() <= 50);
    }

    @Test
    public void testSearchTaskByName() {
        // Test data
        String taskNameToSearch = "Create Login";

        // Create WorkerClass instance and add a task for testing
        WorkerClass worker = new WorkerClass();
        Task task = new Task("Create Login", 1, "Task description", "John", "Doe", 8, "CL:1:DOE");
        worker.tasks.add(task);

        // Execute the method and capture the result
        boolean found = false;
        for (Task t : worker.tasks) {
            if (t.getTaskName().equalsIgnoreCase(taskNameToSearch)) {
                found = true;
                break;
            }
        }

        // Assert that the task was found
        assertTrue(found);
    }

    @Test
    public void testAddTasks() {
        // Create WorkerClass instance
        WorkerClass worker = new WorkerClass();

        // Set up test input
        String taskName = "Login Feature";
        String taskDescription = "Test task description";
        String developerFirstName = "John";
        String developerLastName = "Doe";
        int taskDuration = 5;
        String taskID = "LF:1:DOE";
        WorkerClass.TaskStatus taskStatus = WorkerClass.TaskStatus.TODO;

        // Add the task
        Task task = new Task(taskName, 1, taskDescription, developerFirstName, developerLastName, taskDuration, taskID);
        task.setTaskStatus(taskStatus);
        worker.tasks.add(task);

        assertEquals(1, worker.tasks.size());
        assertEquals(task, worker.tasks.get(0));
        assertEquals(developerFirstName, worker.tasks.get(0).getDeveloperFirstName());
        assertEquals(developerLastName, worker.tasks.get(0).getDeveloperLastName());
    }

}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//
