package eg.gov.moj.hr.service;

import com.example.hrSystem.Service.DepartmentService;

import com.example.hrSystem.entity.Department;
import com.example.hrSystem.repository.DepartmentRepository;
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
import static org.mockito.Mockito.*;


public class DepartmentServiceTest
{
    private DepartmentRepository departmentRepository = mock(DepartmentRepository.class);
    private DepartmentService departmentService = new DepartmentService(departmentRepository);

    private Department getDepartment(){
        Department department = new Department();
        department.setId(new Random().nextInt());
        department.setArabicName("department");
        return department;
    }

    @Test
    public void testGetById() {
        //Prep
        Department department = getDepartment();

        //Test
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        //Assert
       assertThat(departmentService.getById(department.getId())).isEqualTo(Optional.of(department));
    }

    @Test
    public void testSave(){
        //Prep
        Department department = getDepartment();

        //Test
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        //Assert
        assertThat(departmentService.save(department)).isEqualTo(department);

    }

    @Test
    public void testGetAll(){
        //Prep
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(getDepartment());
        departmentList.add(getDepartment());
        Page<Department> departmentPage = new PageImpl(departmentList);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //Test
        Mockito.when(departmentRepository.findAll(pageRequest)).thenReturn(departmentPage);
        Page<Department> all = departmentService.getAll(0, 10);

        //Assert
        assertThat(all.getContent().equals(departmentList));

    }

    @Test
    public void testUpdate(){
        //Prep
        Department department = getDepartment();

        //Test
        Mockito.when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        department.setArabicName("update-asd");
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        departmentService.update(department);

        //Assert
       verify(departmentRepository).save(department);
    }

    @Test
    public void testDelete(){
        //Prep
        Department department = getDepartment();

        //Test
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        //Assert
        departmentService.delete(department);
        verify(departmentRepository).delete(department);
    }
}
