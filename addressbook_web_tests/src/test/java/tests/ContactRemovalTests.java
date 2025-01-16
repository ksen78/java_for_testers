package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact(){
    if (app.hbm().getContactCount() == 0) {
        app.hbm().createContact(new ContactData("", "contact firstname", "contact lastname", "contact address", ""));
    }
        var oldContact = app.hbm().getContactList();
        //System.out.println(oldContact);
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        app.contact().removeContact(oldContact.get(index));
        var newContact = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.remove(index);
        Assertions.assertEquals(newContact, expectedList);
    }
}
