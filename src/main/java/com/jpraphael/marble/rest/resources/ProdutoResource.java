package com.jpraphael.marble.rest.resources;

import com.jpraphael.marble.model.Produto;
import com.jpraphael.marble.rest.AbstractCrudResource;
import com.jpraphael.marble.services.AbstractCrudService;
import com.jpraphael.marble.services.ProdutoService;
import com.jpraphael.marble.utils.RequestAuth;

import javax.inject.Inject;
import javax.ws.rs.Path;
@Path("produtos")
public class ProdutoResource extends AbstractCrudResource<Produto> {

    @Inject
    private ProdutoService service;

    @Override
    protected AbstractCrudService<Produto> getService() {
        return service;
    }

}
