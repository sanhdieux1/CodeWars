package com.axonactive.khoa.rest;

import com.axonactive.khoa.dao.CompanyDAO;
import com.axonactive.khoa.entity.CompanyEntity;
import com.axonactive.khoa.model.Company;
import com.axonactive.khoa.utils.CopyUtil;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/companies")
public class CompanyResource {
    @Inject
    private CompanyDAO companyDAO;
    
    @GET
    public List<Company> findAll(){
        List<Company> emps = companyDAO.getAll(CompanyEntity.class).stream()
                .map(com -> CopyUtil.copy(com, Company.class))
                .collect(Collectors.toList());
        return emps;
    }

//    @GET
//    @Path("/no-employee")
//    public List<Company> findCompaniesHaveNoEmployee(){
//        List<Company> emps = companyDAO.findCompaniesHaveNoEmployee().stream()
//                .map(com -> CopyUtil.copy(com, Company.class))
//                .collect(Collectors.toList());
//        return emps;
//    }

    @GET
    @Path("/no-employee")
    public List<Company> findCompaniesHaveNoEmployee() {
        List<CompanyEntity> rs = companyDAO.findCompaniesHaveNoEmployee_solution();
        List<Company> emps = rs.stream()
                .map(com -> CopyUtil.copy(com, Company.class))
                .collect(Collectors.toList());
        return emps;
    }
}
