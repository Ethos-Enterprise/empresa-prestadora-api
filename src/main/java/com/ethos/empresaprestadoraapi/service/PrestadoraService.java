package com.ethos.empresaprestadoraapi.service;

import com.ethos.empresaprestadoraapi.api.EmpresaApiClient;
import com.ethos.empresaprestadoraapi.api.empresadto.Empresa;
import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.exception.EmpresaApiException;
import com.ethos.empresaprestadoraapi.exception.EmpresaNaoEncontradaException;
import com.ethos.empresaprestadoraapi.exception.PrestadoraJaCadastradaException;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraEntityMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraResponseMapper;
import com.ethos.empresaprestadoraapi.model.Prestadora;
import com.ethos.empresaprestadoraapi.repository.PrestadoraRepository;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import feign.FeignException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrestadoraService {
    private final PrestadoraRepository prestadoraRepository;
    private final PrestadoraMapper prestadoraMapper;
    private final PrestadoraResponseMapper prestadoraResponseMapper;
    private final PrestadoraEntityMapper prestadoraEntityMapper;
    private final EmpresaApiClient empresaApiClient;

    public PrestadoraResponse postEmpresaPrestadora(PrestadoraRequest prestadoraRequest) {
        Prestadora prestadoraModel = prestadoraMapper.from(prestadoraRequest);
        validarEmpresa(prestadoraModel.fkEmpresa());
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

            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<PrestadoraResponse> getAllPrestadoras() {
        return prestadoraRepository.findAll().stream().map(prestadoraResponseMapper::from).toList();
    }

    public List<PrestadoraResponse> getPrestadoraByStatus(StatusAprovacaoEnum status) {
        String statusString = status.toString();
        List<PrestadoraEntity> prestadoraEntityList = prestadoraRepository.findByStatusAprovacao(statusString);
        return prestadoraEntityList.stream().map(prestadoraResponseMapper::from).collect(Collectors.toList());
    }

    public PrestadoraResponse getPrestadoraById(UUID id) {
        return prestadoraResponseMapper.from(getPrestadoraEntityById(id));
    }

    private PrestadoraEntity getPrestadoraEntityById(UUID id) {
        try {
            return prestadoraRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrada", id.toString()));
        }
    }

    public PrestadoraResponse putPrestadoraStatus(UUID id, PrestadoraRequest prestadoraRequest) {
        PrestadoraEntity prestadoraEntity = getPrestadoraEntityById(id);
        prestadoraEntity.setStatusAprovacao(prestadoraRequest.statusAprovacao().toString());
        PrestadoraEntity prestadoraEntityAtualizada = atualizarStatus(prestadoraEntity);
        return prestadoraResponseMapper.from(prestadoraEntityAtualizada);
    }

    public PrestadoraResponse getPrestadoraByFkEmpresa(UUID fkEmpresa){
        try{
            Empresa empresa = empresaApiClient.getEmpresaById(fkEmpresa);
            if(empresa == null){
                throw new EmpresaNaoEncontradaException(String.format("Empresa com id %s não existe", fkEmpresa.toString()));
            }
            Optional<PrestadoraEntity> prestadoraEntity = prestadoraRepository.findPrestadoraEntityByfkEmpresa(fkEmpresa);

            if(prestadoraEntity.isEmpty()){
                throw new EmpresaNaoEncontradaException(String.format("A empresa com id %s não é uma empresa prestadora de serviços", fkEmpresa.toString()));
            }

            return prestadoraResponseMapper.from(prestadoraEntity.get());
        }catch (FeignException e){
            if(e.status() == HttpStatus.SC_NOT_FOUND){
                throw new EmpresaNaoEncontradaException(String.format("Empresa com id %s não existe", fkEmpresa.toString()));
            }
            else {
                throw new EmpresaApiException(e.getMessage());
            }
        }
    }

    private PrestadoraEntity atualizarStatus(PrestadoraEntity entity) {
        prestadoraRepository.updateStatusAprovacao(entity.getId(), entity.getStatusAprovacao());
        return getPrestadoraEntityById(entity.getId());
    }

    public void deletePrestadora(UUID id) {
        PrestadoraEntity prestadoraEntity = getPrestadoraEntityById(id);
        deletar(prestadoraEntity);
        prestadoraResponseMapper.from(prestadoraEntity);
    }

    private void deletar(PrestadoraEntity entity) {
        prestadoraRepository.delete(entity);
    }

    private void validarEmpresa(UUID id) {
        try {
            Empresa empresa = empresaApiClient.getEmpresaById(id);
        } catch (FeignException e) {
            e.printStackTrace();
            if (e.status() == 404) {
                throw new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrada", id.toString()));
            }
            throw new EmpresaApiException(e.getMessage());
        }
    }
}
