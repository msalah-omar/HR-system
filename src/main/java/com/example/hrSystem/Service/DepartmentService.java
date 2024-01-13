package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Department;
import com.example.hrSystem.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService
{
    private DepartmentRepository departmentRepository;


    public Page<Department> getAll(Integer page, Integer size)
    {
        return departmentRepository.findAll(PageRequest.of(page, size));
    }

    public Department save(Department department)
    {
        return departmentRepository.save(department);
    }

    public Optional<Department> getById(Integer id)
    {
        return departmentRepository.findById(id);
    }


    public Department update(Department department)
    {
        return departmentRepository.save(department);
    }


    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
