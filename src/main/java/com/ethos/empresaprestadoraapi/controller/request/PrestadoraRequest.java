package com.ethos.empresaprestadoraapi.controller.request;

import com.ethos.empresaprestadoraapi.EmpresaPrestadoraApiApplication;
import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import java.math.BigDecimal;
import java.util.UUID;

public record PrestadoraRequest(
        UUID idEmpresa,
        StatusAprovacaoEnum statusAprovacao,
        ServicoRequest servicoRequest
) {
    public record ServicoRequest(
            String nome,
            String descricao,
            BigDecimal valor,
            String setor) {
    }

}
