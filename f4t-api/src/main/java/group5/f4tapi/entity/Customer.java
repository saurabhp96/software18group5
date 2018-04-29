package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

    public static class AddRequest {
        public String firstName, lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custID;
    private String FirstName, LastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CustTable")
    private AllTables table;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "CustItems",
            joinColumns=@JoinColumn(name="CustID", referencedColumnName="CustID"),
            inverseJoinColumns=@JoinColumn(name="MenuItem", referencedColumnName="Name")
    )
    private Set<MenuItem> orderedItems;

    public long getCustID() {
        return custID;
    }

    public void setCustID(long custID) {
        this.custID = custID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
