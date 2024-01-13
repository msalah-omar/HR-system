package com.example.hrSystem.mapper;

import com.example.hrSystem.Dto.UserDto;
import com.example.hrSystem.entity.User;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Mappings({
            @Mapping(source = "roles", target = "roles", ignore = true)
    })
    public abstract UserDto toDto(User user);

    public abstract List<UserDto> toDto(List<User> entityList);

//    @AfterMapping
//    public void toDtoAfterMapping(User entity, @MappingTarget UserDto dto) {
//        if (HibernateUtils.isConvertible(entity.getRoles())) {
//            dto.setRoles(roleMapper.toDto(entity.getRoles()));
//        }
//    }

    public abstract List<User> toEntity(List<UserDto> dtoList);

    @InheritInverseConfiguration
    public abstract User toEntity(UserDto userDto);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User updateEntity(UserDto userDto, @MappingTarget User user);


}
