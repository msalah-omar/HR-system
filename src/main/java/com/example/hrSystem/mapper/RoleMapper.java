package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.RoleDto;
import com.example.hrSystem.entity.Role;
import com.example.hrSystem.mapper.common.JPAEntityMapper;
;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto>
{
    Set<RoleDto> toDto(Set<Role> roles);
}
