import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    @ParameterizedTest
    @ValueSource(strings = {"Jacques1!", "Jacques1", "Jacques!", "Jacques"})
    void checkUsernameComplexityFalse(String username) {
        Login login = new Login();
        assertFalse(login.checkUsernameComplexity(username));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Jac_d", "_jac", "Jac_", "Jac_1"})
    void checkUsernameComplexityTrue(String username) {
        Login login = new Login();
        assertTrue(login.checkUsernameComplexity(username));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Jacques", "Jac@1", "JAcques", "123jacques"})
    void checkPasswordComplexityFalse(String password) {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity(password));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Jacques@8", "Jac@1dup", "JAcques%33", "123Jacques*"})
    void checkPasswordComplexityTrue(String password) {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity(password));
    }
}

