package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData("contact firstname", "contact lastname", "contact address"));
    }

    @Test
    public void canCreateContactWithNameOnly() {
        app.contact().createContact(new ContactData().withFirstname("name"));
    }
}
