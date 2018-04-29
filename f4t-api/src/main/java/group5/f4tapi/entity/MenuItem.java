package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MenuItem {
    @Id
    private String name;
    private double Price;
    private int Calories;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "CustItems",
            inverseJoinColumns=@JoinColumn(name="CustID", referencedColumnName="CustID"),
            joinColumns=@JoinColumn(name="MenuItem", referencedColumnName="Name")
    )
    private Set<Customer> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof MenuItem)) return false;

        MenuItem mi = (MenuItem) o;
        return getName().equals(mi.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
