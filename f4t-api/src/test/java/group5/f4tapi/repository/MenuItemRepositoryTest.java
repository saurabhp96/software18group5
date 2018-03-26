package group5.f4tapi.repository;

import group5.f4tapi.entity.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository repository;

    @Test
    public void findByOrders_OrderID() {
        List<MenuItem> items = repository.findByOrders_OrderID(1);
        MenuItem banana = new MenuItem();
        banana.setName("Banana");
        assertThat(items).contains(banana);
    }
}