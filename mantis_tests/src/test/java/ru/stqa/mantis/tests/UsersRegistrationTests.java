package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UsersRegistrationTests extends TestBase {

    DeveloperMailUser user;

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

    @Test
    void canRegisterUserApi() {
        var username = CommonFunctions.randomString(8);
        var password = "password";
        var email = String.format("%s@localhost", username);

        app.jamesApi().AddUser(email, password);

        app.rest().createUser(email, username);
//        app.mail().receive(email, password, Duration.ofSeconds(60));

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





//        var password = "password";
//        user = app.developerMail().addUser();
//        var email = String.format("%s@developermail.com", user.name());
//
//
//       app.james().createUser(user.name(), email);
//       app.mail().receive(email, password, Duration.ofSeconds(60));
//
//       var message = app.developerMail().receive(user, Duration.ofSeconds(60));
//
//       var text = message;
//       var pattern = Pattern.compile("http://\\S*");
//       var matcher = pattern.matcher(text);
//
//       Assertions.assertTrue(matcher.find());
//       var url = text.substring(matcher.start(), matcher.end());
//
//       app.driver().get(url);
//       app.session().confirmRegistration(user.name(), password);
//
//       app.http().login(user.name(), password);
//
//       Assertions.assertTrue(app.http().isLoggedIn());
//    }
//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }


