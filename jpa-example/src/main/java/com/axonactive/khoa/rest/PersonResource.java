package com.axonactive.khoa.rest;

import com.axonactive.khoa.dao.PersonDAO;
import com.axonactive.khoa.entity.PersonEntity;
import com.axonactive.khoa.model.Person;
import com.axonactive.khoa.utils.CopyUtil;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("/persons")
public class PersonResource {
    @Inject
    private PersonDAO baseDAO;

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
}
