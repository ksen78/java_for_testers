package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

public class ContactAddToGroupTests extends TestBase{

    @Test
    void canAddContactInGroup() {
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("", "contact firstname", "contact lastname", "", "", "", "", "", "", ""));
        }

        var groupList = app.hbm().getGroupList();
        ContactData contactForAddToGroup;
        GroupData groupData = groupList.get(0);
        var contactListNotInGroup = app.hbm().getContactsNotInGroup();

        if (contactListNotInGroup.isEmpty()) {
            app.contact().createContact(new ContactData("", "contact firstname", "contact lastname", "", "", "", "", "", "", ""));
        }

        var oldContactListInGroup = app.hbm().getContactsInGroup(groupData);
        contactListNotInGroup = app.hbm().getContactsNotInGroup();

        contactForAddToGroup = contactListNotInGroup.get(0);
        app.contact().addContactInToGroup(contactForAddToGroup, groupData);

        var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var ContactListInGroup = new ArrayList<>(oldContactListInGroup);
        ContactListInGroup.add(contactForAddToGroup);
        Assertions.assertEquals(Set.copyOf(expectedContactListInGroup), Set.copyOf(ContactListInGroup));
    }
}
