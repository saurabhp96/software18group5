package group5.f4tapi.repository;

import group5.f4tapi.entity.Employee;
import group5.f4tapi.entity.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void hireEmployee() {
        Employee newEmp = new Employee();
        newEmp.setFirstName("TestFirstName");
        newEmp.setLastName("TestLastName");
        newEmp.setPassword("TestPassword");
        newEmp.setRole("Chef");
        newEmp.setSalary(10.0);

        long initialCount = employeeRepository.count();
        employeeRepository.save(newEmp);

        Optional<Employee> result = employeeRepository.findById(newEmp.getEmpID());
        assertThat(result.isPresent() && result.get().equals(newEmp)).isTrue();
        assertThat(employeeRepository.count()).isEqualTo(initialCount+1);
    }

    @Test
    public void fireEmployee() {
        long toFire = employeeRepository.findAll().get(0).getEmpID();
        long initialCount = employeeRepository.count();
        employeeRepository.deleteById(toFire);
        Optional<Employee> fired = employeeRepository.findById(toFire);
        assertThat(fired.isPresent()).isFalse();
        assertThat(employeeRepository.count()).isEqualTo(initialCount-1);
    }
}