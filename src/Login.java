public class Login {

    public boolean checkPasswordComplexity(String password) {
        String capitalRegex = ".*[A-Z].*";
        String numberRegex = ".*\\d.*";
        String specialCharRegex = ".*[!@#$%^&*()].*";

        boolean hasCapital = password.matches(capitalRegex);
        boolean hasNumber = password.matches(numberRegex);
        boolean hasSpecialChar = password.matches(specialCharRegex);
        boolean hasMinLength = password.length() >= 8;

        return hasCapital && hasNumber && hasSpecialChar && hasMinLength;
    }
    public boolean checkUsernameComplexity(String username) {
        String underscoreRegex = ".*_.*";

        boolean hasUnderscore = username.matches(underscoreRegex);
        boolean hasMaxLength = username.length() <= 5;

        return hasUnderscore && hasMaxLength;
    }
}