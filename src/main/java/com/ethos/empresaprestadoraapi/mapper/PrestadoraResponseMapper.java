package com.ethos.empresaprestadoraapi.mapper;

import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PrestadoraResponseMapper {
    @Mapping(source = "id", target = "idPrestadora")
    @Mapping(source = "fkEmpresa", target = "fkEmpresa")
    PrestadoraResponse from(PrestadoraEntity prestadoraEntity);
}
