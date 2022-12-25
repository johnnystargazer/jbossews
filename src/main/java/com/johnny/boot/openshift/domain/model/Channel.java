package com.johnny.boot.openshift.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Channel extends IdEntity {
	private String requiredAttribute;
	@ManyToOne
	private Template template;

	public String getRequiredAttribute() {
		return requiredAttribute;
	}

	public void setRequiredAttribute(String requiredAttribute) {
		this.requiredAttribute = requiredAttribute;
	}

}
