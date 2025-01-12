package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("", "firstname", "lastname", "address", "photo"));
        }
        var oldContact = app.contact().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var contact = oldContact.get(index);
        var contactId = contact.id();
        var contactFirstname = contact.firstname();
        var contactAddress = contact.address();
        var testData = new ContactData().withId(contactId).withLastname("modified name").withFirstname(contactFirstname).withAddress(contactAddress);
        app.contact().modifyContact(testData);
        var newContact = app.contact().getList();
        oldContact.set(index, testData.withId(contactId));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        oldContact.sort(compareById);
        Assertions.assertEquals(newContact, oldContact );
    }
}
