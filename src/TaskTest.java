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
    }
