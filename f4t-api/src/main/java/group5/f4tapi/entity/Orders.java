package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    private long orderID;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "OrderItems",
            joinColumns=@JoinColumn(name="OrderID", referencedColumnName="OrderID"),
            inverseJoinColumns=@JoinColumn(name="Item", referencedColumnName="Name")
    )
    private Set<MenuItem> menuItems;

    public boolean addItem(MenuItem item){
        return menuItems.add(item);
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }
}
