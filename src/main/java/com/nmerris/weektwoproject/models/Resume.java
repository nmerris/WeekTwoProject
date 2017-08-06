package com.nmerris.weektwoproject.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;

@Entity
public class Resume {

    // the pattern to validate for date entries
    public static final String DATE_PATTERN = "MM/dd/yyyy";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // suppress the default error message because we want to use our own locale specific message
    @NotEmpty
    private String nameFirst;

    @NotEmpty
    private String nameLast;

    // email can be null, but if user enters anything it will be validated
    @Email
    private String email;

    // ok to be null
    private String organization;

    // I did my own custom validation for the dates
    private String dateStart;

    // if no end date entered, assume still employed at organization
    private String dateEnd;




    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
