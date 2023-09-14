package com.ethos.empresaprestadoraapi.controller;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<PrestadoraResponse> getEmpresaPrestadora(){
        return prestadoraService.getAllPrestadoras();
    }
}
