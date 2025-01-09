package model;

public record ContactData(String id, String firstname, String lastname, String address) {

    public ContactData() {
        this("", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address);
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address);
    }
    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address);
    }
}