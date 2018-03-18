package group5.f4tapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    public static class AddRequest {
        public String firstName, lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CustId;

    private String FirstName, LastName;

    public Customer(){}

    public Customer(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public long getCustId() {
        return CustId;
    }

    public void setCustId(long custId) {
        CustId = custId;
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
