package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class JamesHelper extends HelperBase {

    public JamesHelper(ApplicationManager manager) {
        super(manager);
    }

    private void submit () {
        manager.driver().findElement(By.xpath("//input[@value='Signup']")).click();
    }

    public void createUser (String username, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.id("username"),username);
        type(By.id("email-field"), email);
        submit();
    }
}