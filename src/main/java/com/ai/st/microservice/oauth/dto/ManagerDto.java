package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;
import java.util.Date;

public class ManagerDto implements Serializable {

	private static final long serialVersionUID = 7175035438779604827L;

	private Long id;
	private String name;
	private String taxIdentificationNumber;
	private String alias;
	private Date createdAt;

	public ManagerDto() {

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

	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
