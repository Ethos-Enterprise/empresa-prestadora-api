package com.ethos.empresaprestadoraapi.repository.entity;

import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Builder;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "prestadora_servico")
public class PrestadoraEntity {
    @Id
    UUID id;

    String statusAprovacao;
    UUID fkEmpresa;

    public PrestadoraEntity() {
    }

    @Builder(toBuilder = true)
    public PrestadoraEntity(String statusAprovacao, UUID fkEmpresa) {
        this.id = UUID.randomUUID();
        this.statusAprovacao = statusAprovacao;
        this.fkEmpresa = fkEmpresa;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(String statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public UUID getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(UUID fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
