package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Order {
    @Id
    private long orderID;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
