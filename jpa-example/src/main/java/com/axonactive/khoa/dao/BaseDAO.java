package com.axonactive.khoa.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class BaseDAO {
    
    @Inject
    protected CustomerManager em;
    
    public <T> List<T> getAll(Class<T> entityClass, String entityGraphName) {
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        cq.select(entity);
        if (StringUtils.isNotEmpty(entityGraphName)) {
            return em.getEm().createQuery(cq).setHint("javax.persistence.loadgraph", getEntityGraph(entityGraphName)).getResultList();
        }

        return em.getEm().createQuery(cq).getResultList();
    }
    
    public <T> List<T> getAll(Class<T> entityClass) {
        return getAll(entityClass, null);
    }
    
    public <T> T persist(T instance) {
        em.getEm().persist(instance);
        return instance;
    }

    public <T> T merge(T instance) {
        em.getEm().merge(instance);
        return instance;
    }
    
    protected EntityGraph getEntityGraph(String entityGraphName) {
        return em.getEm().getEntityGraph(entityGraphName);
    }
}
