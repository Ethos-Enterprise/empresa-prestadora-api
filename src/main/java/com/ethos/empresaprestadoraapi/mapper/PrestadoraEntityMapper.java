package com.ethos.empresaprestadoraapi.mapper;

import com.ethos.empresaprestadoraapi.model.Prestadora;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PrestadoraEntityMapper {
    PrestadoraEntity from(Prestadora prestadora);
}
