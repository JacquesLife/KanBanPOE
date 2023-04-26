public class Login {

    public boolean checkPasswordComplexity(String password) {
        // Regex for password complexity
        String capitalRegex = ".*[A-Z].*";
        String numberRegex = ".*\\d.*";
        String specialCharRegex = ".*[!@#$%^&*()].*";

        boolean hasCapital = password.matches(capitalRegex);
        boolean hasNumber = password.matches(numberRegex);
        boolean hasSpecialChar = password.matches(specialCharRegex);
        boolean hasMinLength = password.length() >= 8; // Min length of 8

        return hasCapital && hasNumber && hasSpecialChar && hasMinLength; // Return true if all conditions are met
    }
    public boolean checkUsernameComplexity(String username) {
        // Regex for username complexity
        String underscoreRegex = ".*_.*";

        boolean hasUnderscore = username.matches(underscoreRegex);
        boolean hasMaxLength = username.length() <= 5; // Max length of 5

        return hasUnderscore && hasMaxLength; // Return true if all conditions are met
    }
}