/*
this class is used to check the complexity of the username and password
it makes use of regex to check if the username and password meet the requirements
 */


public class Login {

    public boolean checkPasswordComplexity(String password) {
        // Regex for password complexity
        String capitalRegex = ".*[A-Z].*"; // Must contain a capital letter
        String numberRegex = ".*\\d.*"; // Must contain a number
        String specialCharRegex = ".*[!@#$%^&*()].*"; // Must contain a special character

        boolean hasCapital = password.matches(capitalRegex);
        boolean hasNumber = password.matches(numberRegex);
        boolean hasSpecialChar = password.matches(specialCharRegex);
        boolean hasMinLength = password.length() >= 8; // Min length of 8

        return hasCapital && hasNumber && hasSpecialChar && hasMinLength; // Return true if all conditions are met
    }
    public boolean checkUsernameComplexity(String username) {
        // Regex for username complexity
        String underscoreRegex = ".*_.*"; // Must contain an underscore

        boolean hasUnderscore = username.matches(underscoreRegex);
        boolean hasMaxLength = username.length() <= 5; // Max length of 5

        return hasUnderscore && hasMaxLength; // Return true if all conditions are met
    }
}

//--------------------------------------------▄︻̷̿┻̿═━一-End of File-一━═┻̿┷̿︻▄-----------------------------------------------//