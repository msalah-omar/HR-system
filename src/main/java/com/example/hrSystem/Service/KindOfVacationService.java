package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Department;
import com.example.hrSystem.entity.KindOfVacation;
import com.example.hrSystem.repository.DepartmentRepository;
import com.example.hrSystem.repository.KindOfVacationDtoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KindOfVacationService
{
    private KindOfVacationDtoRepository kindOfVacationDtoRepository;


    public Page<KindOfVacation> getAll(Integer page, Integer size)
    {
        return kindOfVacationDtoRepository.findAll(PageRequest.of(page, size));
    }

    public KindOfVacation save(KindOfVacation kindOfVacation)
    {
        return kindOfVacationDtoRepository.save(kindOfVacation);
    }

    public Optional<KindOfVacation> getById(Integer id)
    {
        return kindOfVacationDtoRepository.findById(id);
    }


    public KindOfVacation update(KindOfVacation kindOfVacation)
    {
        return kindOfVacationDtoRepository.save(kindOfVacation);
    }


    public List<KindOfVacation> delete(KindOfVacation kindOfVacation)
    {
        kindOfVacationDtoRepository.delete(kindOfVacation);
        return null;
    }
}
