package manager.hbm;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "address")
    public String address;

    public String home;

    public String mobile;

    public String work;

    public String phone2;

    public String email;

    @ManyToMany(mappedBy = "contacts")
    public List<GroupRecord> groups;

    public ContactRecord(){
    }

    public ContactRecord(int id, String firstname, String lastname, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        }
    }
