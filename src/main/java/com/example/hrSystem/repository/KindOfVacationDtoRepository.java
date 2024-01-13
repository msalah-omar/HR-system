package com.example.hrSystem.repository;

import com.example.hrSystem.entity.KindOfVacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindOfVacationDtoRepository extends JpaRepository <KindOfVacation , Integer>
{
}
