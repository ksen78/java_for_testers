package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void createContact(ContactData contact, GroupData group) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        returnToContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        returnToContactPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        updateContactModification();
        click(By.linkText("home page"));
    }

    public void addContactInToGroup(ContactData contactForAddToGroup, GroupData groupData) {
        returnToContactPage();
        selectContact(contactForAddToGroup);
        selectToGroup(groupData);
        addToContact();
    }

    private void addToContact() {
        manager.driver.findElement(By.name("add")).click();
    }

    private void selectToGroup(GroupData groupData) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(groupData.id());
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
        manager.driver.findElement(By.linkText("home")).click();
    }

    private void updateContactModification() { click(By.xpath("//input[@name='update']"));    }

    private void fillContactForm(ContactData contact) {
        type(By.name("lastname"), contact.lastname());
        type(By.name("firstname"), contact.firstname());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"), contact.photo());
    }

    private void initContactModification(ContactData contact) {
        var edit = manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
        edit.click();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    public void selectGroupById(GroupData groupData) {
        returnToContactPage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(groupData.id());
    }

    public void removeContactFromGroup(ContactData contactForDelete, GroupData groupData) {
        returnToContactPage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(groupData.id());
        selectContact(contactForDelete);
        manager.driver.findElement(By.name("remove")).click();
    }

    public int getCount() {
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

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
    return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmail() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var email = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, email);
        }
        return result;
    }



}
