import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();

        ArrayList<ArrayList<String>> users = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                ArrayList<String> userInfo = new ArrayList<>();
                for (String part : parts) {
                    userInfo.add(part);
                }
                users.add(userInfo);
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        String[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(null,"Login or Register","Login/Register" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String username = JOptionPane.showInputDialog("Enter username");
        while (!login.checkUsernameComplexity(username)) {
            JOptionPane.showMessageDialog(null, "Username must contain an underscore and be less than 5 characters");
            username = JOptionPane.showInputDialog("Enter username");
        }
        String password = JOptionPane.showInputDialog("Enter password");
        if (choice == 0) {

            boolean userExists = false;
            for (ArrayList<String> user : users) {
                if (user.get(0).equals(username) && user.get(1).equals(password)) {
                    userExists = true;
                    JOptionPane.showMessageDialog(null, "Welcome " + user.get(2) + " " + user.get(3));
                    break;
                }
            }

            if (!userExists) {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        } else {
            while (!login.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null, "Password must contain a capital letter, a number, a special character, and be at least 8 characters");
                password = JOptionPane.showInputDialog("Enter password");
            }

            String firstName = JOptionPane.showInputDialog("Enter first name");
            String lastName = JOptionPane.showInputDialog("Enter last name");

            ArrayList<String> userInfo = new ArrayList<String>();
            userInfo.add(username);
            userInfo.add(password);
            userInfo.add(firstName);
            userInfo.add(lastName);

            users.add(userInfo);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt",true));
                writer.write(String.join(",",userInfo));
                writer.newLine();
                writer.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Welcome " + firstName + " " + lastName);
        }
    }
}
