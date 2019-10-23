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
public class CompanyDAO extends BaseDAO {

//    public List<CompanyEntity> findCompaniesHaveNoEmployee() {
//        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
//        CriteriaQuery<CompanyEntity> cq = cb.createQuery(CompanyEntity.class);
//        Root<EmployeeEntity> employee = cq.from(EmployeeEntity.class);
//        Root<CompanyEntity> company = cq.from(CompanyEntity.class);
//
//        cq.select(company).distinct(true);
//        List<Predicate> whereClause = new ArrayList<>();
//        whereClause.add(cb.equal(company.get("companyName"), employee.get("companyName")));
//        cq.where(whereClause.toArray(new Predicate[]{}));
//
//        return em.getEm().createQuery(cq).getResultList();
//    }

    public List<CompanyEntity> findCompaniesHaveNoEmployee_solution() {
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> cq = cb.createQuery(CompanyEntity.class);
        Root<CompanyEntity> company = cq.from(CompanyEntity.class);
        Join<CompanyEntity, EmployeeEntity> employee = company.join("employees", JoinType.LEFT);
        cq.select(company).distinct(true);

        List<Predicate> whereClause = new ArrayList<>();
        whereClause.add(employee.get("id").isNull());
        cq.where(whereClause.toArray(new Predicate[]{}));

        return em.getEm().createQuery(cq).setHint("javax.persistence.loadgraph", getEntityGraph("graph.employeesIncludePerson")).getResultList();
    }
}
