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
        ContactData contactForDelete = null;
        GroupData groupData = null;
        for (int i = 0; i < groupList.size() - 1; i++) {
            var contactListInGroup = app.hbm().getContactsInGroup(groupList.get(i));
            if  ( (contactListInGroup != null) && (!contactListInGroup.isEmpty()) ) {
                contactForDelete = contactListInGroup.get(0);
                groupData = groupList.get(i);
                break;
            }
        }
        if (contactForDelete == null) {
            var contactListNotInGroup = app.hbm().getContactsNotInGroup();
            if  ((contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty())) {
                contactForDelete = contactListNotInGroup.get(0);
                groupData = groupList.get(0);
                app.contact().addContactInToGroup(contactForDelete, groupData);
            }
        }
        if (contactForDelete == null) {
            groupData = groupList.get(0);
            app.contact().createContact(
                    new ContactData("", "firstname", "lastname", "address", ""),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForDelete = contacts.get(0);
        }
        var oldContacts = app.hbm().getContactsInGroup(groupData);
        app.contact().selectGroupById(groupData);
        app.contact().removeContactFromGroup(contactForDelete);
        var newContacts = app.hbm().getContactsInGroup(groupData);
        var expectedList = new ArrayList<>(oldContacts);
        ContactData finalContactForDelete = contactForDelete;
        expectedList.removeIf(contactData -> finalContactForDelete.id().equals(contactData.id()));
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newContacts));
    }

}
