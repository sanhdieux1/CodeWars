package com.axonactive.khoa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;


//@NamedEntityGraphs(value = {
//        @NamedEntityGraph(
//                name = "graph.employees", attributeNodes = { @NamedAttributeNode("employees")}),
//        @NamedEntityGraph(
//                name = "graph.employeesIncludePerson", attributeNodes = { @NamedAttributeNode(value = "employees", subgraph = "subgragh.person")},
//                subgraphs = {
//                        @NamedSubgraph(name = "subgragh.person", attributeNodes = { @NamedAttributeNode("person")})
//                })
//})

@Entity(name = "company")
@Getter
@Setter
public class CompanyEntity implements Serializable {
    
    @Id
    @Column(name = "id")
    private Long id;
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(referencedColumnName = "name",
//            name = "company_name", //column name of EmployeeEntity
//            insertable = false, updatable = false, foreignKey = @ForeignKey(NO_CONSTRAINT)
//            )
//    private List<EmployeeEntity> employees;
}
