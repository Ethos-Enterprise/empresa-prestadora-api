package com.ethos.empresaprestadoraapi.controller.response;

import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import java.util.List;
import java.util.UUID;

public record PrestadoraResponse(
        UUID idPrestadora,
        StatusAprovacaoEnum statusAprovacao,
        UUID fkEmpresa
) {
}
