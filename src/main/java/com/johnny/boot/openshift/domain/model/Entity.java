package com.johnny.boot.openshift.domain.model;

public interface Entity<T> {
	public T getId();

	public void setId(T id);

}
