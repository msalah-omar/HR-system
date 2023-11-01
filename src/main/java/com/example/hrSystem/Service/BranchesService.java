package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Branches;
import com.example.hrSystem.repository.BranchesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BranchesService
{
    private BranchesRepository branchesRepository;

    public Page<Branches> getAll(Integer page, Integer size)
    {
        return branchesRepository.findAll(PageRequest.of(page, size));
    }

    public Branches save(Branches branches)
    {
        return branchesRepository.save(branches);
    }

    public Optional<Branches> getById(Integer id)
    {
        return branchesRepository.findById(id);
    }


    public Branches update(Branches branches)
    {
        return branchesRepository.save(branches);
    }


    public List<Branches> delete(Branches branches)
    {
        branchesRepository.delete(branches);
        return null;
    }
}
