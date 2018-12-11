package com.jpraphael.marble.rest.resources;

import com.jpraphael.marble.model.Orcamento;
import com.jpraphael.marble.rest.AbstractCrudResource;
import com.jpraphael.marble.services.AbstractCrudService;
import com.jpraphael.marble.services.OrcamentoService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("orcamentos")
public class OrcamentoResource extends AbstractCrudResource<Orcamento> {

    @Inject
    private OrcamentoService service;

    @Override
    protected AbstractCrudService<Orcamento> getService() {
        return service;
    }

}

