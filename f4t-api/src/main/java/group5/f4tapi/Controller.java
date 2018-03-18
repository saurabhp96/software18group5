package group5.f4tapi;

import group5.f4tapi.entity.Customer;
import group5.f4tapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private CustomerRepository customerRepository;

    @Autowired
    public Controller(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void addUser(@RequestBody Customer.AddRequest addRequest) {
        Customer customer = new Customer(addRequest.firstName, addRequest.lastName);
        customerRepository.save(customer);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
