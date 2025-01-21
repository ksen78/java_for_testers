package model;

public record ContactData(
        String id,
        String lastname,
        String firstname,
        String address,
        String photo,
        String home,
        String mobile,
        String work,
        String secondary,
        String email) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.lastname, this.firstname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary, this.email);
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, this.lastname, firstname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary, this.email);
    }
    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, lastname, this.firstname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastname, this.firstname, address, this.photo, this.home, this.mobile, this.work, this.secondary, this.email);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, photo, this.home, this.mobile, this.work, this.secondary, this.email);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, this.photo, home, this.mobile, this.work, this.secondary, this.email);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, this.photo, this.home, mobile, this.work, this.secondary, this.email);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, this.photo, this.home, this.mobile, work, this.secondary, this.email);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, this.photo, this.home, this.mobile, this.work, secondary, this.email);
    }
    public ContactData withMail(String email) {
        return new ContactData(this.id, this.lastname, this.firstname, this.address, this.photo, this.home, this.mobile, this.work, this.secondary, email);
    }
}