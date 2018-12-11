package com.jpraphael.marble.services;

import com.jpraphael.marble.model.Orcamento;
import com.jpraphael.marble.utils.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrcamentoService extends AbstractCrudService<Orcamento> {

    @Inject
    private GenericDao<Orcamento> dao;

    @Override
    protected GenericDao<Orcamento> getDao() {
        return dao;
    }
}
