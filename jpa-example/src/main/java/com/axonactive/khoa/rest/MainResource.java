package com.axonactive.khoa.rest;

import com.axonactive.khoa.dao.BaseDAO;
import com.axonactive.khoa.entity.PersonEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/main")
public class MainResource {
    @Inject
    private BaseDAO baseDAO;
    @GET
    @Path("/helloworld")
    public String helloworld() {
        return "Hello World!";
    }

    @GET
    @Path("/person")
    public List<PersonEntity> getAllPerson(){
        return baseDAO.getAll(PersonEntity.class);
    }

    @POST
    @Path("/person")
    public PersonEntity savePerson(PersonEntity personEntity){
        return baseDAO.persist(personEntity);
    }
}
