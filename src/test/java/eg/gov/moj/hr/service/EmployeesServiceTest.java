package eg.gov.moj.hr.service;


import com.example.hrSystem.Service.EmployeesService;
import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmployeesServiceTest
{
    private EmployeesRepository employeesRepository = mock(EmployeesRepository.class);
    private EmployeesService employeesService = new EmployeesService(employeesRepository);

    private Employees getEmployees(){
        Employees employees = new Employees();
        employees.setId(new Random().nextInt());
        employees.setName("employees");
        return employees;
    }

    @Test
    public void testGetById() {
        //Prep
        Employees employees = getEmployees();

        //Test
        Mockito.when(employeesRepository.findById(employees.getId())).thenReturn(Optional.of(employees));

        //Assert
        assertThat(employeesService.getById(employees.getId())).isEqualTo(Optional.of(employees));
    }

    @Test
    public void testSave(){
        //Prep
        Employees employees = getEmployees();

        //Test
        Mockito.when(employeesRepository.save(employees)).thenReturn(employees);

        //Assert
        assertThat(employeesService.save(employees)).isEqualTo(employees);

    }

    @Test
    public void testGetAll(){
        //Prep
        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(getEmployees());
        employeesList.add(getEmployees());
        Page<Employees> employeesPage = new PageImpl(employeesList);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //Test
        Mockito.when(employeesRepository.findAll(pageRequest)).thenReturn(employeesPage);
        Page<Employees> all = employeesService.getAll(0, 10);

        //Assert
        assertThat(all.getContent().equals(employeesList));

    }

    @Test
    public void testUpdate(){
        //Prep
        Employees employees = getEmployees();

        //Test
        Mockito.when(employeesRepository.findById(1)).thenReturn(Optional.of(employees));
        employees.setName("update-asd");
        Mockito.when(employeesRepository.save(employees)).thenReturn(employees);
        employeesService.update(employees);

        //Assert
        verify(employeesRepository).save(employees);
    }

    @Test
    public void testDelete(){
        //Prep
        Employees employees = getEmployees();

        //Test
        Mockito.when(employeesRepository.findById(employees.getId())).thenReturn(Optional.of(employees));

        //Assert
        employeesService.delete(employees);
        verify(employeesRepository).delete(employees);
    }

}
