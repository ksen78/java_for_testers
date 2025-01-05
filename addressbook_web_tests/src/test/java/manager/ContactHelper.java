package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HalperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("add new"))) {
            click(By.linkText("add new"));
        }
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void removeContact() {
        returnToContactPage();
        selectContact();
        removeSelectedContact();
        returnToContactPage();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.name("home"));
    }

    private void removeSelectedContact() {
        manager.driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    private void returnToContactPage() {
        click(By.linkText("home"));
    }


    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }

}
