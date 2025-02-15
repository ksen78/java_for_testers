package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void confirmRegistration(String username, String password) {
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        manager.driver().findElement(By.xpath("//button[@type='submit']")).click();
    }
}
