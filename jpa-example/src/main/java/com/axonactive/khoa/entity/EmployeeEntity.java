package com.axonactive.khoa.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @Column(name = "department")
    private String department;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private PersonEntity person;

    @Column(name = "company_name")
    private String companyName;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "company_name",
            name = "address", //column name of EmployeeEntity
            insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
//            @JoinColumn(referencedColumnName = "id",
//                    name = "id", //column name of EmployeeEntity
//                    insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
//            )
    
//    @PrimaryKeyJoinColumn(referencedColumnName = "name",
//            name = "company_name", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    @JoinTable(
//            name="tbl_settings_objectproxy",
//            joinColumns = {@JoinColumn(name = "company_name")},
//            inverseJoinColumns = {@JoinColumn( name = "name"), @JoinColumn( name = "address")}
//    )
    private List<CompanyEntity> companyEntity;
}
