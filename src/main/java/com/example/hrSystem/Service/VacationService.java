package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Vacation;
import com.example.hrSystem.repository.VacationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VacationService
{
    private VacationRepository vacationRepository;


    public Page<Vacation> getAll(Integer page, Integer size)
    {
        return vacationRepository.findAll(PageRequest.of(page, size));
    }

    public Vacation save(Vacation vacation)
    {
        return vacationRepository.save(vacation);
    }

    public Optional<Vacation> getById(Integer id)
    {
        return vacationRepository.findById(id);
    }


    public Vacation update(Vacation vacation)
    {
        return vacationRepository.save(vacation);
    }

    public List<Vacation> delete(Vacation vacation)
    {
        vacationRepository.delete(vacation);
        return null;
    }
}
