package com.example.hrSystem.repository;

import com.example.hrSystem.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository <Vacation , Integer>
{
}
