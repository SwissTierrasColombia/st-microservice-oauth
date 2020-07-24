package com.ai.st.microservice.oauth.dto;

import java.io.Serializable;
import java.util.Date;

public class OperatorDto implements Serializable {

	private static final long serialVersionUID = 4784320463657739097L;

	private Long id;
	private String name;
	private String taxIdentificationNumber;
	private Date createdAt;
	private Boolean isPublic;

	public OperatorDto() {

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

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

}
