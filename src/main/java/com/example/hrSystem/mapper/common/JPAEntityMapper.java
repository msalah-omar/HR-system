package com.example.hrSystem.mapper.common;


import com.example.hrSystem.Dto.commen.RestDto;
import com.example.hrSystem.entity.commen.JPAEntity;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface JPAEntityMapper<T extends JPAEntity, S extends RestDto> {

    T toEntity(S s);

    S toDto(T t);

    List<T> toEntity(List<S> dtoList);

    List<S> toDto(List<T> dtoList);

    T updateEntityFromDto(S s, @MappingTarget T t);
}
