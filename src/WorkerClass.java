/*
this class is responsible for performing the login or register operation, adding tasks, displaying tasks, calculating total hours, saving users, saving tasks, loading users and loading tasks.
it does this by creating a backend object and a login object and calling the methods from these classes.
ArrayList<Task> tasks is used to store the tasks that are created.
addTask() is used to add a task to the tasks' arraylist.
displayTasks() is used to display the tasks in the tasks' arraylist.
calculateTotalHours() is used to calculate the total hours of all the tasks in the tasks' arraylist.
saveUsers() is used to save the users to a file.
saveTasks() is used to save the tasks to a file.
loadUsers() is used to load the users from a file.
loadTasks() is used to load the tasks from a file.
performLoginOrRegister() is used to perform the login or register operation.
performOperations() is used to perform the add task, display tasks, calculate total hours, save users, save tasks, load users and load tasks operations.
*/
import javax.swing.*;
import java.util.ArrayList;

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
        // create a backend object and a login object
        backend = new Backend();
        login = new Login();
        tasks = new ArrayList<>();
    }

    public void performLoginOrRegister() {
        // load users from file
        backend.loadUsers();


        String[] options = {"Login", "Register"};
        // display a dialog box to ask the user if they want to log in or register
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
            // ask the user for their username and password
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
                    // if the user is logging in the password must match the complexity requirements
                    break;
                }

                JOptionPane.showMessageDialog(null, "Password must contain a capital letter, a number, a special character, and be at least 8 characters");
            }

            if (choice == 0) {
                // if the user is logging in check if the user exists
                if (backend.isUserValid(username, password)) {
                    userExists = true;
                    ArrayList<String> user = backend.getUser(username);
                    JOptionPane.showMessageDialog(null, "Welcome " + user.get(2) + " " + user.get(3));
                } else {
                    // if the user does not exist display an error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                // if the user is registering check if the user exists
                String firstName = JOptionPane.showInputDialog("Enter first name");
                String lastName = JOptionPane.showInputDialog("Enter last name");

                backend.registerUser(username, password, firstName, lastName);

                JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName);
                break;
            }
        }
    }

    public void addTasks() {
        // add tasks
        boolean quit = false;

        while (!quit) {
            // prompt for task name, description, developer name, duration, and status
            String taskName = "Login Feature";
            String taskDescription = TaskDescription.getTaskDescription();

            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name");
            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name");

            int taskDuration;
            // prompt for task duration
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

            // Prompt for task status
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
                // set the task status based on the user's choice
                case 1 -> task.setTaskStatus(TaskStatus.DOING);
                case 2 -> task.setTaskStatus(TaskStatus.DONE);
                default -> task.setTaskStatus(TaskStatus.TODO);
            }

            tasks.add(task);

            JOptionPane.showMessageDialog(null, "Task successfully captured\n\n" + task.printTaskDetails());

            int choice;
            // prompt the user to add more tasks or quit
            while (true) {
                try {
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report\n3) Calculate total hours\n4) Quit"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                // if the user chooses to quit or show the report set the quit flag to true to exit the loop
                case 1:
                    break;
                case 2:
                    showReport();
                    break;
                case 3:
                    int totalHours = calculateTotalHours();
                    JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                    quit = true; // Set the quit flag to true to exit the loop
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    quit = true; // Set the quit flag to true to exit the loop
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }


    public static class TaskDescription {
        // prompt for task description
        public static String getTaskDescription() {
            String taskDescription;
            while (true) {
                taskDescription = JOptionPane.showInputDialog("Enter task description");

                if (taskDescription.length() <= 50) {
                    // if the task description is less than 50 characters break out of the loop
                    break;
                }

                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
            return taskDescription;
        }
    }


    String createTaskID(String taskName, int taskNumber, String developerLastName) {
        // create task ID
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3).toUpperCase();
    }


    public int calculateTotalHours() {
        // calculate total hours
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }

    public void showReport() {
        // show report
        JOptionPane.showMessageDialog(null, "Coming Soon");

        performOperations();
    }

    public void performOperations() {
        // perform operations
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int choice;
        boolean quit = false;
        // Exit the loop immediately if quit is true
        do {
            while (true) {
                try {
                    // prompt the user to add tasks, show the report, calculate total hours, or quit
                    choice = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report\n3) Calculate total hours\n4) Quit"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }

            switch (choice) {
                // perform the operation based on the user's choice
                case 1 -> addTasks();
                case 2 -> showReport();
                case 3 -> {
                    int totalHours = calculateTotalHours();
                    JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                }
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    quit = true;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice");
            }

        } while (!quit);


        System.exit(0);
        // Exit the application
    }

    public static void main(String[] args) {
        WorkerClass worker = new WorkerClass();
        worker.performLoginOrRegister();
        worker.performOperations();
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//