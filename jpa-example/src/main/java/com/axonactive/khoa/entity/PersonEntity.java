package com.axonactive.khoa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="person")
@NamedQueries(
        @NamedQuery(name = "dynamicField", query = "SELECT t.name as name, t.age as age FROM PersonEntity t")
)
public class PersonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;


}
