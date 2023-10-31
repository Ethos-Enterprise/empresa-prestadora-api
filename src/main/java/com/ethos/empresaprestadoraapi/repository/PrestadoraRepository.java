package com.ethos.empresaprestadoraapi.repository;

import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface PrestadoraRepository extends JpaRepository<PrestadoraEntity, UUID> {
    List<PrestadoraEntity> findByStatusAprovacao(String statusAprovacao);

    @Transactional
    @Modifying
    @Query("UPDATE PrestadoraEntity p SET p.statusAprovacao = ?2 WHERE p.id = ?1")
    void updateStatusAprovacao(UUID id, String statusAprovacao);

    Optional<PrestadoraEntity> findPrestadoraEntityByfkEmpresa(UUID id);
}
