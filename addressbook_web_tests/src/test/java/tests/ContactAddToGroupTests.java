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
        if ( app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData("", "firstname", "lastname", "address", "", "", "", "", "", ""));
        }
        var groupList = app.hbm().getGroupList();

        ContactData contactForAddToGroup = null;
        GroupData groupData = groupList.get(0);
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var contactListNotInGroup = app.hbm().getContactsNotInGroup();
        if  ((contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty())) {
            contactForAddToGroup = contactListNotInGroup.get(0);
            app.contact().addContactInToGroup(contactForAddToGroup, groupData);
        }
        if (contactForAddToGroup == null) {
            app.contact().createContact(
                    new ContactData("", "firstname", "lastname", "address", "", "", "", "", "", ""),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForAddToGroup = contacts.get(contacts.size() - 1);
        }
        var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var newContactListInGroup = new ArrayList<>(oldContactListInGroup);
        newContactListInGroup.add(contactForAddToGroup);
        Assertions.assertEquals(Set.copyOf(expectedContactListInGroup), Set.copyOf(newContactListInGroup));
    }
}
