package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MenuItem {
    @Id
    private String ItemName;
    private double Price;
    private int Calories;

    public static class ForChef {
        private String ItemName;
        private long CustID;
        private int Calories;

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String itemName) {
            ItemName = itemName;
        }

        public long getCustID() {
            return CustID;
        }

        public void setCustID(long custID) {
            CustID = custID;
        }

        public int getCalories() {
            return Calories;
        }

        public void setCalories(int calories) {
            Calories = calories;
        }
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "CustItems",
            inverseJoinColumns=@JoinColumn(name="CustID", referencedColumnName="CustID"),
            joinColumns=@JoinColumn(name="MenuItem", referencedColumnName="ItemName")
    )
    private Set<Customer> orders;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
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
        return getItemName().equals(mi.getItemName());
    }

    @Override
    public String toString() {
        return getItemName();
    }
}
