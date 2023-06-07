public class Task {
    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerFirstName;
    private final String developerLastName;
    private final int taskDuration;
    private final String taskID;

    public Task(String taskName, int taskNumber, String taskDescription, String developerFirstName, String developerLastName, int taskDuration, String taskID) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskDuration = taskDuration;
        this.taskID = taskID;
    }

    public String printTaskDetails() {
        return "Task Status: \nDeveloper Details: " + developerFirstName + " " + developerLastName +
                "\nTask Number: " + taskNumber + "\nTask Name: " + taskName +
                "\nTask Description: " + taskDescription + "\nTask ID: " + taskID +
                "\nTask Duration: " + taskDuration + " hours";
    }
}
