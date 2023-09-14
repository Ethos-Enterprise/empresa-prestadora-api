package com.ethos.empresaprestadoraapi.model;

import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import java.util.UUID;
import lombok.Builder;

public record Prestadora(
        StatusAprovacaoEnum statusAprovacao,
        UUID fkEmpresa
) {
    @Builder(toBuilder = true)
    public Prestadora(StatusAprovacaoEnum statusAprovacao, UUID fkEmpresa) {
        this.statusAprovacao = statusAprovacao == null ? StatusAprovacaoEnum.PENDENTE : statusAprovacao;
        this.fkEmpresa = fkEmpresa;
    }
}
