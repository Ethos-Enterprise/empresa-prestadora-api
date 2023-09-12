package com.ethos.empresaprestadoraapi.mapper;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.model.Prestadora;
import com.ethos.empresaprestadoraapi.model.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PrestadoraMapper {
    Servico from(PrestadoraRequest.ServicoRequest servicoRequest);
    @Mapping(target = "fkEmpresa", source = "idEmpresa")
    Prestadora from(PrestadoraRequest prestadoraRequest);
}
