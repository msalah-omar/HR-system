package com.example.hrSystem.Service;


import com.example.hrSystem.entity.Project;

import com.example.hrSystem.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService
{
    private ProjectRepository projectRepository;


    public Page<Project> getAll(Integer page, Integer size)
    {
        return projectRepository.findAll(PageRequest.of(page, size));
    }

    public Project save(Project project)
    {
        return projectRepository.save(project);
    }

    public Optional<Project> getById(Integer id)
    {
        return projectRepository.findById(id);
    }

    public Project update(Project project)
    {
        return projectRepository.save(project);
    }


    public void delete(Project project)
    {
        projectRepository.delete(project);
    }
}
