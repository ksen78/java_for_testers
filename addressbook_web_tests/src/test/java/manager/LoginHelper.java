package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HalperBase {

     public LoginHelper(ApplicationManager manager) {
         super(manager);
    }

    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
       click(By.xpath("//input[@value=\'Login\']")) ;
    }
}
