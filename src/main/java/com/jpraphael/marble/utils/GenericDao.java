package com.jpraphael.marble.utils;

import com.jpraphael.marble.model.Entidade;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Dao Generico para operações CRUD encapsulando o EntityManager
 *
 * @param <T> Tipo da Entidade no GenericDao
 * @author jpraphael
 */
public class GenericDao<T extends Entidade> {

    private final EntityManager em;
    private final Class<T> type;

    public GenericDao(EntityManager em, Class<T> type) {
        this.em = em;
        this.type = type;
    }

    public T save(T bean) {

        if (bean.getId() != null) {

            T oldBean = em.find(type, bean.getId());
            this.checkNotFound(oldBean);

            return em.merge(bean);

        } else {

            em.persist(bean);

            return bean;

        }
    }

    public void delete(Long id) {

        T reference = em.getReference(type, id);
        this.checkNotFound(reference);
        em.remove(reference);

    }

    protected void checkNotFound(T bean) {
        if (bean == null) {
            throw new EntityNotFoundException();
        }
    }


    private boolean nomEmpty(String... values) {
        for (String val : values) {
            if (val == null || val.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private List<String> asListString(String value) {
        return Arrays.asList(value.split("\\."));
    }

    public List<T> findAll(String filterField, String filterData, String order) {
        return findAll(null, null, filterField, filterData, order);
    }

    public Long getCount(String filterField, String filterData) {
        JpaCriteriaHelper helper = JpaCriteriaHelper.select(em, type);
        if (nomEmpty(filterField, filterData)) {
            helper.where(asListString(filterField), JpaCriteriaHelper.ComparatorOperator.LIKE_IGNORE_CASE, filterData.replaceAll("\\b", "%"));
        }
        return helper.count();
    }

    public List<T> findAll(Integer pageSize, Integer pageNumber, String filterField, String filterData, String order) {
        JpaCriteriaHelper helper = JpaCriteriaHelper.select(em, type);
        if (pageSize != null && pageNumber != null) {
            helper.setPageSize(pageSize)
                    .page(pageNumber);
        }
        if (nomEmpty(filterField, filterData)) {
            helper.where(asListString(filterField), JpaCriteriaHelper.ComparatorOperator.LIKE_IGNORE_CASE, filterData.replaceAll("\\b", "%"));
        }
        if (nomEmpty(order)) {
            String[] parts = order.split("\\+");
            helper.orderBy(parts[0]);
            if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
                helper.desc();
            }
        }
        return helper.getResults();
    }

    public JpaCriteriaHelper<T> selectCriteria() {
        return JpaCriteriaHelper.select(em, type);
    }

    public JPAQueryFactory query() {
        return new JPAQueryFactory(em);
    }

    public JPAUpdateClause update(EntityPath<T> entityPath) {
        return new JPAUpdateClause(em, entityPath);
    }

    public T find(Long id) {
        T bean = em.find(type, id);
        if (bean == null) {
            throw new EntityNotFoundException();
        }
        return bean;
    }
}