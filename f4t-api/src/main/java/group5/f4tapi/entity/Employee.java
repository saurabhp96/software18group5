package group5.f4tapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empID;
    private String role;
    private Double salary;
    private String firstName;
    private String lastName;
    private String password;

    public static class AddRequest {
        public String firstName, lastName, role, password;
        public Double salary;
    }

    public long getEmpID() {
        return empID;
    }

    public void setEmpID(long empID) {
        this.empID = empID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Employee)) return false;

        Employee emp = (Employee) o;
        return getEmpID() == emp.getEmpID() && getFirstName().equals(emp.getFirstName()) && getLastName().equals(emp.getLastName()) && getRole().equals(emp.getRole()) && getSalary().equals(emp.getSalary());
    }
}
