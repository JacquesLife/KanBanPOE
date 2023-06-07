import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class Backend {
    private final ArrayList<ArrayList<String>> users;

    public Backend() {
        users = new ArrayList<>();
    }

    public void loadUsers() {
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

    public boolean isUserValid(String username, String password) {
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username) && user.get(1).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getUser(String username) {
        for (ArrayList<String> user : users) {
            if (user.get(0).equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void registerUser(String username, String password, String firstName, String lastName) {
        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add(username);
        userInfo.add(password);
        userInfo.add(firstName);
        userInfo.add(lastName);
        users.add(userInfo);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("userInfo.txt", true));
            bw.write(String.join(",", userInfo));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

