package com.jpraphael.marble.services;

import com.jpraphael.marble.model.Produto;
import com.jpraphael.marble.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProdutoService extends AbstractCrudService<Produto> {

    @Inject
    private GenericDao<Produto> dao;

    @Override
    protected GenericDao<Produto> getDao() {
        return dao;
    }
}
