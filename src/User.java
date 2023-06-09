/*
this class is the backend for the user class and provides getters and setters for the user class
 */


public class User {

    // create a user class to store the user's info
    private String password;
    private String username;
    private String firstName;
    private String lastName;

    public String getPassword() {
        return password;
    }

    // getters and setters for user class
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // constructor for user class
    public User(String password, String username, String firstName, String lastName) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//