package com.axonactive.khoa.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class BaseDAO {

    @Inject
    private CustomerManager em;

    public <T> List<T> getAll(Class<T> entityClass){
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        cq.select(entity);
        return em.getEm().createQuery(cq).getResultList();
    }

    public <T> T persist(T instance){
        em.getEm().persist(instance);
        return instance;
    }
}
