package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 2472856029377390970L;

	public static final Long ROLE_ADMINISTRATOR = (long) 1;
	public static final Long ROLE_MANAGER = (long) 2;
	public static final Long ROLE_OPERATOR = (long) 3;
	public static final Long ROLE_SUPPLY_SUPPLIER = (long) 4;
	public static final Long ROLE_SUPER_ADMINISTRATOR = (long) 5;

	public static final Long SUB_ROLE_DIRECTOR_MANAGER = (long) 1;

	public static final Long SUB_ROLE_DIRECTOR_PROVIDER = (long) 1;

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
