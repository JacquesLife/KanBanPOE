import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // create a login object
        Login login = new Login();

        // create an arraylist to store users
        ArrayList<ArrayList<String>> users = new ArrayList<>();

        try {
            // Use a buffered reader to read the file
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();

            // read the file line by line and add each line to the users arraylist
            while (line != null) {
                // split the line by commas
                String[] parts = line.split(",");
                // create an arraylist to store the user's info
                ArrayList<String> userInfo = new ArrayList<>(Arrays.asList(parts));
                users.add(userInfo);
                line = reader.readLine();
            }
            // close the buffered reader
            reader.close();

        } catch (IOException e) {
            // print the stack trace if an exception occurs
            e.printStackTrace();
        }
        // create an array of options for the user to choose from
        String[] options = {"Login", "Register"};
        // create a variable to store the user's choice
        int choice = JOptionPane.showOptionDialog(null, "Login or Register", "Login/Register", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // create a boolean to check if the user exists
        boolean userExists = false;
        // create a while loop to keep asking the user for their username and password until they enter a valid username and password
        while (!userExists) {
            String username = JOptionPane.showInputDialog("Enter username");

            // loop through the users arraylist and check if the username and password match
            while (!login.checkUsernameComplexity(username)) {
                JOptionPane.showMessageDialog(null, "Username must contain an underscore and be less than 5 characters");
                username = JOptionPane.showInputDialog("Enter username");
            }

            String password = JOptionPane.showInputDialog("Enter password");
            // check if the user wants to log in or register
            if (choice == 0) {

                // loop through the users arraylist and check if the username and password match
                for (ArrayList<String> user : users) {

                    // if the username and password match, set userExists to true and break out of the loop
                    if (user.get(0).equals(username) && user.get(1).equals(password)) {
                        userExists = true;
                        JOptionPane.showMessageDialog(null, "Welcome " + user.get(2) + " " + user.get(3));
                        break;
                    }
                }
                // if the username and password don't match, display an error message
                if (!userExists) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } else {
                // check if the username already exists
                while (!login.checkPasswordComplexity(password)) {
                    JOptionPane.showMessageDialog(null, "Password must contain a capital letter, a number, a special character, and be at least 8 characters");
                    password = JOptionPane.showInputDialog("Enter password");
                }

                String firstName = JOptionPane.showInputDialog("Enter first name");
                String lastName = JOptionPane.showInputDialog("Enter last name");

                // loop through the users arraylist and check if the username and password match
                ArrayList<String> userInfo = new ArrayList<>();
                userInfo.add(username);
                userInfo.add(password);
                userInfo.add(firstName);
                userInfo.add(lastName);

                users.add(userInfo);

                try {
                    // Use a buffered writer to write to the file
                    BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
                    writer.write(String.join(",", userInfo));
                    writer.newLine();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName);
                // set userExists to true and break out of the loop
                break;
            }
        }
    }
}
