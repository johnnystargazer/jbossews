package com.johnny.boot.openshift.domain.model;

import javax.persistence.Entity;

@Entity
public class Project extends IdEntity {
	private ProjectStatus projectStatus;
	private Double quotationTotal;
	private Double transferTotal;

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Double getQuotationTotal() {
		return quotationTotal;
	}

	public void setQuotationTotal(Double quotationTotal) {
		this.quotationTotal = quotationTotal;
	}

	public Double getTransferTotal() {
		return transferTotal;
	}

	public void setTransferTotal(Double transferTotal) {
		this.transferTotal = transferTotal;
	}

}
