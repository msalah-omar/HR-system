package com.example.hrSystem.repository;

import com.example.hrSystem.entity.TimeSheetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetDetailRepository extends JpaRepository<TimeSheetDetail,Integer>
{

}
