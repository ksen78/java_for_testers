package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

public class ContactRemovalFromGroupTests extends TestBase{

    @Test
    void canContactRemoveFromGroup() {
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var groupList = app.hbm().getGroupList();
        ContactData contactForAddToGroup;
        GroupData groupData = groupList.get(0);
        var ContactListInGroup = app.hbm().getContactsInGroup(groupData);

        if (ContactListInGroup.isEmpty()) {
            var contactListNotInGroup = app.hbm().getContactsNotInGroup();
             if (contactListNotInGroup.isEmpty()) {
                 app.contact().createContact(new ContactData("", "contact firstname", "contact lastname", "", "", "", "", "", "", ""));
             }
            contactListNotInGroup = app.hbm().getContactsNotInGroup();
            contactForAddToGroup = contactListNotInGroup.get(0);
            app.contact().addContactInToGroup(contactForAddToGroup, groupData);
        }

        ContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var contactForDelete = ContactListInGroup.get(0);

        app.contact().removeContactFromGroup(contactForDelete, groupData);

        var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var newContactListInGroup = new ArrayList<>(ContactListInGroup);

        newContactListInGroup.remove(contactForDelete);

        Assertions.assertEquals(Set.copyOf(expectedContactListInGroup), Set.copyOf(newContactListInGroup));
    }

}
