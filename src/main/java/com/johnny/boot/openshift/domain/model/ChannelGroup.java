package com.johnny.boot.openshift.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ChannelGroup extends IdEntity {
	private Template masterTemplate;

	@ManyToOne
	public Template getMasterTemplate() {
		return masterTemplate;
	}

	public void setMasterTemplate(Template masterTemplate) {
		this.masterTemplate = masterTemplate;
	}

}
