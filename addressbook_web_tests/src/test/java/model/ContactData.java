package model;

public record ContactData(String firstname, String lastname, String address) {

    public ContactData() {
        this("", "", "");
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(firstname, this.lastname, this.address);
    }
}