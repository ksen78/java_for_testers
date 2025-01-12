package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                    result.add(new ContactData()
                            .withFirstname(firstname)
                            .withLastname(lastname)
                            .withAddress(address));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        var oldContact = app.contact().getList();
        app.contact().createContact(contact);
        var newContact = app.contact().getList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);

        var expectedList = new ArrayList<>(oldContact);
        expectedList.add(contact.withId(newContact.get(newContact.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }

}
