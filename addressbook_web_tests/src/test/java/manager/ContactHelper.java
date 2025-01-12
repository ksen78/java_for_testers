package manager;

import model.ContactData;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HalperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("add"))) {
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void removeContact(ContactData contact) {
        returnToContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    public void modifyContact(ContactData contact){
        openContactPage();
        initContactModification(contact);
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContact() {
        manager.driver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    private void returnToContactPage() {
        click(By.linkText("home"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }


    private void fillContactForm(ContactData contact) {
        type(By.name("lastname"), contact.lastname());
        type(By.name("firstname"), contact.firstname());
        type(By.name("address"), contact.address());
        attach(By.name("photo"), contact.photo());
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    public int getCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var td = tr.findElements(By.tagName("td"));
            var id = String.valueOf(Integer.parseInt(td.get(0).findElement(By.tagName("input")).getAttribute("value")));
            var lastname = td.get(1).getText();
            var firstname = td.get(2).getText();
            var address = td.get(3).getText();
            contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address));
        }
        return contacts;
    }
}
