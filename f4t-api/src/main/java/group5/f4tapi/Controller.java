package group5.f4tapi;

import group5.f4tapi.entity.*;
import group5.f4tapi.repository.*;
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
    private RequestsRepository requestsRepository;

    @Autowired
    public Controller(CustomerRepository customerRepository, MenuItemRepository menuItemRepository,
                      TableRepository tableRepository, EmployeeRepository employeeRepository, RequestsRepository requestsRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.tableRepository=tableRepository;
        this.employeeRepository=employeeRepository;
        this.requestsRepository = requestsRepository;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void addUser(@RequestParam String firstName, @RequestParam String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customerRepository.save(customer);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.POST)
    public boolean addToOrder(@PathVariable("id") long id, @RequestParam String itemName) {
        return menuItemRepository.addItemToOrder(id, itemName);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public List<MenuItem> findItemsInOrder(@PathVariable("id") long id) {
        return menuItemRepository.findByOrders_CustID(id);
    }

    @RequestMapping(value="/seatcustomer", method = RequestMethod.GET)
    public int seatCustomer(@RequestParam long tableID, @RequestParam long custID){
        return tableRepository.seatCustomer(tableID, custID);
    }

    @RequestMapping(value="/unseatcustomer", method = RequestMethod.GET)
    public int unseatCustomer(@RequestParam long tableID){
        return tableRepository.unseatCustomer(tableID);
    }

    @RequestMapping(value="/tables",method=RequestMethod.GET)
    public List<AllTables> findAllTables(){
        return tableRepository.findAll();
    }

    @RequestMapping(value = "/findtables",method = RequestMethod.GET)
    public List<AllTables> findEmptyTables(@RequestParam int numPeople){
        return tableRepository.findByNumSeatsGreaterThanEqualAndCustomerIsNull(numPeople);
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

    @RequestMapping(value="/employees",method=RequestMethod.GET)
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public Employee hireEmployee(@RequestBody Employee.AddRequest addRequest){
        Employee hire=new Employee();
        hire.setFirstName(addRequest.firstName);
        hire.setLastName(addRequest.lastName);
        hire.setSalary(addRequest.salary);
        hire.setRole(addRequest.role);
        hire.setPassword(addRequest.password);
        return employeeRepository.save(hire);
    }

    @RequestMapping(value = "/employees",method = RequestMethod.DELETE)
    public void fireEmployee(@RequestParam long empID){
        employeeRepository.deleteById(empID);
    }

    @RequestMapping(value = "/seerequests", method = RequestMethod.GET)
    public List<Requests> seeRequests(){
        return requestsRepository.findAll();
    }

    @RequestMapping(value = "/addrequest", method = RequestMethod.GET)
    public Requests addRequest(@RequestParam long tableID, @RequestParam String message){
        Requests req = new Requests();
        req.setTable(tableID);
        req.setMessage(message);
        return requestsRepository.save(req);
    }

    @RequestMapping(value = "/closerequest", method = RequestMethod.GET)
    public void closeRequest(@RequestParam long reqID){
        requestsRepository.deleteById(reqID);
    }
}
