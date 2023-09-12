package com.ethos.empresaprestadoraapi.repository;

import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadoraRepository extends JpaRepository<PrestadoraEntity, UUID> {
}
