package com.ethos.empresaprestadoraapi.service;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.exception.EmpresaNaoEncontradaException;
import com.ethos.empresaprestadoraapi.exception.PrestadoraJaCadastradaException;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraEntityMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraResponseMapper;
import com.ethos.empresaprestadoraapi.model.Prestadora;
import com.ethos.empresaprestadoraapi.repository.PrestadoraRepository;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrestadoraService {
    private final PrestadoraRepository prestadoraRepository;
    private final PrestadoraMapper prestadoraMapper;
    private final PrestadoraResponseMapper prestadoraResponseMapper;
    private final PrestadoraEntityMapper prestadoraEntityMapper;

    public PrestadoraResponse postEmpresaPrestadora(PrestadoraRequest prestadoraRequest) {
        Prestadora prestadoraModel = prestadoraMapper.from(prestadoraRequest);
        PrestadoraEntity prestadoraEntity = prestadoraEntityMapper.from(prestadoraModel);
        PrestadoraEntity prestadoraEntitySalva = salvar(prestadoraEntity);
        return prestadoraResponseMapper.from(prestadoraEntitySalva);
    }

    private PrestadoraEntity salvar(PrestadoraEntity entity) {
        try {
            return prestadoraRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("prestadora_servico_fk_empresa_key")) {
                throw new PrestadoraJaCadastradaException(
                        String.format("Empresa com id %s já cadastrada como prestadora de serviço", entity.getFkEmpresa().toString()));
            } else if (e.getMessage().contains("fk_empresa_id")) {
                throw new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrada", entity.getFkEmpresa().toString()));

            } throw new RuntimeException(e.getMessage());
        }
    }

    public List<PrestadoraResponse> getAllPrestadoras(){
        return prestadoraRepository.findAll().stream().map(prestadoraResponseMapper::from).toList();
    }

    public PrestadoraResponse getPrestadoraById(java.util.UUID id){
        return prestadoraResponseMapper.from(getPrestadoraEntityById(id));
    }

    private PrestadoraEntity getPrestadoraEntityById(UUID id){
        return prestadoraRepository.findById(id).orElseThrow();
    }
}
