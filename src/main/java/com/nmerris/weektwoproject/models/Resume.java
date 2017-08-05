package com.nmerris.weektwoproject.models;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Resume {

    // the pattern to validate for date entries
    private static final String DATE_PATTERN = "MM/dd/yyyy";


    // TODO: id is not auto incrementing, must fix this.. actually changing application.properties file fixes this
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // suppress the default error message because we want to use our own locale specific message
    @NotNull
    @Size(min = 1, max = 40, message = "")
    private String nameFirst;

    @NotNull
    @Size(min = 1, max = 40, message = "")
    private String nameLast;

    // email can be null, but if user enters anything it will be validated
    @Email
    private String email;

    // ok to be null
    private String organization;

    // require the date be of a given format, ie 07/04/1776 = July 4th, 1776
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private Date dateStart;

    // ok to be null, if so assume currently employed
    // but if user enters anything, it will be validated
    @DateTimeFormat(pattern = DATE_PATTERN)
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
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
