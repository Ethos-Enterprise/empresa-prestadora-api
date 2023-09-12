package com.ethos.empresaprestadoraapi.service;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraEntityMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraMapper;
import com.ethos.empresaprestadoraapi.mapper.PrestadoraResponseMapper;
import com.ethos.empresaprestadoraapi.model.Prestadora;
import com.ethos.empresaprestadoraapi.model.Servico;
import com.ethos.empresaprestadoraapi.repository.PrestadoraRepository;
import com.ethos.empresaprestadoraapi.repository.entity.PrestadoraEntity;
import com.ethos.empresaprestadoraapi.repository.entity.ServicoEntity;
import lombok.RequiredArgsConstructor;
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
        Servico servicoModel = prestadoraMapper.from(prestadoraRequest.servicoRequest());
        PrestadoraEntity prestadoraEntity = prestadoraEntityMapper.from(prestadoraModel);
        ServicoEntity servicoEntity = prestadoraEntityMapper.from(servicoModel);
        PrestadoraEntity prestadoraEntitySalva = prestadoraRepository.save(prestadoraEntity);
        return prestadoraResponseMapper.from(prestadoraEntitySalva);
    }

    private PrestadoraEntity salvar(PrestadoraEntity entity){
        try {
            return prestadoraRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
