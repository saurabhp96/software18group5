package group5.f4tapi;

import group5.f4tapi.entity.*;
import group5.f4tapi.repository.CustomerRepository;
import group5.f4tapi.repository.EmployeeRepository;
import group5.f4tapi.repository.MenuItemRepository;
import group5.f4tapi.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private CustomerRepository customerRepository;
    private MenuItemRepository menuItemRepository;
    private TableRepository tableRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public Controller(CustomerRepository customerRepository, MenuItemRepository menuItemRepository,
                      TableRepository tableRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.tableRepository=tableRepository;
        this.employeeRepository=employeeRepository;
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

    @RequestMapping(value="/employees",method=RequestMethod.GET)
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    @RequestMapping(value="/menu", method = RequestMethod.GET)
    public List<MenuItem> findAllMenuItems(){
        return menuItemRepository.findAll();
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public @ResponseBody Employee checkLogin(@RequestParam long empid, @RequestParam String password){

        Optional<Employee> searchResult=employeeRepository.findById(empid);
        if(searchResult.isPresent()){
            Employee employee=searchResult.get();
            return employee.getPassword().equals(password)?employee:null;
        }
        else{
            return null;
        }

    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employee.AddRequest addRequest){
        Employee hire=new Employee();
        hire.setFirstName(addRequest.firstName);
        hire.setLastName(addRequest.lastName);
        hire.setSalary(addRequest.salary);
        hire.setRole(addRequest.role);
        hire.setPassword(addRequest.password);
        employeeRepository.save(hire);
    }






}
