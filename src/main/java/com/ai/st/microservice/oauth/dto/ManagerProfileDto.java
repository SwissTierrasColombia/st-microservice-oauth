package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;

public class ManagerProfileDto implements Serializable {

    private static final long serialVersionUID = 6415729231877595623L;

    private Long id;
    private String description;
    private String name;

    public ManagerProfileDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
