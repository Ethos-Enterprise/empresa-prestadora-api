package com.ethos.empresaprestadoraapi.controller;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import com.ethos.empresaprestadoraapi.repository.entity.statusenum.StatusAprovacaoEnum;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ethos.empresaprestadoraapi.service.PrestadoraService;

@RestController
@RequestMapping(path = "/v1.0/prestadoras")
public class PrestadoraController {
    @Autowired
    private PrestadoraService prestadoraService;

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public PrestadoraResponse postEmpresaPrestadora(@RequestBody PrestadoraRequest prestadoraRequest){
        return prestadoraService.postEmpresaPrestadora(prestadoraRequest);
    }

    @GetMapping
    public List<PrestadoraResponse> getEmpresaPrestadora(@RequestParam (value = "statusAprovacao", required = false) StatusAprovacaoEnum status){
        if (status != null){
            return prestadoraService.getPrestadoraByStatus(status);
        }
        return prestadoraService.getAllPrestadoras();
    }

    @GetMapping(path = "/{id}")
    public PrestadoraResponse getEmpresaPrestadoraById(@PathVariable UUID id){
        return prestadoraService.getPrestadoraById(id);
    }

    @PutMapping(path = "/{id}")
    public PrestadoraResponse putEmpresaPrestadoraStatus(@PathVariable UUID id, @RequestBody PrestadoraRequest prestadoraRequest){
        return prestadoraService.putPrestadoraStatus(id, prestadoraRequest);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteEmpresaPrestadora(@PathVariable UUID id){
        prestadoraService.deletePrestadora(id);
    }

    @GetMapping(path = "/empresa/{fkEmpresa}")
    public PrestadoraResponse getPrestadoraByFkEmpresa(@PathVariable UUID fkEmpresa){
        return prestadoraService.getPrestadoraByFkEmpresa(fkEmpresa);
    }
}
