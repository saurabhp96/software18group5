package group5.f4tapi.entity;

import javax.persistence.*;

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
