package group5.f4tapi.repository;

import group5.f4tapi.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,String> {
    List<MenuItem> findByOrders_OrderID(long id);
    long countByOrders_OrderID(long id);
}
