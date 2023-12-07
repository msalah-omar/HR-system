package com.example.hrSystem.Service;


import com.example.hrSystem.entity.TimeSheetDetail;
import com.example.hrSystem.repository.TimeSheetDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TimeSheetDetailService
{
    private TimeSheetDetailRepository timeSheetDetailRepository;

    public Page<TimeSheetDetail> getAll(Integer page, Integer size)
    {
        return timeSheetDetailRepository.findAll(PageRequest.of(page, size));
    }

    public TimeSheetDetail save(TimeSheetDetail timeSheetDetail)
    {
        return timeSheetDetailRepository.save(timeSheetDetail);
    }

    public Optional<TimeSheetDetail> getById(Integer id)
    {
        return timeSheetDetailRepository.findById(id);
    }


    public TimeSheetDetail update(TimeSheetDetail timeSheetDetail)
    {
        return timeSheetDetailRepository.save(timeSheetDetail);
    }


    public List<TimeSheetDetail> delete(TimeSheetDetail timeSheetDetail)
    {
        timeSheetDetailRepository.delete(timeSheetDetail);
        return null;
    }
}
