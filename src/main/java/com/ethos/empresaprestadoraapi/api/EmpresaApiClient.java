package com.ethos.empresaprestadoraapi.api;


import com.ethos.empresaprestadoraapi.api.empresadto.Empresa;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "empresa-api", url = "http://localhost:8082/v1.0/empresas")
public interface EmpresaApiClient {
    @GetMapping(path = "/{id}")
    Empresa getEmpresaById(@PathVariable(value = "id") UUID id);
}
