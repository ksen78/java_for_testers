package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UsersRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(8);
        var password = "password";
        var email = String.format("%s@localhost", username);

        app.jamesCli().AddUser(email, password);
        app.james().createUser(username, email);
        app.mail().receive(email, password, Duration.ofSeconds(60));

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);

        Assertions.assertTrue(matcher.find());
        var url = text.substring(matcher.start(), matcher.end());

        app.driver().get(url);
        app.session().confirmRegistration(username,password);

        app.http().login(username, password);

        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
