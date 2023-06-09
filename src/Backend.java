/*
this class is responsible for loading users from file, checking if user exists, getting user info, and registering new users
it does this by using an arraylist of arraylists to store the user info
bufferedreader is used to read the file and string.split is used to split the line into an array of strings
bufferedwriter is used to write to the file
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
class Backend {
    private final ArrayList<ArrayList<String>> users;

    public Backend() {
        users = new ArrayList<>();
    }


    public void loadUsers() {
    // load users from file
        try {
            BufferedReader br = new BufferedReader(new FileReader("userInfo.txt"));
            String line = br.readLine();

            while (line != null) {
                String[] parts = line.split(",");
                ArrayList<String> userInfo = new ArrayList<>(Arrays.asList(parts));
                users.add(userInfo);
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // check if user exists
    public boolean isUserValid(String username, String password) {
        // check if user exists
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username) && user.get(1).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getUser(String username) {
        // return user info
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void registerUser(String username, String password, String firstName, String lastName) {
        // add user to users arraylist
        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add(username);
        userInfo.add(password);
        userInfo.add(firstName);
        userInfo.add(lastName);
        users.add(userInfo);

        try {
            // write user info to file
            BufferedWriter bw = new BufferedWriter(new FileWriter("userInfo.txt", true));
            bw.write(String.join(",", userInfo));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//