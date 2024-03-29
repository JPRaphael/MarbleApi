package com.jpraphael.marble.services;

import com.jpraphael.marble.model.Entidade;
import com.jpraphael.marble.utils.GenericDao;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.Valid;
import java.util.List;

public abstract class AbstractCrudService<T extends Entidade> {

    public List<T> findAll(Integer pageSize, Integer pageNumber, String filterField, String filterData, String order) {
        return getDao().findAll(pageSize, pageNumber, filterField, filterData, order);
    }

    public Long getCount(String filterField, String filterData) {
        return getDao().getCount(filterField, filterData);
    }

    public List<T> findAllOver(String filterField, String filterData, String order) {
        return getDao().findAll(filterField, filterData, order);
    }

    public T findById(Long id) {
        return getDao().find(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T insert(@Valid T bean) {
        return getDao().save(bean);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) {
        getDao().delete(id);
    }

    protected abstract GenericDao<T> getDao();
}