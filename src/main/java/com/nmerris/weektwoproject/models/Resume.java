package com.nmerris.weektwoproject.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
public class Resume {



    // TODO: id is not auto incrementing, must fix this
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 1, max = 40)
    private String nameFirst;

    @NotNull
    private String nameLast;

//    @DateTimeFormat
    @NotNull
    private String dateStart;

    private String email;

    // thymeleaf can handle Java Date objects!
    //    Bean validation doesn't matter, you should use Thymeleaf formatting:
//
//<td th:text="${#dates.format(sprint.releaseDate, 'dd-MMM-yyyy')}"></td>
//    Also make sure your releaseDate property is java.util.Date.
//
//    Output will be like: 04-Oct-2016

    // ok to be null, assume currently employed
    private String dateEnd;

    // ok to be null
//    @Max(99)
    private String organization;



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
