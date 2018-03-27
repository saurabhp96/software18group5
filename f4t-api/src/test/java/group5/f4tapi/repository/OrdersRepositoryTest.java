package group5.f4tapi.repository;

import group5.f4tapi.entity.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void addItemToOrder() {
        long initialCount = menuItemRepository.countByOrders_OrderID(1);
        boolean added = ordersRepository.addItemToOrder(1,"Samosa");
        assertThat(added).isTrue();
        MenuItem samosa = new MenuItem();
        samosa.setName("Samosa");
        List<MenuItem> items = menuItemRepository.findByOrders_OrderID(1);
        assertThat(items).contains(samosa);
        assertThat(items.size()).isEqualTo(initialCount+1);
    }
}