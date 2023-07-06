/*
this class is used to create a task object and store the task details.
iit makes use of the WorkerClass.TaskStatus enum to store the task status
this class also has a printTaskDetails method to print the task details
 */

public class Task {
    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerFirstName;
    private final String developerLastName;
    private final int taskDuration;
    private final String taskID;
    private WorkerClass.TaskStatus taskStatus;

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
        return "Task Status: " + taskStatus + "\nDeveloper Details: " + developerFirstName + " " + developerLastName +
                "\nTask Number: " + taskNumber + "\nTask Name: " + taskName +
                "\nTask Description: " + taskDescription + "\nTask ID: " + taskID +
                "\nTask Duration: " + taskDuration + " hours";
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskStatus(WorkerClass.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public WorkerClass.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeveloperFirstName() {
        return developerFirstName;
    }

    public String getDeveloperLastName() {
        return developerLastName;
    }

    public String getTaskID() {
        return taskID;
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//
