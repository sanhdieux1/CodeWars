package com.axonactive.khoa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;


@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "graph.employees", attributeNodes = { @NamedAttributeNode("employees")}),
        @NamedEntityGraph(
                name = "graph.employeesIncludePerson", attributeNodes = { @NamedAttributeNode(value = "employees", subgraph = "subgragh.person")},
                subgraphs = {
                        @NamedSubgraph(name = "subgragh.person", attributeNodes = { @NamedAttributeNode("person")})
                })
})

@Entity(name = "company")
@Getter
@Setter
public class CompanyEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "company_name",
            name = "company_name", //column name of EmployeeEntity
            insertable = false, updatable = false, foreignKey = @ForeignKey(NO_CONSTRAINT)
            )
    private List<EmployeeEntity> employees;
}
