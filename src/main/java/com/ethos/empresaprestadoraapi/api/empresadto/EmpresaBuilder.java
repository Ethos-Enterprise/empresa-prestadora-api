package com.ethos.empresaprestadoraapi.api.empresadto;

import java.util.UUID;

public class EmpresaBuilder {
    private UUID id;

    public EmpresaBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public Empresa createEmpresa() {
        return new Empresa(id);
    }
}
