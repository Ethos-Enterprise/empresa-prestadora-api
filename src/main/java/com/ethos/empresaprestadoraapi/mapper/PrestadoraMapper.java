package com.ethos.empresaprestadoraapi.mapper;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.model.Prestadora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PrestadoraMapper {
    @Mapping(target = "fkEmpresa", source = "idEmpresa")
    Prestadora from(PrestadoraRequest prestadoraRequest);
}
