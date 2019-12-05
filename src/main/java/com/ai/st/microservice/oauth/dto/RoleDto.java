package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 2472856029377390970L;

	private Long id;
	private String name;

	public RoleDto() {

	}

	public RoleDto(Long id, String name) {
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
