package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testContactInfoFields() {
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("", "contact firstname", "contact lastname", "Москва, ул. Горького, д.29", "", "12345", "123456", "47415", "44111", "pampararam@pam.com"));
        }
        var contacts = app.hbm().getContactList();
        var expectedPhone = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var expectedAddress = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var expectedEmail = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones();
        var address = app.contact().getAddress();
        var email = app.contact().getEmail();

        Assertions.assertEquals(expectedPhone, phones);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedEmail, email);
    }

}
