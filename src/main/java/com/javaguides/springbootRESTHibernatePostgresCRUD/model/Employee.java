package com.javaguides.springbootRESTHibernatePostgresCRUD.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Email")
    private String email;
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
