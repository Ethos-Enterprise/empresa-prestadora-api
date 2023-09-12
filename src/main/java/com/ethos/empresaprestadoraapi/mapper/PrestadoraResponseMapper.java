package com.ethos.empresaprestadoraapi.mapper;

import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PrestadoraResponseMapper {
    PrestadoraResponse from(PrestadoraEntity prestadoraEntity);
}
