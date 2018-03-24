package group5.f4tapi;

import group5.f4tapi.entity.Customer;
import group5.f4tapi.entity.MenuItem;
import group5.f4tapi.entity.AllTables;
import group5.f4tapi.repository.CustomerRepository;
import group5.f4tapi.repository.MenuItemRepository;
import group5.f4tapi.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private CustomerRepository customerRepository;
    private MenuItemRepository menuItemRepository;
    private TableRepository tableRepository;

    @Autowired
    public Controller(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, TableRepository tableRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.tableRepository=tableRepository;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void addUser(@RequestBody Customer.AddRequest addRequest) {
        Customer customer = new Customer();
        customer.setFirstName(addRequest.firstName);
        customer.setLastName(addRequest.lastName);
        customerRepository.save(customer);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public List<MenuItem> findItemsInOrder(@PathVariable("id") long id) {
        return menuItemRepository.findByOrders_OrderID(id);
    }

    @RequestMapping(value="/tables",method=RequestMethod.GET)
    public List<AllTables> findAllTables(){
        return tableRepository.findAll();
    }






}
