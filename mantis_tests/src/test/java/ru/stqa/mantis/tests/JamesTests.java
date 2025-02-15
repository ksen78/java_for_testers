package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class JamesTests extends TestBase{

    @Test
    void canCreateUser() {
        app.jamesCli().AddUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }

    @Test
    void createUser() {
        app.james().createUser("blabla", "blabla@localhost");
    }
}
