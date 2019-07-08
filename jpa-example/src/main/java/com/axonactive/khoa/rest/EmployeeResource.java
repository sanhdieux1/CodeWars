package com.axonactive.khoa.rest;

import com.axonactive.khoa.dao.EmployeeDao;
import com.axonactive.khoa.model.Employee;
import com.axonactive.khoa.utils.CopyUtil;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeeDao employeeDao;

    @GET
    @Path("/find-by-company-address")
    public List<Employee> findByCompanyName(@QueryParam("companyAddress") String companyAddress){
        return  employeeDao.findByCompanyAddress(companyAddress)
                .stream()
                .map(employee -> CopyUtil.copy(employee, Employee.class))
                .collect(Collectors.toList());
    }

    @GET
    public List<Employee> getAll(){
        return employeeDao.findAll()
                .stream()
                .map(employee -> CopyUtil.copy(employee, Employee.class))
                .collect(Collectors.toList());
    }
}
