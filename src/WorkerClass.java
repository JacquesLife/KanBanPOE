import javax.swing.*;
import java.util.ArrayList;

public class WorkerClass {
    private final Backend backend;
    private final Login login;
    private final ArrayList<Task> tasks;

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
            String username = JOptionPane.showInputDialog("Enter username");

            while (!login.checkUsernameComplexity(username)) {
                JOptionPane.showMessageDialog(null, "Username must contain an underscore and be less than 5 characters");
                username = JOptionPane.showInputDialog("Enter username");
            }

            String password = JOptionPane.showInputDialog("Enter password");

            if (choice == 0) {
                if (backend.isUserValid(username, password)) {
                    userExists = true;
                    ArrayList<String> user = backend.getUser(username);
                    JOptionPane.showMessageDialog(null, "Welcome " + user.get(2) + " " + user.get(3));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                while (!login.checkPasswordComplexity(password)) {
                    JOptionPane.showMessageDialog(null, "Password must contain a capital letter, a number, a special character, and be at least 8 characters");
                    password = JOptionPane.showInputDialog("Enter password");
                }

                String firstName = JOptionPane.showInputDialog("Enter first name");
                String lastName = JOptionPane.showInputDialog("Enter last name");

                backend.registerUser(username, password, firstName, lastName);

                JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName);
                break;
            }
        }
    }

    public void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you want to enter"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name");
            String taskDescription = JOptionPane.showInputDialog("Enter task description");
            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name");
            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours"));

            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                i--;
                continue;
            }

            String taskID = createTaskID(taskName, i, developerLastName);
            Task task = new Task(taskName, i, taskDescription, developerFirstName, developerLastName, taskDuration, taskID);
            tasks.add(task);

            JOptionPane.showMessageDialog(null, "Task successfully captured\n\n" + task.printTaskDetails());
        }
    }

    private String createTaskID(String taskName, int taskNumber, String developerLastName) {
        return taskName.substring(0, 2) + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3);
    }

    public void showReport() {
        JOptionPane.showMessageDialog(null, "Coming Soon");
    }

    public void performOperations() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int choice;
        do {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report\n3) Quit"));

            switch (choice) {
                case 1 -> addTasks();
                case 2 -> showReport();
                case 3 -> JOptionPane.showMessageDialog(null, "Goodbye!");
                default -> JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        WorkerClass worker = new WorkerClass();
        worker.performLoginOrRegister();
        worker.performOperations();
    }
}
