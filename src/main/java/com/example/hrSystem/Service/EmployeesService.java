package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.entity.Employees;
import com.example.hrSystem.repository.BranchesRepository;
import com.example.hrSystem.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeesService
{
    private EmployeesRepository employeesRepository;

    public Page<Employees> getAll(Integer page, Integer size)
    {
        return employeesRepository.findAll(PageRequest.of(page, size));
    }

    public Employees save(Employees employees)
    {
        return employeesRepository.save(employees);
    }

    public Optional<Employees> getById(Integer id)
    {
        return employeesRepository.findById(id);
    }

    public Optional<Employees> findNationalId(String nationalId) {
        return employeesRepository.findByNationalId(nationalId);
    }


    public Employees update(Employees employees)
    {
        return employeesRepository.save(employees);
    }


    public List<Employees> delete(Employees employees)
    {
        employeesRepository.delete(employees);
        return null;
    }
}
