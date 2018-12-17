package com.netcracker.edu.backend.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private long id;
    @NotNull
    @Size(min = 1,max=40)
    private String code;
    @NotNull
    @Size(min = 1,max=45)
    private String description;
    @NotNull
    private Account reporter;

    public Project(){}

    public Project(long id,String code,String description,Account reporter){
        this.id = id;
        this.code = code;
        this.description = description;
        this.reporter = reporter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getReporter() {
        return reporter;
    }

    public void setReporter(Account reporter) {
        this.reporter = reporter;
    }
}
