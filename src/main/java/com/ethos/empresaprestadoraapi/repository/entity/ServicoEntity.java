package com.ethos.empresaprestadoraapi.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "SERVICO")
public class ServicoEntity {
    @Id
    UUID id;

    String nome;

    String descricao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestadora_id", referencedColumnName = "id")
    PrestadoraEntity prestadora;
    String valor;

    public ServicoEntity() {
    }
    @Builder(toBuilder = true)
    public ServicoEntity(String nome, String descricao, String valor) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
