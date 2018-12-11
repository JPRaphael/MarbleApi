package com.jpraphael.marble.rest.resources;

import com.jpraphael.marble.model.Cliente;
import com.jpraphael.marble.rest.AbstractCrudResource;
import com.jpraphael.marble.services.AbstractCrudService;
import com.jpraphael.marble.services.ClienteService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("clientes")
public class ClienteResource extends AbstractCrudResource<Cliente> {

    @Inject
    private ClienteService service;

    @Override
    protected AbstractCrudService<Cliente> getService() {
        return service;
    }

}

