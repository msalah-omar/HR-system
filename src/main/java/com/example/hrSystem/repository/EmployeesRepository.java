package com.example.hrSystem.repository;

import com.example.hrSystem.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees ,Integer>
{
    @Query(value = "SELECT e FROM Employees e WHERE e.nationalId= :nationalId ")
    Optional<Employees> findByNationalId(@Param("nationalId") String nationalId);

}
