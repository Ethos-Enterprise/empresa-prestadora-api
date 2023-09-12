package com.ethos.empresaprestadoraapi.controller.response;

import java.util.List;
import java.util.UUID;

public record PrestadoraResponse(
        UUID idPrestadora,
        String razaoSocial,
        List<ServicoResponse> servicos,
        UUID fkEmpresa
) {
    public record ServicoResponse(
            UUID id,
            String nome,
            String descricao,
            String valor,
            String setor
    ) {
    }
}
