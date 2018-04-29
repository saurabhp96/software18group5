package group5.f4tapi.repository;

import group5.f4tapi.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,String> {
    List<MenuItem> findByOrders_CustID(long id);
    long countByOrders_CustID(long id);

    @Transactional
    @Modifying
    @Query(value= "INSERT INTO CustItems VALUES (:custID, :menuItem)", nativeQuery = true)
    int addItemToOrder(@Param("custID") long custID, @Param("menuItem") String menuItem);
}
