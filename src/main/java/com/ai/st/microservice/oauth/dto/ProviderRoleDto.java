package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;

public class ProviderRoleDto implements Serializable {

    private static final long serialVersionUID = -8478165185005520773L;

    private Long id;
    private String name;

    public ProviderRoleDto() {

    }

    public ProviderRoleDto(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
