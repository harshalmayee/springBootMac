package com.javaguides.springbootRESTHibernatePostgresCRUD.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

    @Schema(
            description = "Unique Identifier of Employee",
            required = true,
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Schema(
            description = "First Name Of Employee",
            required = true,
            example = "Harshal"
    )
    @Size(max=100)
    @Column(name = "First_Name")
    private String firstName;

    @Schema(
            description = "Surname Of Employee",
            required = true,
            example = "Mayee"
    )
    @Size(max=100)
    @Column(name = "Last_Name")
    private String lastName;

    @Schema(
            description = "Email Id Of Employee",
            required = false,
            example = "harshal@gmail.com"
    )
    @Size(max=100)
    @Email(message = "Email Address")
    @Column(name = "Email")
    private String email;

    @Schema(
            description = "Profession of Employee",
            required = false,
            example = "Software Developer"
    )
    @Size(max=100)
    @Column(name="Profession")
    private String profession;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email,String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profession=profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
