package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MenuItem {
    @Id
    private String name;
    private double Price;
    private int Calories;

    @ManyToMany
    @JoinTable(
            name = "OrderItems",
            inverseJoinColumns=@JoinColumn(name="OrderID", referencedColumnName="OrderID"),
            joinColumns=@JoinColumn(name="Item", referencedColumnName="Name")
    )
    private Set<Orders> orders;

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
}
