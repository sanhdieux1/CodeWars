package com.axonactive.khoa.rest;

import com.axonactive.khoa.dao.CompanyDAO;
import com.axonactive.khoa.dao.PersonDAO;
import com.axonactive.khoa.entity.CompanyEntity;
import com.axonactive.khoa.entity.PersonEntity;
import com.axonactive.khoa.model.Person;
import com.axonactive.khoa.utils.CopyUtil;
import org.apache.commons.lang3.RandomStringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Path("/persons")
@Transactional
public class PersonResource {
    @EJB
    private PersonDAO baseDAO;

    @EJB
    private CompanyDAO companyDAO;
    @GET
    public List<Person> getAllPerson() {
        return baseDAO.getAll(PersonEntity.class)
                .stream()
                .map(entity -> CopyUtil.copy(entity, Person.class))
                .collect(Collectors.toList());
    }

    @POST
    public PersonEntity savePerson(Person person) {
        return baseDAO.persist(CopyUtil.copy(person, PersonEntity.class));
    }

    @PUT
    @Path("/{id}")
    public PersonEntity updatePerson(@PathParam("id") Long id, Person person) {
        person.setId(id);
        PersonEntity toUpdate = CopyUtil.copy(person, PersonEntity.class);
        PersonEntity newInstance = baseDAO.merge(toUpdate);
        toUpdate.setAge(String.valueOf(Long.parseLong(toUpdate.getAge()) + 1L));
        return baseDAO.update(toUpdate);
    }

    @GET
    @Path("dynamic")
    public List getDynamic() {
        return baseDAO.dynamicAttribute();
    }
}
