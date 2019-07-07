package com.axonactive.khoa.dao;

import com.axonactive.khoa.entity.CompanyEntity;
import com.axonactive.khoa.entity.EmployeeEntity;
import com.axonactive.khoa.entity.PersonEntity;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmployeeDao extends BaseDAO {

    public List<EmployeeEntity> findByCompanyAddress(String companyAddress) {
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> cq = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> employee = cq.from(EmployeeEntity.class);
        Fetch<EmployeeEntity, PersonEntity> person = employee.fetch("person", JoinType.INNER);

        Root<CompanyEntity> company = cq.from(CompanyEntity.class);

        cq.select(employee);
        List<Predicate> whereClause = new ArrayList<>();
        whereClause.add(cb.equal(company.get("companyName"), employee.get("companyName")));
        whereClause.add(cb.equal(company.get("address"), cb.literal(companyAddress)));
        cq.where(whereClause.toArray(new Predicate[]{}));

        return em.getEm().createQuery(cq).getResultList();
    }

    public List<EmployeeEntity> findAll() {
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> cq = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> employee = cq.from(EmployeeEntity.class);
        Fetch<EmployeeEntity, PersonEntity> person = employee.fetch("person", JoinType.INNER);
        cq.select(employee);
        return em.getEm().createQuery(cq).getResultList();
    }
}
