package com.axonactive.khoa.dao;

import com.axonactive.khoa.entity.CompanyEntity;
import com.axonactive.khoa.entity.PersonEntity;
import com.axonactive.khoa.model.Person;
import com.axonactive.khoa.utils.MyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.concurrent.ContextService;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
@Transactional
public class PersonDAO extends BaseDAO {

    @Resource
    SessionContext ctx;
    @EJB
    private CompanyDAO companyDAO;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PersonEntity update(PersonEntity copy) {

        int isolate = Connection.TRANSACTION_READ_COMMITTED;
        ctx.getBusinessObject(PersonDAO.class).updateCompay();
        Query upateQuery = em.getEm().createQuery("UPDATE person SET age = :age WHERE name = :name");
        upateQuery.setParameter("age", copy.getAge());
        upateQuery.setParameter("name", copy.getName());

        upateQuery.executeUpdate();
        return copy;
    }

    public List dynamicAttribute() {
        CriteriaBuilder cb = em.getEm().getCriteriaBuilder();
//        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<PersonEntity> root = cq.from(PersonEntity.class);

        List<Tuple> list2 = em.getEm().createQuery(cq.multiselect(root.get("name").alias("name"), root.get("age").alias("age"))).getResultList();
        List<String> field = Arrays.asList("name", "age");
        ObjectMapper mapper = new ObjectMapper();

        List<Person> result = new ArrayList<>();
        for(Tuple tuple : list2) {
            ObjectNode entityNode = mapper.createObjectNode();
            field.forEach(alias -> entityNode.put(alias, tuple.get(alias, String.class)));
            try {
                Person person = mapper.readValue(entityNode.toString(), Person.class);
                result.add(person);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

//        List list2 = em.getEm().createQuery(cq.multiselect(root.get("name").alias("name"), root.get("age").alias("age")))
//                .unwrap(org.hibernate.Query.class)
//                .setResultTransformer(Transformers.aliasToBean(Person.class))
//                .list();
//                .getResultList();

        List list = em.getEm().createQuery("select p.name as name from PersonEntity p")
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(Person.class))
                .list();
        List list3 = em.getEm().createNamedQuery("dynamicField")
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(Person.class))
                .list();
        Tuple tuple;
//        .getResultList();
        return list;
    }

    public void updateCompay() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        companyDAO.merge(new CompanyEntity(1L,generatedString,"Dong Thap", null));
    }
}
