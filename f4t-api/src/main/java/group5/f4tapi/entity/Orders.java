package group5.f4tapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    private long orderID;

    @ManyToMany
    private Set<MenuItem> menuItems;

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }
}
