package com.nmerris.weektwoproject.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import java.util.Date;

public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String nameFirst;

    @NotNull
    private String nameLast;

    @NotNull
    private Date dateStart;

    // thymeleaf can handle Java Date objects!
    //    Bean validation doesn't matter, you should use Thymeleaf formatting:
//
//<td th:text="${#dates.format(sprint.releaseDate, 'dd-MMM-yyyy')}"></td>
//    Also make sure your releaseDate property is java.util.Date.
//
//    Output will be like: 04-Oct-2016

    // ok to be null, assume currently employed
    private Date dateEnd;

    @Max(99)
    @NotNull
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


}
