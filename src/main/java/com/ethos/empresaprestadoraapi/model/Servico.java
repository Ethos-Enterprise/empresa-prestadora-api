package com.ethos.empresaprestadoraapi.model;

import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import java.math.BigDecimal;
import lombok.Builder;

public record Servico(String nome, String descricao, BigDecimal valor, Prestadora prestadora) {
    @Builder(toBuilder = true)

    public Servico(String nome, String descricao, BigDecimal valor, Prestadora prestadora) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.prestadora = prestadora;
    }
}
