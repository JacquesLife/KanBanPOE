/*
this class is responsible for performing the login or register operation, adding tasks, displaying tasks, calculating total hours, saving users, saving tasks, loading users and loading tasks.
it does this by creating a backend object and a login object and calling the methods from these classes.
ArrayList<Task> tasks is used to store the tasks that are created.
addTask() is used to add a task to the tasks' arraylist.
displayTasks() is used to display the tasks in the tasks' arraylist.
calculateTotalHours() is used to calculate the total hours of all the tasks in the tasks' arraylist.
performLoginOrRegister() is used to perform the login or register operation.
performOperations() is used to perform the add task, display tasks, calculate total hours, save users, save tasks, load users and load tasks operations.
ChatGPT was used to help
 */
package poekanban;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author jacques
 */
public class WorkerClass {
    private final Backend backend;
    private final Login login;
    final ArrayList<Task> tasks;

    public enum TaskStatus {
        TODO,
        DOING,
        DONE
    }

    public WorkerClass() {
        backend = new Backend();
        login = new Login();
        tasks = new ArrayList<>();
    }

    public void performLoginOrRegister() {
        backend.loadUsers();

        String[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Login or Register",
                "Login/Register",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        boolean userExists = false;

        while (!userExists) {
            String username;
            while (true) {
                username = JOptionPane.showInputDialog("Enter username");

                if (login.checkUsernameComplexity(username)) {
                    break;
                }

                JOptionPane.showMessageDialog(null, "Username must contain an underscore and be less than 5 characters");
            }

            String password;
            while (true) {
                password = JOptionPane.showInputDialog("Enter password");

                if (choice == 0 || login.checkPasswordComplexity(password)) {
                    break;
                }

                JOptionPane.showMessageDialog(null, "Password must contain a capital letter, a number, a special character, and be at least 8 characters");
            }

            if (choice == 0) {
                if (backend.isUserValid(username, password)) {
                    userExists = true;
                    ArrayList<String> user = backend.getUser(username);
                    JOptionPane.showMessageDialog(null, "Welcome " + user.get(2) + " " + user.get(3));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                String firstName = JOptionPane.showInputDialog("Enter first name");
                String lastName = JOptionPane.showInputDialog("Enter last name");

                backend.registerUser(username, password, firstName, lastName);

                JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName);
                break;
            }
        }
    }

    public void addTasks() {
        boolean quit = false;

        while (!quit) {
            String taskName = "Login Feature";
            String taskDescription = TaskDescription.getTaskDescription();

            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name");
            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name");

            int taskDuration;
            while (true) {
                try {
                    taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            String taskID = createTaskID(taskName, tasks.size() + 1, developerLastName);
            Task task = new Task(taskName, tasks.size() + 1, taskDescription, developerFirstName, developerLastName, taskDuration, taskID);

            String[] statusOptions = {"ToDo", "Doing", "Done"};
            int statusChoice = JOptionPane.showOptionDialog(
                    null,
                    "Select the task status",
                    "Task Status",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    statusOptions,
                    statusOptions[0]
            );

            switch (statusChoice) {
                case 1 -> task.setTaskStatus(TaskStatus.DOING);
                case 2 -> task.setTaskStatus(TaskStatus.DONE);
                default -> task.setTaskStatus(TaskStatus.TODO);
            }

            tasks.add(task);

            JOptionPane.showMessageDialog(null, "Task successfully captured\n\n" + task.printTaskDetails());

            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report\n3) Calculate total hours\n4) Finished tasks\n5) Longest tasks\n6) Delete task\n7) Search task by name\n8) Quit"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                case 1:
                    break;
                case 2:
                    showReport();
                    break;
                case 3:
                    int totalHours = calculateTotalHours();
                    JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                    quit = true;
                    break;
                case 4:
                    finishedTasks();
                    break;
                case 5:
                    longestTasks();
                    break;
                case 6:
                    deleteTask();
                    break;
                case 7:
                    searchTaskByName();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    quit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }

    public static class TaskDescription {
        public static String getTaskDescription() {
            String taskDescription;
            while (true) {
                taskDescription = JOptionPane.showInputDialog("Enter task description");

                if (taskDescription.length() <= 50) {
                    break;
                }

                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
            return taskDescription;
        }
    }

    String createTaskID(String taskName, int taskNumber, String developerLastName) {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3).toUpperCase();
    }

    public int calculateTotalHours() {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }

    public void showReport() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        } else {
            StringBuilder report = new StringBuilder();
            report.append("Task Report:\n");
            for (Task task : tasks) {
                report.append("Task Name: ").append(task.getTaskName()).append("\n");
                report.append("Developer: ").append(task.getDeveloperFirstName()).append(" ").append(task.getDeveloperLastName()).append("\n");
                report.append("Task Duration: ").append(task.getTaskDuration()).append(" hours\n");
                report.append("Status: ").append(task.getTaskStatus()).append("\n");
                report.append("\n");
            }
            JOptionPane.showMessageDialog(null, report.toString());
        }

        performOperations();
    }

    public void finishedTasks() {
        StringBuilder finishedTasks = new StringBuilder("Finished Tasks:\n\n");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTaskStatus() == TaskStatus.DONE) {
                finishedTasks.append(task.printTaskDetails()).append("\n\n");
                found = true;
            }
        }

        if (!found) {
            finishedTasks.append("No finished tasks found.");
        }

        JOptionPane.showMessageDialog(null, finishedTasks.toString());

        performOperations();
    }

    public void longestTasks() {
        int maxDuration = 0;
        ArrayList<Task> longestTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getTaskDuration() > maxDuration) {
                maxDuration = task.getTaskDuration();
                longestTasks.clear();
                longestTasks.add(task);
            } else if (task.getTaskDuration() == maxDuration) {
                longestTasks.add(task);
            }
        }

        if (longestTasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        } else {
            StringBuilder longestTaskDetails = new StringBuilder("Longest Tasks:\n\n");
            for (Task task : longestTasks) {
                longestTaskDetails.append(task.printTaskDetails()).append("\n");
            }
            JOptionPane.showMessageDialog(null, longestTaskDetails.toString());
        }

        performOperations();
    }

    public void deleteTask() {
        String taskNameToDelete = JOptionPane.showInputDialog("Enter the task name to delete");

        boolean found = false;
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                taskToRemove = task;
                found = true;
                break;
            }
        }

        if (found) {
            tasks.remove(taskToRemove);
            JOptionPane.showMessageDialog(null, "Task deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Task not found");
        }

        performOperations();
    }

    public void searchTaskByName() {
    String searchTerm = JOptionPane.showInputDialog("Enter the task name or developer name to search");

    boolean found = false;
    StringBuilder searchResults = new StringBuilder("Search Results:\n\n");

    for (Task task : tasks) {
        if (task.getTaskName().equalsIgnoreCase(searchTerm) || task.getDeveloperFirstName().equalsIgnoreCase(searchTerm)) {
            searchResults.append(task.printTaskDetails()).append("\n");
            found = true;
        }
    }

    if (!found) {
        searchResults.append("No tasks found with the given name or developer.");
    }

    JOptionPane.showMessageDialog(null, searchResults.toString());

    performOperations();
}

    public void performOperations() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int choice;
        boolean quit = false;
        do {
            while (true) {
                try {
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report\n3) Calculate total hours\n4) Finished tasks\n5) Longest tasks\n6) Delete task\n7) Search task by name\n8) Quit"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                case 1 -> addTasks();
                case 2 -> showReport();
                case 3 -> {
                    int totalHours = calculateTotalHours();
                    JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                }
                case 4 -> finishedTasks();
                case 5 -> longestTasks();
                case 6 -> deleteTask();
                case 7 -> searchTaskByName();
                case 8 -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    quit = true;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice");
            }

        } while (!quit);

        System.exit(0);
    }

    public static void main(String[] args) {
        WorkerClass worker = new WorkerClass();
        worker.performLoginOrRegister();
        worker.performOperations();
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//
