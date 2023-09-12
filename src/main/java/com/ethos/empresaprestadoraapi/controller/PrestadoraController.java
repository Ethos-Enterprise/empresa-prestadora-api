package com.ethos.empresaprestadoraapi.controller;

import com.ethos.empresaprestadoraapi.controller.request.PrestadoraRequest;
import com.ethos.empresaprestadoraapi.controller.response.PrestadoraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ethos.empresaprestadoraapi.service.PrestadoraService;

@RestController
@RequestMapping(path = "/v1.0/prestadoras")
public class PrestadoraController {
    @Autowired
    private PrestadoraService prestadoraService;

    @PostMapping
    public PrestadoraResponse postEmpresaPrestadora(@RequestBody PrestadoraRequest prestadoraRequest){
        return prestadoraService.postEmpresaPrestadora(prestadoraRequest);
    }
}
