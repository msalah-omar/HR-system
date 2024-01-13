package com.example.hrSystem.Service;

import com.example.hrSystem.entity.Role;
import com.example.hrSystem.repository.RoleRepository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Page<Role> getAll(Integer page, Integer size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

    public Role getReference(Integer id) {
        return roleRepository.getById(id);
    }

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    public Optional<Role> findByCode(String code) {
        return roleRepository.findByCode(code);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
