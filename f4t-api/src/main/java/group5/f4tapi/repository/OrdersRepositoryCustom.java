package group5.f4tapi.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepositoryCustom {
    boolean addItemToOrder(long orderID, String itemName);
}
