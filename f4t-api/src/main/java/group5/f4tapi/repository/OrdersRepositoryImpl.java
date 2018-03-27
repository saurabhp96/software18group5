package group5.f4tapi.repository;

import group5.f4tapi.entity.MenuItem;
import group5.f4tapi.entity.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class OrdersRepositoryImpl implements OrdersRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public boolean addItemToOrder(long orderID, String itemName) {
        MenuItem item = em.find(MenuItem.class, itemName);
        Orders orders = em.find(Orders.class, orderID);
        if (orders.addItem(item)) {
            em.persist(orders);
            em.persist(item);
            return true;
        }
        return false;
    }
}
